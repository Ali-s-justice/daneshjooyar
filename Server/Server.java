import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Welcome to the Server!");
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("waiting for client...");
            new ClientHandler(serverSocket.accept()).start();
        }
    }
}

class ClientHandler extends Thread {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    ClientHandler(Socket socket) {
        this.socket = socket;
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataOutputStream(socket.getInputStream());
        System.out.println("");

    }

    @Override
    public void run() {

    }
}
