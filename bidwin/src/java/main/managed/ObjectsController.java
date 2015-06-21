package main.managed;

import main.facade.ObjectsFacade;
import main.util.JsfUtil;
import main.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import main.Objects;
import main.session.ObjectSession;

/**
 * 
 * @author Emanuele
 */

@ManagedBean(name = "objectsController")
@SessionScoped
public class ObjectsController implements Serializable {

    private Objects current;
    private DataModel items = null;
    @EJB
    private main.facade.ObjectsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private int objectid;
    private Objects currentObject;
    
    @EJB
    private ObjectSession objectsession;

    /**
     * empty constructor for the class
     */
    public ObjectsController() {
    }

    /**
     * get the current object
     * @return the current object
     */
    public Objects getSelected() {
        if (current == null) {
            current = new Objects();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ObjectsFacade getFacade() {
        return ejbFacade;
    }

    /**
     * get the objects list of the current user
     * @return the list of the objects of the current user
     */  
    public List<Objects> myobjects() {
        return objectsession.getMyObjects();
    }

    /**
     * get the object id of the current user
     * @return the object id of the current user
     */ 
    public int getObjectid() {
        return this.objectid;
    }

    /**
     * get the object of the current user
     * @return the object of the current user
     */ 
    public Objects getCurrentObject() {
        return this.currentObject;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    /**
     * crud function
     * @return the page list
     */
    public String prepareList() {
        recreateModel();
        return "List";
    }
    
    /**
     * crud function
     * @return the page view
     */
    public String prepareView() {
        current = (Objects) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    /**
     * crud function
     * @return the page create
     */
    public String prepareCreate() {
        current = new Objects();
        selectedItemIndex = -1;
        return "Create";
    }
    
    /**
     * prepare the auction with the give object assigned to it
     * @param objectid the object assigned to the auction
     * @return the page createauction
     */
    public String prepareAuction(int objectid) {
        this.objectid = objectid;
        currentObject = new Objects();
        currentObject = objectsession.getObjectFromId(objectid);
        return "createauction";
    }
    
    /**
     * crud function
     * @return the function preparecreate(null if fails)
     */
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ObjectsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    /**
     * crud function
     * @return the page edit
     */
    public String prepareEdit() {
        current = (Objects) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    /**
     * crud function
     * @return the page view
     */
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ObjectsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    /**
     * crud function
     * @return the page list
     */
    public String destroy() {
        current = (Objects) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }
    
    /**
     * crud function
     * @return the page view ot list(whether the method fails or not)
     */
    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
    
    /**
     * crud function
     */
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ObjectsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    /**
     * crud function
     */
    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }
    
    /**
     * crud function
     * @return the items
     */
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    /**
     * crud function
     */
    private void recreateModel() {
        items = null;
    }
    
    /**
     * crud function
     */
    private void recreatePagination() {
        pagination = null;
    }
    
    /**
     * crud function
     * @return the next page list
     */
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }
    
    /**
     * crud function
     * @return the previous page list
     */
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    /**
     * crud function
     * @return the list of selected items
     */
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    /**
     * crud function
     * @return the list of selected item
     */
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Objects.class)
    public static class ObjectsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObjectsController controller = (ObjectsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "objectsController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Objects) {
                Objects o = (Objects) object;
                return getStringKey(o.getObjectid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Objects.class.getName());
            }
        }

    }
}
