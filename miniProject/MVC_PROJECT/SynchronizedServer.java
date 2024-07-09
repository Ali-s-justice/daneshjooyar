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

    }
}