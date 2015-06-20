package main.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Davide
 */
public class BidDTO {
        
        @NotEmpty
    private int auctionid;
        
        @NotEmpty
    private int value;


    public int getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(int auctionid) {
        this.auctionid = auctionid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
