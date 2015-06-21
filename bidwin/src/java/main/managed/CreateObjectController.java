package main.managed;

import main.dto.ObjectsDTO;
import main.session.ObjectSession;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Davide
 */
@ManagedBean(name="createobjectBean")
@RequestScoped
public class CreateObjectController {

    @EJB
    private ObjectSession objectsession;

    private ObjectsDTO object;

    /**
     *empty constructor of the class
     */
    public CreateObjectController() {
        object = new ObjectsDTO();
    }

    /**
     * get the objectDTO of the class
     * @return the objectDTO of the class
     */
    public ObjectsDTO getObject() {
        return object;
    }

    /**
     * set the objectDTO of the class
     * @param object the objectDTO of the class
     */
    public void setObject(ObjectsDTO object) {
        this.object = object;
    }

    /**
     * create a new object and make it persistent on the db
     * @return the homepage (the current page otherwise)
     */
    public String register() {
        objectsession.save(object);
        return "/user/welcome?faces-redirect=true"; 
    }

}
