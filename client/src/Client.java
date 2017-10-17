import java.io.*;
import java.net.Socket;

public class Client {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 7777;
    public InputStreamReader inReader;
    public OutputStreamWriter outWriter;
    public BufferedReader inBuffered;
    public BufferedWriter outBuffered;

    public void connect(){
        try (Socket socket = new Socket(IP, PORT)) {
            inReader = new InputStreamReader(socket.getInputStream());
            outWriter = new OutputStreamWriter(socket.getOutputStream());
            inBuffered = new BufferedReader(inReader);
            outBuffered = new BufferedWriter(outWriter);
            System.out.println("Client socket connected");

            BufferedReader userInput = new BufferedReader(new InputStreamReader (System.in));

            String serverOut;
            String msg;

            while(true){
                System.out.println("Waiting for user input");
                msg = userInput.readLine();
                System.out.println("Writing message");
                outWriter.write(msg + "\r\n");
                outWriter.flush();
                if(msg.equalsIgnoreCase("exit")){
                    System.out.println("User wanna EXIT");
                    break;
                }
                System.out.println("Message writing, waiting server response");
                serverOut = inBuffered.readLine();
                System.out.println("Server response: " + serverOut);
            }
            System.out.println("Closing socket");
            socket.close();



        } catch (IOException e){
            System.out.println("Connection fail");
            e.printStackTrace();
        }

    }
}
