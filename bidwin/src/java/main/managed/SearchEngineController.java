/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.managed;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import main.Auction;
import main.Users;
import main.session.BidSession;
import main.session.SearchSession;

/**
 *
 * @author Ga
 */
@ManagedBean(name="searchBean")
@RequestScoped
public class SearchEngineController {
    
    private String keyword;
    private List<Users> currentusers;
    private List<Auction> currentauctions;
    
    @EJB
    private SearchSession search;
    
    @EJB
    private BidSession bidsession;
    
    private int auctionid;
    private Auction currentAuction;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Users> getCurrentusers() {
        return this.currentusers;
    }

    public List<Auction> getCurrentauctions() {
        return this.currentauctions;
    }

    public int getAuctionid() {
        return this.auctionid;
    }

    public Auction getCurrentAuction() {
        return this.currentAuction;
    }

    public String searchByUser() {
        currentusers = search.getUsersByName(keyword);
        return "displayusers";
    }
    
    public String searchByObject() {
        currentauctions = search.getAuctionsByTag(keyword);
        System.out.println(keyword);
        return "displayobjects";
    }
    
    public String prepareBid(int auctionid) {
        this.auctionid = auctionid;
        currentAuction = new Auction();
        currentAuction = bidsession.getAuctionFromId(auctionid);
        return "makebid";
    }

}
