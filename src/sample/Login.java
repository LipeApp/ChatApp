package sample;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView avatar_image;

    @FXML
    private TextField avatar_input;

    @FXML
    private TextField user_name_input;

    @FXML
    void avatar_change(InputMethodEvent event) {
        System.out.println("change");
        String avatar_url = avatar_input.getText();
        if (avatar_url.startsWith("http") && (avatar_url.endsWith("png") || avatar_url.endsWith("jpeg") || avatar_url.endsWith("jpg"))){
            avatar_image.setFitWidth(150);
            avatar_image.setFitHeight(150);
            avatar_image.setImage(new Image(avatar_url));
        }
        IM.avatar = avatar_url;
    }
    @FXML
    void login_click(MouseEvent event){
        IM.name = user_name_input.getText();
        IM.avatar = avatar_input.getText();
        if (user_name_input.getText().length()>0){

            Stage primaryStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 600, 600));
            primaryStage.show();
        }
        Stage st = (Stage) avatar_input.getScene().getWindow();
        st.close();
    }
    @FXML
    void avatar_find(MouseEvent event) {
        System.out.println("change");
        String avatar_url = avatar_input.getText();
        if (avatar_url.startsWith("http") && (avatar_url.endsWith("png") || avatar_url.endsWith("jpeg") || avatar_url.endsWith("jpg"))){
            avatar_image.setFitWidth(150);
            avatar_image.setFitHeight(150);
            avatar_image.setImage(new Image(avatar_url));
        }
        if (user_name_input.getText().length()>0){

        }
    }

    @FXML
    void initialize() {
        assert avatar_image != null : "fx:id=\"avatar_image\" was not injected: check your FXML file 'Login.fxml'.";
        assert avatar_input != null : "fx:id=\"avatar_input\" was not injected: check your FXML file 'Login.fxml'.";
        assert user_name_input != null : "fx:id=\"user_name_input\" was not injected: check your FXML file 'Login.fxml'.";

    }


}