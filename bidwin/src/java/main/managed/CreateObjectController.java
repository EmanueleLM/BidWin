package main.managed;

import main.dto.ObjectsDTO;
import main.session.ObjectSession;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mago
 */
@ManagedBean(name="createobjectBean")
@RequestScoped
public class CreateObjectController {

	@EJB
	private ObjectSession objectsession;

	private ObjectsDTO object;


        public CreateObjectController() {
		object = new ObjectsDTO();
	}

	public ObjectsDTO getObject() {
		return object;
	}

	public void setObject(ObjectsDTO object) {
		this.object = object;
	}

        public String register() {
                objectsession.save(object);
                return "/welcome?faces-redirect=true"; 
	}

}
