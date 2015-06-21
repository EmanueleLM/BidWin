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

    /**
     * get the auction id from the bidDTO
     * @return the auction id from the bidDTO
     */
    public int getAuctionid() {
        return auctionid;
    }

    /**
     * set the auction id of the bidDTO
     * @param auctionid the auction id of the bidDTO
     */
    public void setAuctionid(int auctionid) {
        this.auctionid = auctionid;
    }

    /**
     * get the value from the bidDTO
     * @return the value from the bidDTO
     */
    public int getValue() {
        return value;
    }

    /**
     * set the value of the bidDTO
     * @param value  the value of the bidDTO
     */
    public void setValue(int value) {
        this.value = value;
    }

}
