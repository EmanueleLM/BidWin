package main.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * 
 * @author Davide
 */

@ManagedBean(name="searchBean")
@RequestScoped
public class SearchController {
    
    private String keyword;

    /**
     * empty constructor for the current class
     */
    public SearchController() {
    }

    /**
     * get the keyword used by the search engine
     * @return the keyword used by the search engine
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * set the keyword used by the search engine
     * @param keyword  the keyword used by the search engine
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
