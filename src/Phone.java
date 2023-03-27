import java.net.*;
import java.io.*;

public class Phone {

    ServerSocket server;
    Socket client;
    BufferedReader reader;
    BufferedWriter writer;

    public Phone(String port) { //server
        try {
            server = new ServerSocket(Integer.parseInt(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Phone(String ip, String port) {
        try {
            client = new Socket(ip, Integer.parseInt(port));
            createStreams();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void accept() {
        try {
            client = server.accept();
            createStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStreams(){
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
