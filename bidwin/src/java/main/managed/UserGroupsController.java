package main.managed;

import main.util.JsfUtil;
import main.util.PaginationHelper;
import main.facade.UserGroupsFacade;

import java.io.Serializable;
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
import main.UserGroups;

/**
 * 
 * @author Emanuele
 */

@ManagedBean(name = "userGroupsController")
@SessionScoped
public class UserGroupsController implements Serializable {

    private UserGroups current;
    private DataModel items = null;
    @EJB
    private main.facade.UserGroupsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    /**
     * empty constructor
     */
    public UserGroupsController() {
    }

    /**
     * get the current usergroups
     * @return the current usergroups
     */
    public UserGroups getSelected() {
        if (current == null) {
            current = new UserGroups();
            current.setUserGroupsPK(new main.UserGroupsPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserGroupsFacade getFacade() {
        return ejbFacade;
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
        current = (UserGroups) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    /**
     * crud function
     * @return the page create
     */
    public String prepareCreate() {
        current = new UserGroups();
        current.setUserGroupsPK(new main.UserGroupsPK());
        selectedItemIndex = -1;
        return "Create";
    }
    
    /**
     * crud function
     * @return the function preparecreate (null if fails)
     */
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleUserGroups").getString("UserGroupsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleUserGroups").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    /**
     * crud function
     * @return the page edit
     */
    public String prepareEdit() {
        current = (UserGroups) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleUserGroups").getString("UserGroupsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleUserGroups").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    /**
     * crud function
     * @return the page list
     */
    public String destroy() {
        current = (UserGroups) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }
    
    /**
     * crud function
     * @return the page view or list
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleUserGroups").getString("UserGroupsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleUserGroups").getString("PersistenceErrorOccured"));
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

    @FacesConverter(forClass = UserGroups.class)
    public static class UserGroupsControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserGroupsController controller = (UserGroupsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userGroupsController");
            return controller.ejbFacade.find(getKey(value));
        }

        main.UserGroupsPK getKey(String value) {
            main.UserGroupsPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new main.UserGroupsPK();
            key.setUsername(values[0]);
            key.setGroupId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(main.UserGroupsPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUsername());
            sb.append(SEPARATOR);
            sb.append(value.getGroupId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserGroups) {
                UserGroups o = (UserGroups) object;
                return getStringKey(o.getUserGroupsPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UserGroups.class.getName());
            }
        }

    }

}
