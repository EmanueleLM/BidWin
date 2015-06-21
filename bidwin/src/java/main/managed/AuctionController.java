package main.managed;

import main.facade.AuctionFacade;
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
import main.Auction;
import main.Users;
import main.session.AuctionSession;
import main.session.BidSession;
import main.session.NotificationsSession;
import main.session.SearchSession;
import main.session.UserSession;

/**
 * 
 * @author Davide
 */

@ManagedBean(name = "auctionController")
@SessionScoped
public class AuctionController implements Serializable {

    private Auction current;
    private DataModel items = null;
    @EJB
    private main.facade.AuctionFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private int auctionid;
    private Users currentUser;
    private Auction currentAuction;
    private List<Auction> currentauctions;
    @EJB
    private NotificationsSession notificationsession;

    @EJB
    private AuctionSession auctionsession;
    
    @EJB
    private BidSession bidsession;
    
    @EJB
    private SearchSession search;
    
    @EJB
    UserSession usersession;

    /**
     *empty constructor of the class auctioncontoller
     */
    public AuctionController() {
    }

    /**
     * serach an object by a string keyword
     * @param keyword the string which is used for the search
     * @return the page where the result is displayed
     */
    public String searchByObject(String keyword) {
        currentauctions = search.getAuctionsByTag(keyword);
        return "displayobjects";
    }

    /**
     * get the current auctions
     * @return the current aucitons
     */
    public List<Auction> getCurrentauctions() {
        return this.currentauctions;
    }

    /**
     * get the selected auction session
     * @return the selected auction session
     */
    public Auction getSelected() {
        if (current == null) {
            current = new Auction();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AuctionFacade getFacade() {
        return ejbFacade;
    }

    /**
     * get the auctions of the current user
     * @return the list of the auction of the current user
     */
    public List<Auction> myAuctions() {
        return auctionsession.getMyOpenedAuctions();
    }

    /**
     * get the closed auctions of the current user
     * @return the list of the open auction of the current user
     */
    public List<Auction> myClosedAuctions() {
        return auctionsession.getMyClosedAuctions();
    }

    /**
     * get all but the current user's auctions
     * @return the list of all but the current user's auctions
     */
    public List<Auction> allButMineAuctions() {
        return auctionsession.getAllOpenedAuctions();
    }

    /**
     * get all but the current user's auctions, filterd by objectname
     * @param objectname  the object type used to filter the serach
     * @return the list of auctions fileterd by objectname
     */    
    public List<Auction> allNamedButMineAuctions(String objectname) {
        return auctionsession.getAuctionsByTag(objectname);
    }

    /**
     * get the auctionid from the auctionController
     * @return the auctionid from the auctionController
     */
    public int getAuctionid() {
        return this.auctionid;
    }

    /**
     * get the current auction from the auctionController
     * @return the current acution from the auctionController
     */
    public Auction getCurrentAuction() {
        return this.currentAuction;
    }

    /**
     * get the current user from the auctionController
     * @return the current user from the auctionController
     */
    public Users getCurrentUser() {
        return this.currentUser;
    }

    /**
     * get the list of notifications from the auctionController
     * @return the list of notifications from the auctionController
     */
    public List<String> getNotifications() {
        return notificationsession.getStringNotifications();
    }

    /**
     * get the pagination of the objects (used in the crud)
     * @return the pagiination helper (used  in the crud)
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
        current = (Auction) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    /**
     * function of the crud
     * @return page create
     */
    public String prepareCreate() {
        current = new Auction();
        selectedItemIndex = -1;
        return "Create";
    }

    /**
     * prepare the bid page
     * @param auctionid the auction where you want to bid
     * @return page makebid where you can make a bid
     */
    public String prepareBid(int auctionid) {
        this.auctionid = auctionid;
        currentAuction = new Auction();
        currentAuction = bidsession.getAuctionFromId(auctionid);
        return "makebid?redirect=true";
    }

    /**
     * prepare the list of the auction of a user
     * @param username the user whose auctions are displayed
     * @return user's opened auctions
     */
    public String prepareList(String username) {
        currentauctions = search.getHisOpenedAuctions(username);
        currentUser = new Users();
        currentUser = usersession.find(username);
        return "displayhisauctions?redirect=true";
    }

    /**
     * function of the crud
     * @return page list
     */
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AuctionCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     * function of the crud
     * @return page list
     */
    public String prepareEdit() {
        current = (Auction) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    /**
     * function of the crud
     * @return page list
     */
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AuctionUpdated"));
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
        current = (Auction) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    /**
     * function of the crud
     * @return page view or list
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AuctionDeleted"));
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
     * @return the list of items for the pagination of the crud
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

    @FacesConverter(forClass = Auction.class)
    public static class AuctionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AuctionController controller = (AuctionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "auctionController");
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
            if (object instanceof Auction) {
                Auction o = (Auction) object;
                return getStringKey(o.getAuctionid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Auction.class.getName());
            }
        }

    }

}
