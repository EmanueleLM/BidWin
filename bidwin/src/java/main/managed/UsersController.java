package main.managed;

import main.facade.UsersFacade;
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
import main.Users;
import main.session.NotificationsSession;
import main.session.SearchSession;
import main.session.UserSession;

/**
 * 
 * @author Emanuele
 */

@ManagedBean(name = "usersController")
@SessionScoped
public class UsersController implements Serializable {

    private Users current;
    private DataModel items = null;
    
    @EJB
    private main.facade.UsersFacade ejbFacade;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<Users> currentusers;
    private Users currentUser;
    
    @EJB
    UserSession usersession;
    
    @EJB
    private SearchSession search;
    
    @EJB
    private NotificationsSession notificationssession;

    /**
     * empty constructor of the class
     */
    public UsersController() {
    }

    /**
     * serach a user by its name (or part of the name, see sql functions in searchsession for details)
     * @param keyword the keyword used by the search engine
     * @return the matching users
     */
    public String searchByUser(String keyword) {
        currentusers = search.getUsersByName(keyword);
        return "displayusers";
    }

    /**
     * get the current users
     * @return the list of the current users
     */
    public List<Users> getCurrentusers() {
        return this.currentusers;
    }

    /**
     * get the current user
     * @return the list of the current user
     */
    public Users getCurrentUser() {
        return this.currentUser;
    }

    /**
     * get list of users that needs to be voted by the current one
     * @return list of users that needs to be voted by the current one
     */
    public List<Users> getUsersToVote() {
        return notificationssession.getUsersToVote();
    }

    /**
     * get the current usercontroller
     * @return the current usercontroller
     */
    public Users getSelected() {
        if (current == null) {
            current = new Users();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UsersFacade getFacade() {
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
     * @return the page list for the root
     */    
    public String prepareListFromRoot() {
        recreateModel();
        return "users/List";
    }
    
    /**
     * crud function
     * @return the page view
     */
    public String prepareView() {
        current = (Users) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    /**
     * crud function
     * @return the page create
     */
    public String prepareCreate() {
        current = new Users();
        selectedItemIndex = -1;
        return "Create";
    }

    /**
     * show the page where you vote a user
     * @param username the user who is receiving the vote
     * @return the page where the user is receiving the vote
     */
    public String prepareVote(String username) {
        currentUser = new Users();
        currentUser = usersession.find(username);
        return "rankuser?redirect=true";
    }

    /**
     * function used after the vote on a user is completed, update the rank of the user, and return to the previous page
     * @param username the name of the user who received the vote
     * @param vote the vote itself (from 1 to 5)
     * @return the previous page (where are displayed the users that still needs a vote)
     */
    public String vote(String username, float vote) {
        usersession.updateranking( username, (int) (vote*100) );
        notificationssession.setUserVoted(username);
        return "toberankedusers?redirect=true";
    }
    
    /**
     * crud function
     * @return the function preparecreate (null if fails)
     */
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsersCreated"));
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
        current = (Users) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsersUpdated"));
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
        current = (Users) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsersDeleted"));
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

    @FacesConverter(forClass = Users.class)
    public static class UsersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsersController controller = (UsersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usersController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Users) {
                Users o = (Users) object;
                return getStringKey(o.getUsername());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Users.class.getName());
            }
        }

    }

}
