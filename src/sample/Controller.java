package sample;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import okhttp3.*;
import okhttp3.Request.Builder;
import sample.api.Message;
import sample.api.Msg;
import sample.api.User;

public class Controller {

    OkHttpClient client = new OkHttpClient();
    WebSocket socket = null;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXListView<Label> user_list;

    @FXML
    private JFXListView<AnchorPane> chat_list;

    @FXML
    private JFXTextArea textarea;

    @FXML
    private ImageView file_choose;

    @FXML
    void search_release(KeyEvent event) {

    }

    @FXML
    void send_click(MouseEvent event) {
        String message = textarea.getText();
        if (message.length()>0){
            Message msg = new Message(new User(IM.name, IM.avatar),message, new Date().toString());
            socket.send(new Gson().toJson(msg));
            outBoxMessage(msg);
            textarea.clear();
        }
    }

    @FXML
    void initialize() {
        assert user_list != null : "fx:id=\"user_list\" was not injected: check your FXML file 'sample.fxml'.";
        assert chat_list != null : "fx:id=\"chat_list\" was not injected: check your FXML file 'sample.fxml'.";
        assert textarea != null : "fx:id=\"textarea\" was not injected: check your FXML file 'sample.fxml'.";

        Request request = new Request.Builder()
                .url("ws://lipe.uz:4567/chat")
                .build();
        socket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onMessage(WebSocket webSocket, okio.ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                System.out.println("onClosed...");
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                System.out.println("onClosing...");
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                System.out.println("onFailure...");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Msg msg = new Gson().fromJson(text, Msg.class);
                        uList(msg.getUserlist());
                        inBoxMessage(new Gson().fromJson(msg.getUserMessage(), Message.class));
                    }
                });
            }

            private void uList(List<User> userlist) {
                for (User u : userlist){
                    Label label = new Label(u.getName());
                    Image img = new Image(u.getAvatar());
                    ImageView imageView = new ImageView(img);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    label.setMaxWidth(100);
                    label.setMaxHeight(100);
                    label.setGraphic(imageView);
                    user_list.getItems().clear();
                    user_list.getItems().add(label);
                }
            }

            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                System.out.println("Open...");
            }
        });
        String send_user = new Gson().toJson(new Message(new User(IM.name, IM.avatar),"iimthis", new Date().toString()));
        socket.send(send_user);
        System.out.println(send_user);
    }

    private void outBoxMessage(Message message) {
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("chat_outbox.fxml"));
            HBox hBox = (HBox) pane.getChildren().get(0);
            Image image = new Image(message.getUser().getAvatar());
            ImageView view = (ImageView) hBox.getChildren().get(1);
            VBox vBox = (VBox) hBox.getChildren().get(0);
            Text name = (Text) vBox.getChildren().get(0);
            name.setText(message.getUser().getName());
            Text text = (Text) vBox.getChildren().get(1);
            text.setText(message.getMessage());
            Text data = (Text) vBox.getChildren().get(2);
            data.setText(new Date().toString());
            view.setImage(image);
            chat_list.getItems().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inBoxMessage(Message message) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("chat_inbox.fxml"));
            HBox hBox = (HBox) pane.getChildren().get(0);
            Image image = new Image(message.getUser().getAvatar());
            ImageView view = (ImageView) hBox.getChildren().get(0);
            VBox vBox = (VBox) hBox.getChildren().get(1);
            Text name = (Text) vBox.getChildren().get(0);
            name.setText(message.getUser().getName());
            Text text = (Text) vBox.getChildren().get(1);
            text.setText(message.getMessage());
            Text data = (Text) vBox.getChildren().get(2);
            data.setText(new Date().toString());
            view.setImage(image);
            chat_list.getItems().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
