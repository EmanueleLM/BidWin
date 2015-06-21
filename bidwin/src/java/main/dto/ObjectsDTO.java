package main.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Davide
 */
public class ObjectsDTO {
    	
        @NotEmpty
    private String objectName;
        
        @NotEmpty
    private String objectType;
	
	@NotEmpty
    private String description;
    
	@NotNull
    private String imageLink;


    /**
     * get the object's name from the Object
     * @return the object's name from the Object
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * set the object name of the object
     * @param objectName object's name of the object
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }


    /**
     * get the object's name from the Object
     * @return the object's name from the Object
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * set the object type of the object
     * @param objectType  object's type of the object
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * get the object's description from the Object
     * @return the object's description from the Object
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the object description of the object
     * @param description  object's description of the object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the object's image link from the Object
     * @return the object's image link from the Object
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     * set the object image link of the object
     * @param imageLink  object's image link of the object
     */
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
