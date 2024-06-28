package MVC_PROJECT;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SynchronizedServer {
    static final Object lock = new Object();

    public static void main(String[] args) {

        //Testing project
        StudentView studentView = new StudentView();
        StudentModel studentModel = new StudentModel();
        StudentController studentController = new StudentController(studentView, studentModel);
        studentView.setStudentController(studentController);
        studentView.RUN();
        //Testing project


//        ServerSocket serverSocket = null;
//
//        try {
//            serverSocket = new ServerSocket(9999);
//            System.out.println("Server started and listening on port 9999...");
//
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("New connection accepted: " + clientSocket);
//
//                synchronized (lock) {
//                    StudentView studentView;
//                    StudentModel studentModel = new StudentModel();
//                    Thread clientThread = new Thread(studentView = new StudentView(clientSocket));
//                    StudentController studentController = new StudentController(studentView, studentModel);
//                    studentView.setStudentController(studentController);
//                    clientThread.start();
//                    try {
//                        lock.wait(); // Wait until the current client thread finishes
//                    } catch (InterruptedException e) {
//                        System.out.println("Exception!\n");
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Exception!\n");
//        } finally {
//            try {
//                if (serverSocket != null) {
//                    serverSocket.close();
//                }
//            } catch (IOException e) {
//                System.out.println("Exception!\n");
//            }
//        }
    }
}