package main.managed;

import main.facade.BidFacade;
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
import main.Bid;
import main.session.BidSession;
import main.session.UserSession;

/**
 * 
 * @author Davide
 */

@ManagedBean(name = "bidController")
@SessionScoped
public class BidController implements Serializable {

    private Bid current;
    private DataModel items = null;
    @EJB
    private main.facade.BidFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private int auctionid;
    private Auction currentAuction;
    private List<Bid> currentbids;
    
    @EJB
    private UserSession usersession;
    
    @EJB
    private BidSession bidsession;

    /**
     * empty constructor
     */
    public BidController() {
    }

    /**
     * get the current bid
     * @return the current bid
     */
    public Bid getSelected() {
        if (current == null) {
            current = new Bid();
            current.setBidPK(new main.BidPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    /**
     * get the bids of the current user
     * @return the list of the bids of the current user
     */  
    public List<Auction> myOpenedBids() {
        return bidsession.getMyOpenedBids();
        
    }

    /**
     * get if you are the winner of a given auction
     * @param auctionid the auction id of the auction
     * @return yes or no whether you are the winner of the current auction
     */
    public String getWinner(int auctionid) {
        return (bidsession.isWinner(auctionid,usersession.getPrincipalUsername())) ? "yes" : "no";
    }

    /**
     * get the winner of a given auction
     * @param auctionid the auction id of the auction
     * @return the username of the winner of the current auction
     */
    public String getWinnerName(int auctionid) {
        return bidsession.getWinner(auctionid);
    }

    /**
     * get the list of my closed bids
     * @return the list of my closed bids
     */
    public List<Auction> myClosedBids() {
        return bidsession.getMyClosedBids();
    }

    private BidFacade getFacade() {
        return ejbFacade;
    }

    /**
     * get the auction id of the current bid
     * @return the auction id of the current bid
     */
    public int getAuctionid() {
        return this.auctionid;
    }

    /**
     * get the auction of the current bid
     * @return the auction of the current bid
     */
    public Auction getCurrentAuction() {
        return this.currentAuction;
    }

    /**
     * get the bids of the current bid(taken from the auction)
     * @return the bids id of the current bid (taken form the auction)
     */
    public List<Bid> getCurrentbids() {
        return this.currentbids;
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
        current = (Bid) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    /**
     * crud function
     * @return the page create
     */
    public String prepareCreate() {
        current = new Bid();
        current.setBidPK(new main.BidPK());
        selectedItemIndex = -1;
        return "Create";
    }
    /**
     * display the auction's details from a given auction id
     * @param auctionid the auction id
     * @return the page where are displayed the auction's details from a given auction id
     */
    public String prepareShowBids(int auctionid) {
        this.auctionid = auctionid;
        currentAuction = new Auction();
        currentAuction = bidsession.getAuctionFromId(auctionid);
        currentbids = bidsession.getMySpecifiedBids(auctionid);
        return "auctiondetail";
    }
    
    /**
     * crud function
     * @return the function preparecreate (null if fails)
     */
    public String create() {
        try {
            current.getBidPK().setUsername(current.getUsers().getUsername());
            current.getBidPK().setAuctionid(current.getAuction().getAuctionid());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BidCreated"));
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
        current = (Bid) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    /**
     * crud function
     * @return the page view
     */
    public String update() {
        try {
            current.getBidPK().setUsername(current.getUsers().getUsername());
            current.getBidPK().setAuctionid(current.getAuction().getAuctionid());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BidUpdated"));
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
        current = (Bid) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BidDeleted"));
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

    @FacesConverter(forClass = Bid.class)
    public static class BidControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BidController controller = (BidController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bidController");
            return controller.ejbFacade.find(getKey(value));
        }

        main.BidPK getKey(String value) {
            main.BidPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new main.BidPK();
            key.setUsername(values[0]);
            key.setAuctionid(Integer.parseInt(values[1]));
            key.setValue(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(main.BidPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUsername());
            sb.append(SEPARATOR);
            sb.append(value.getAuctionid());
            sb.append(SEPARATOR);
            sb.append(value.getValue());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Bid) {
                Bid o = (Bid) object;
                return getStringKey(o.getBidPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Bid.class.getName());
            }
        }

    }

}
