package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AccueilController {

    @FXML
    private Button btnListPost;

    @FXML
    private Button btnAddPost;
    
    public void onClickListPost(MouseEvent e) throws IOException
	{
		Parent vueListPost = FXMLLoader.load(getClass().getResource("FunnyPost.fxml"));
		Scene sceneListPost = new Scene(vueListPost);
		sceneListPost.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(sceneListPost);
		stage.show();
	}
    
    public void onClickAddPost(MouseEvent e) throws IOException
	{
		Parent vueAddPost = FXMLLoader.load(getClass().getResource("AddPost.fxml"));
		Scene sceneAddPost = new Scene(vueAddPost);
		sceneAddPost.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(sceneAddPost);
		stage.show();
	}

}
