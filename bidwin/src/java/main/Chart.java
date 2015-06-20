/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emanuele
 */
@Entity
@XmlRootElement
public class Chart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "username")
    @Id
    private String username;
    @Column(name = "value")
    private int value;
    @Column(name = "count")
    private int count;

    /**
     * get the username of a user in the chart (which manages the position of a specific username in an auction)
     * @return the username related to the chart
     */
    public String getUsername() {
        return username;
    }

    /**
     * get the username of the user in a chart(which manages the position of a specific username in an auction)
     * @param username the username of the user related to a chart
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * teh value of a bid in a chart
     * @return the value of the bid in the row of the chart
     */
    public int getValue() {
        return value;
    }

    /**
     * set the value of a bid in a chart
     * @param value the value of a bid in a chart
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * function used to get the parameter used to check whether a user is a winner or not
     * @return the parameter used to check whether a user is the winner or not
     */
    public int getCount() {
        return count;
    }

    /**
     * set the parameter used to check whether a user is a winner or not in a chart
     * @param count the paratmeter used to check whether a user is a winner or not
     */
    public void setCount(int count) {
        this.count = count;
    }
    
    
    
    
}
