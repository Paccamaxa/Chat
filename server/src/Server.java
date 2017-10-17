import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 7777;
    private InputStreamReader inReader;
    private OutputStreamWriter outWriter;
    private BufferedReader inBuffered;
    private BufferedWriter outBuffered;

    public void connect(){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket;
            socket = serverSocket.accept();
            inReader = new InputStreamReader(socket.getInputStream());
            outWriter = new OutputStreamWriter(socket.getOutputStream());
            inBuffered = new BufferedReader(inReader);
            outBuffered = new BufferedWriter(outWriter);
            System.out.println("Client socket connected");

            String userInput;
            while(true){
                userInput = inBuffered.readLine();
                if(userInput.equalsIgnoreCase("exit")){
                    System.out.println("Client says EXIT");
                    break;
                }
                System.out.println("Waiting for client message");
                userInput += "\r\n";
                System.out.println("Echo to client");
                outBuffered.write(userInput);
                outBuffered.flush();
            }
            System.out.println("Server closed");

        } catch (IOException e){
            System.out.println("Connection fail");
            e.printStackTrace();
        }
    }

}
