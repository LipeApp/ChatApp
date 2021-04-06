package sample;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXListView<?> user_list;

    @FXML
    private Text chat_user;

    @FXML
    private JFXListView<?> chat_list;

    @FXML
    private JFXTextArea textarea;

    @FXML
    void search_release(KeyEvent event) {

    }

    @FXML
    void send_click(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert user_list != null : "fx:id=\"user_list\" was not injected: check your FXML file 'sample.fxml'.";
        assert chat_user != null : "fx:id=\"chat_user\" was not injected: check your FXML file 'sample.fxml'.";
        assert chat_list != null : "fx:id=\"chat_list\" was not injected: check your FXML file 'sample.fxml'.";
        assert textarea != null : "fx:id=\"textarea\" was not injected: check your FXML file 'sample.fxml'.";

    }
}
