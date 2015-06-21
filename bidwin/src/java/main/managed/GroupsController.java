package main.managed;

import main.facade.GroupsFacade;
import main.util.JsfUtil;
import main.util.PaginationHelper;

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
import main.Groups;

/**
 * 
 * @author Emanuele
 */
@ManagedBean(name = "groupsController")
@SessionScoped
public class GroupsController implements Serializable {

    private Groups current;
    private DataModel items = null;
    @EJB
    private main.facade.GroupsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    /**
     * empty constructor of the class
     */
    public GroupsController() {
    }

    /**
     * get the selected groupscontroller
     * @return the selected groupscontroller
     */
    public Groups getSelected() {
        if (current == null) {
            current = new Groups();
            selectedItemIndex = -1;
        }
        return current;
    }

    private GroupsFacade getFacade() {
        return ejbFacade;
    }

    /**
     * get the pagination of the objects (used in the crud)
     * @return the pagination helper (used  in the crud)
     */
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
     * function of the crud
     * @return page list
     */
    public String prepareList() {
        recreateModel();
        return "List";
    }

    /**
     * function of the crud
     * @return page list
     */
    public String prepareView() {
        current = (Groups) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    /**
     * function of the crud
     * @return page create
     */
    public String prepareCreate() {
        current = new Groups();
        selectedItemIndex = -1;
        return "Create";
    }

    /**
     * function of the crud
     * @return page create
     */
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GroupsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     * function of the crud
     * @return page edit
     */
    public String prepareEdit() {
        current = (Groups) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    /**
     * function of the crud
     * @return page view or null whether the group exists or not
     */
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GroupsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     * function of the crud
     * @return page list
     */
    public String destroy() {
        current = (Groups) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    /**
     * function of the crud
     * @return page view or edit whether the remoction is performed or not
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
     * function of the crud
     */
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GroupsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * function of the crud
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
     * function of the crud
     * @return the items to display
     */
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    /**
     * function of the crud
     */
    private void recreateModel() {
        items = null;
    }

    /**
     * function of the crud
     */
    private void recreatePagination() {
        pagination = null;
    }

    /**
     * function of the crud
     * @return next page list
     */
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    /**
     * function of the crud
     * @return previous page list
     */
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    /**
     * function of the crud
     * @return the items for the pagination
     */
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    /**
     * function of the crud
     * @return the item for the pagination
     */
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Groups.class)
    public static class GroupsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GroupsController controller = (GroupsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "groupsController");
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
            if (object instanceof Groups) {
                Groups o = (Groups) object;
                return getStringKey(o.getGroupId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Groups.class.getName());
            }
        }

    }

}
