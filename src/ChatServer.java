import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;
    ChatServer() throws IOException {
        serverSocket = new ServerSocket(1234);
    }
    void sendAll(String massage) {
        for (Client client : clients) {
            client.receive(massage);
        }
    }

    public void run(){
        while (true) {
            System.out.println("waiting...");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("clent connected!");
                clients.add(new Client(socket, this));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
