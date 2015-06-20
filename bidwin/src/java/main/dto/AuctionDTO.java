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


    public int getNumberauction() {
        return numberauction;
    }

    public void setNumberauction(int numberauction) {
        this.numberauction = numberauction;
    }

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

}
