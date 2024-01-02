package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entite.Post;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.Connexion;
import service.PostService;
import service.WebService;

public class FunnyPostController implements Initializable {
	
	@FXML
	private ListView<String> listPost;
	
	@FXML
    private Label titlePost;
	
	@FXML
    private TextArea bodyPost;
	
	@FXML
    private Button btnInit;
	
	@FXML
    private Button btnDelete;

    @FXML
    private TextField inputId;
    
    @FXML
    private Label labelDelete;
    
    @FXML
    private Button btnAccueil;
	
	String currentPost;
	
	PostService postService = new PostService();
	WebService webService = new WebService();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		updateListView();
		
		listPost.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

				currentPost = listPost.getSelectionModel().getSelectedItem();
				
				currentPost = currentPost.substring(currentPost.indexOf('.') + 2);
				
				String body = postService.getBodyByTitle(currentPost);
				
				titlePost.setText(currentPost);
				
				bodyPost.setText(body);
			}
		});
		
	}
	
	public void insertAllPost()
	{
		webService.getAllPost();
		
		listPost.getItems().clear();
		
		updateListView();
	}
	
	public void updateListView()
	{
		ArrayList<Post> posts = postService.getAllPost();
		
		for (Post Post : posts)
		{
			String item = Post.getId() + ". " + Post.getTitle();
			listPost.getItems().addAll(item);
		}
	}
	
	public void deletePost()
	{
		try {
			
			int id = Integer.valueOf(inputId.getText());
			
			Boolean delete = postService.deletePost(id);
			
			if(delete)
			{
				labelDelete.setTextFill(Color.GREEN);
				labelDelete.setText("Post: " + id + " supprimé !");
				labelDelete.setVisible(true);
				
				listPost.getItems().clear();
				
				updateListView();
			}
			else
			{
				labelDelete.setTextFill(Color.RED);
				labelDelete.setText("Erreur lors de la suppression !");
				labelDelete.setVisible(true);
			}
			
		} catch (NumberFormatException e) {
        	
			labelDelete.setTextFill(Color.RED);
			labelDelete.setText("Veuillez un nombres !");
			labelDelete.setVisible(true);
        	
        }
	}
	
	public void onClickAccueil(MouseEvent e) throws IOException
	{
		Parent vueAccueil = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
		Scene sceneAccueil = new Scene(vueAccueil);
		sceneAccueil.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(sceneAccueil);
		stage.show();
	}

}
