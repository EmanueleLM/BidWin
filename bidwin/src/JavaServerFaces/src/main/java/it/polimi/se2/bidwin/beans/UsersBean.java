/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.se2.bidwin.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.sql.DataSource;
 
/**
 *
 * @author Ga
 */
import it.polimi.se2.bidwin.entity.Users;
import javax.faces.bean.ManagedBean;
 
@ManagedBean(name="users")
@SessionScoped
public class UsersBean implements Serializable{
 
	//resource injection
	@Resource(name="jdbc/glassfish_resource")
	private DataSource ds;
 
	//if resource injection is not support, you still can get it manually.
	/*public CustomerBean(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mkyongdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
 
	}*/
 
	//connect to DB and get customer list
	public List<Users> getUsersList() throws SQLException{
 
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		Connection con = ds.getConnection();
 
		if(con==null)
			throw new SQLException("Can't get database connection");
 
		PreparedStatement ps; 
                ps = con.prepareStatement(
                    "select username, name, surname from glassfish.user"); 
 
		//get customer data from database
		ResultSet result =  ps.executeQuery();
 
		List<Users> list = new ArrayList<Users>();
		while(result.next()){
			Users usr = new Users();
 
			usr.setUsername(result.getString("username"));
			usr.setName(result.getString("name"));
			usr.setSurname(result.getString("surname"));
			//store all data into a List
			list.add(usr);
		}
 
		return list;
	}
}
