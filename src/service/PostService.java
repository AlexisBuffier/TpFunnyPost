package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entite.Post;

public class PostService {

	public ArrayList<Post> getAllPost()
	{
		try {
			Connection conn = Connexion.getConnection();
			
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM post ORDER BY id ASC");
			
			ArrayList<Post> posts = new ArrayList<>();
			

            while(rs.next()) {
            	
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");

                Post post = new Post(id, title, body);
                posts.add(post);
            }

            return posts;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getBodyByTitle(String titlePost)
	{
		try {
			Connection conn = Connexion.getConnection();
			
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT body FROM post where title like '" + titlePost + "'");
			
			if (rs.next()) {
	            return rs.getString("body");
	        } else {
	            return null; 
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean deletePost(int id)
	{
		try {
			
			Connection conn = Connexion.getConnection();
			
			String deleteSql = "DELETE FROM post WHERE id = ?";
			
			PreparedStatement statement = conn.prepareStatement(deleteSql);
            statement.setInt(1, id);
            statement.executeUpdate();
            
            return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean addPost(String title, String body)
	{
	    try {
	    	Connection conn = Connexion.getConnection();
	    	
	    	Boolean verifExistPost = verifPostExist(title);
	    	
	    	if(verifExistPost)
	    	{
	    		return false;
	    	}

	        String maxIdSql = "SELECT MAX(id) AS maxId FROM post";
	        PreparedStatement statement = conn.prepareStatement(maxIdSql);
	        ResultSet resultSet = statement.executeQuery();

	        int maxId = 0;
	        if (resultSet.next()) {
	            maxId = resultSet.getInt("maxId");
	        }

	        String insertSql = "INSERT INTO post (id, title, body) VALUES (?, ?, ?)";
	        statement = conn.prepareStatement(insertSql);
	        statement.setInt(1, maxId + 1);
	        statement.setString(2, title);
	        statement.setString(3, body);

	        statement.executeUpdate();
	        return true;
	    } catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean verifPostExist(String title)
	{
		try {
			Connection conn = Connexion.getConnection();
			
			String selectSql = "SELECT count(*) FROM post where title = ?";
			
			PreparedStatement statement = conn.prepareStatement(selectSql);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next() && resultSet.getInt(1) > 0)
            {
            	return true;
            }
            else
            {
            	return false;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
