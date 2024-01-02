package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.PostService;

public class AddPostController {

    @FXML
    private TextField inputTitre;

    @FXML
    private TextArea inputBody;
    
    @FXML
    private Button btnAccueil;
    
    @FXML
    private Label labelPost;
    
    @FXML
    private Button btnAddPost;
    
    PostService postService = new PostService();
    
    public void addPost()
    {
    	try {
	    	String title = inputTitre.getText();
	    	String body = inputBody.getText();
	    	
	    	Boolean insert = postService.addPost(title, body);
	    	
	    	if(insert)
			{
	    		labelPost.setTextFill(Color.GREEN);
	    		labelPost.setText("Post: " + title + " ajouté !");
	    		labelPost.setVisible(true);
			}
			else
			{
				labelPost.setTextFill(Color.RED);
				labelPost.setText("Erreur lors de l'ajout !");
				labelPost.setVisible(true);
			}
    	} catch (Exception e) {
			e.printStackTrace();
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
