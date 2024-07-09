package MVC_PROJECT;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Server!");
        ServerSocket serverSocket = new ServerSocket(3559);
        LinkedHashMap<String, String> links = newsHappenLinkGetter();
        ArrayList<String> temp = new ArrayList<>();
        temp.add(links.toString());
        writer(temp, "daneshjooyar/informations/linkes.txt");
        while (true) {
            System.out.println("waiting for client...");
            new ClientHandler(serverSocket.accept()).start();
        }
    }

    public static void writer(ArrayList<String> allOfFile, String address) {
        try {
            FileWriter writer = new FileWriter(address);
            for (String s : allOfFile) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static LinkedHashMap<String, String> newsHappenLinkGetter() {
        String url = "https://www.sbu.ac.ir/";
        LinkedHashMap<String, String> announcementMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> clearedAnnouncement = new LinkedHashMap<>();

        try {
            Document document = Jsoup.connect(url).get();
            Elements announcements = document.select("section.sp-announcement.pt-5.pb-5");
            for (Element announcement : announcements) {
                Elements links = announcement.select("a");
                for (Element link : links) {
                    String title = link.text();
                    String href = link.attr("abs:href");
                    announcementMap.put(title, href);
                }
            }

            for (String key : announcementMap.keySet()) {
                if (key.length() > 17) {
                    clearedAnnouncement.put(key, announcementMap.get(key));
                }
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
        return clearedAnnouncement;
    }
}

class ClientHandler extends Thread {
    Socket socket;
    BufferedReader reader;
    BufferedWriter writer;

    ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        System.out.println("connected to Server");
    }

    public String listener() throws IOException {
        System.out.println("listener is activated!");
        StringBuilder sb = new StringBuilder();
        int index = reader.read();
        while (index != 0) {
            sb.append((char) index);
            index = reader.read();
        }
        System.out.println("read command successfully!");
        return sb.toString();
    }

    public void writer(String write) throws IOException {
        writer.write(write);
        writer.flush();
        writer.close();
        reader.close();
        socket.close();
        System.out.println(write);
        System.out.println("------------------>command finish and response sent to server");
    }

    @Override
    public void run() {
        super.run();
        String command;
        try {
            command = listener();
            System.out.println("command received: { " + command + " }");
        } catch (IOException io) {
            throw new RuntimeException(io);
        }
        StudentView studentView = new StudentView();
        StudentModel studentModel = new StudentModel();
        StudentController studentController = new StudentController(studentView, studentModel);
        studentView.setStudentController(studentController);
        String[] split = command.split("//");
        String response = studentView.allObligation(split);
        System.out.println(response);
        try {
            writer(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

