package main.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Davide
 */
public class AuctionDTO {
        
        @NotNull
    private int numberauction;
        
        @NotEmpty
    private int objectid;

    /**
     * get the number of the auction
     * @return the number of the auction
     */
    public int getNumberauction() {
        return numberauction;
    }

    /**
     * set the number of the auction
     * @param numberauction the number of the auction
     */
    public void setNumberauction(int numberauction) {
        this.numberauction = numberauction;
    }

    /**
     * set the object id of the auction
     * @return  the object id of the auction
     */
    public Integer getObjectid() {
        return objectid;
    }

    /**
     * get the object id of the auction
     * @param objectid the object id of the auction
     */
    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

}
