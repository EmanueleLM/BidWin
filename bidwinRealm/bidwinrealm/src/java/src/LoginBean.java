package src;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;



@ManagedBean
public class LoginBean {
 
private String username;
private String password;

 
/**
* @return the username
*/
public String getUsername() {
return username;
}
 
/**
* @param username the username to set
*/
public void setUsername(String username) {
this.username = username;
}
 
/**
* @return the password
*/
public String getPassword() {
return password;
}
 
/**
* @param password the password to set
*/
public void setPassword(String password) {
this.password = password;
}
 
public String login() {
// Get faces context
        FacesContext context = FacesContext.getCurrentInstance();
        
        // Error if username or password are empty
        if(username==null || "".equals(username))
        {
            FacesMessage msg = new FacesMessage("User-name cannot be empty");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, msg);
            return "index";
        }
        if(password==null || "".equals(password))
        {
            FacesMessage msg = new FacesMessage("Password cannot be empty");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, msg);
            return "index";
        }
        
        // Get request for login
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        // Try to log the user in
        try
        {
            request.login(this.username, this.password);
        }
        catch (ServletException e)
        {
            // In case of error remain on the index page and show message
            FacesMessage msg = new FacesMessage("Login failed, try again!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, msg);
            return "loginError";
        }
        
        //INSERIRE LA CLASSE INIZIALIZER CHE INIZIALIZZA LA PAGINA DELL?UTENTE SPECIFICO
       
        // If login successful, redirect to the Registered User Home
        return "/user/home?faces-redirect=true";
    }
 
}