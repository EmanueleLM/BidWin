package main;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import main.session.BidSession;

/*
1 - user wins the auction
2 - user loses the auction, anybody wins
3 - user loses the auction, nobody wins
4 - my auction, anybody wins
5 - my auction, nobody wins
6 - my auction, no partecipants
*/

@Singleton
public class TimerService {

    @EJB
    private BidSession bidsession;
    
    @Schedule(second="*/20", minute="*",hour="*", persistent=false)
    public void doWork(){
        
        if (!bidsession.auctionsToNotify().isEmpty()) {
            for (Auction a : bidsession.auctionsToNotify()) {
                
                if (bidsession.partecipants(a.getAuctionid()).isEmpty()) {
                    System.out.println("L' asta "+a.getAuctionid()+" non ha avuto nessuna puntata");
                } else{
                    for (Users u : bidsession.partecipants(a.getAuctionid())) {
                        
                        if (bidsession.getWinner(a.getAuctionid(), u.getUsername())){
                            System.out.println("L' asta "+a.getAuctionid()+" è vinta da "+u.getUsername());
                        }
                        if (!bidsession.getWinner(a.getAuctionid(), u.getUsername())){
                            System.out.println("L' asta "+a.getAuctionid()+" è persa da "+u.getUsername());
                        }
                        
                    }
                }
                
                bidsession.notifyTrue(a);
            }
        }
    }

}