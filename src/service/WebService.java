package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import entite.Post;

public class WebService {
	
	public static void insertData(Post post) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	    	
	        connection = Connexion.getConnection();

	        String checkPost = "SELECT COUNT(*) FROM post WHERE id = ?";
	        statement = connection.prepareStatement(checkPost);
	        statement.setInt(1, post.getId());
	        resultSet = statement.executeQuery();

	        if (resultSet.next() && resultSet.getInt(1) == 0) {
	            String insertSql = "INSERT INTO post (id, title, body) VALUES (?, ?, ?)";
	            statement = connection.prepareStatement(insertSql);
	            statement.setInt(1, post.getId());
	            statement.setString(2, post.getTitle());
	            statement.setString(3, post.getBody());
	            statement.executeUpdate();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

	public void getAllPost() {
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/posts");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();		
			conn.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;
			
			while((inputLine = in.readLine()) != null)
			{
				response.append(inputLine).append("\n");
			}
			in.close();
			
			JSONParser parser = new JSONParser();

	        try {
	            JSONArray jsonArray = (JSONArray) parser.parse(response.toString());

	            for (Object obj : jsonArray) {
	                JSONObject jsonObject = (JSONObject) obj;

	                Post post = new Post();
	                post.setId(Integer.parseInt(jsonObject.get("id").toString()));
	                post.setTitle((String) jsonObject.get("title"));
	                post.setBody((String) jsonObject.get("body"));
	                
	                insertData(post);
	            }
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
		} catch (Exception e1) {
            e1.printStackTrace();
        }
	}
}
