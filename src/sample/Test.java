package sample;

import com.google.gson.Gson;
import javafx.application.Platform;
import okhttp3.*;
import sample.api.Message;
import sample.api.Msg;

public class Test {
    public static void main(String[] args) {
        WebSocket socket = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("ws://localhost:4567/chat")
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
                Msg msg = new Gson().fromJson(text,Msg.class);
                System.out.println(new Gson().fromJson(msg.getUserMessage(), Message.class).getUser().getName());

            }

            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                System.out.println("Open...");
            }
        });
    }
}
