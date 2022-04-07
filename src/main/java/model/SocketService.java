package model;

import com.google.gson.Gson;
import responce.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketService extends Thread {



    public interface ResponseListener{
        void message(Response response);
    }

    private ResponseListener __listener;

    private Socket __socket;
    private final BufferedReader __bufferedReader;
    private final PrintWriter __printWriter;

    private boolean __disconnect;

    public SocketService(String string, int port) throws IOException {
        __socket = new Socket(string, port);
        __bufferedReader = new BufferedReader(new InputStreamReader(__socket.getInputStream()));
        __printWriter = new PrintWriter(__socket.getOutputStream(), true);
        start();
    }

    @Override
    public void run() {

        try {
            while (!__disconnect){
                String msg = __bufferedReader.readLine();
                if (msg != null){
                    Response _response = new Gson().fromJson(msg, Response.class);
                    __listener.message(_response);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeMessage(String result) {
        __printWriter.println(result);
    }

    public void setListener(ResponseListener listener) {
        __listener = listener;
    }

    public boolean isDisconnect() {
        return __disconnect;
    }

    public void setDisconnect(boolean disconnect) {
        __disconnect = disconnect;
    }
}
