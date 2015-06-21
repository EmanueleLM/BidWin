package main.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Davide
 */
public class UsersDTO {
	
        @NotEmpty
    private String username;
        
        @NotEmpty
    private String name;
	
	@NotEmpty
    private String surname;
    
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="invalid email")
    private String email;
	
	@NotEmpty
    private String address;
        
	@NotEmpty
    private String paymentInfo;
	
	@NotNull
    private Date birthdate;
        
        @NotEmpty
    private String password;

    /**
     * get the username from the userDTO
     * @return the username from the userDTO
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username of the userDTO
     * @param username the username of the userDTO
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get the name from the userDTO
     * @return the name from the userDTO
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the userDTO
     * @param name  the name of the userDTO
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the surname from the userDTO
     * @return the surname from the userDTO
     */
    public String getSurname() {
        return surname;
    }

    /**
     * set the surname of the userDTO
     * @param surname  the surname of the userDTO
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * get the email from the userDTO
     * @return the email from the userDTO
     */
    public String getEmail() {
        return email;
    }

    /**
     * set the email of the userDTO
     * @param email  the email of the userDTO
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get the address from the userDTO
     * @return the address from the userDTO
     */
    public String getAddress() {
        return address;
    }

    /**
     * set the address of the userDTO
     * @param address  the address of the userDTO
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get the payment info from the userDTO
     * @return the payment info from the userDTO
     */
    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * set the payment info of the userDTO
     * @param paymentInfo  the payment info of the userDTO
     */
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    /**
     * get the birthdate from the userDTO
     * @return the birthdate from the userDTO
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * set the birthdate of the userDTO
     * @param birthdate  the birthdate of the userDTO
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * get the password from the userDTO
     * @return the password from the userDTO
     */
    public String getPassword() {
        return password;
    }

    /**
     * set the password of the userDTO
     * @param password  the password of the userDTO
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
