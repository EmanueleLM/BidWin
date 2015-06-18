package main;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import main.session.BidSession;
import main.session.NotificationsSession;

/*
1 - user wins the auction
2 - user loses the auction, somebody wins
3 - user loses the auction, nobody wins
4 - my auction, somebody wins
5 - my auction, nobody wins
6 - my auction, no partecipants
*/

@Singleton
public class TimerService {

    @EJB
    private BidSession bidsession;
    
    @EJB
    private NotificationsSession notificationssession;
    
    
    @Schedule(second="*/20", minute="*",hour="*", persistent=false)
    public void doWork(){
        
        if (!bidsession.auctionsToNotify().isEmpty()) {
            for (Auction a : bidsession.auctionsToNotify()) {
                
                if (bidsession.partecipants(a.getAuctionid()).size() == 1) {
                    
                    notificationssession.save( notificationssession.owner(a), a, 6);
                    notificationssession.replicateobject( notificationssession.owner(a), a.getObjectid().getObjectName(), a.getObjectid().getObjectType(), a.getObjectid().getDescription(), a.getObjectid().getImageLink());
                    notifyUsers(a, false);
                    
                } else{
                    
                    if (bidsession.getWinner(a.getAuctionid(), "user")){
                        
                        notificationssession.save( notificationssession.owner(a), a, 5);
                        notificationssession.replicateobject( notificationssession.owner(a), a.getObjectid().getObjectName(), a.getObjectid().getObjectType(), a.getObjectid().getDescription(), a.getObjectid().getImageLink());
                        notifyUsers(a, false);
                        
                    } else {
                        
                        notificationssession.save( notificationssession.owner(a), a, 4);
                        notifyUsers(a, true);
                        
                    }
                
                }
                
                bidsession.notifyTrue(a);
            }
        }
    }

    public void notifyUsers(Auction a, boolean win) {
        for (Users u : bidsession.partecipants(a.getAuctionid())) {
            
            if ( (bidsession.getWinner(a.getAuctionid(), u.getUsername())) && (!"user".equals(u.getUsername())) ) {
                
                notificationssession.save( u, a, 1);
            
            }
            
            if ( (!bidsession.getWinner(a.getAuctionid(), u.getUsername())) && (!"user".equals(u.getUsername())) ) {
                
                if (win) {
                    notificationssession.save( u, a, 2);
                } else {
                    notificationssession.save( u, a, 3);
                }
            
            }
        
        }
    }

}