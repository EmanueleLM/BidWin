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

    public SearchController() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
