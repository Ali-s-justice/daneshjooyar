package MVC_PROJECT;

import java.net.Socket;
import java.util.Scanner;

public class StudentView implements Runnable{

    private final Socket clientSocket;
    StudentController studentController;

    public StudentView(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void setStudentController(StudentController studentController){
        this.studentController = studentController;
    }

//    @Override
//    public void run() {
//        synchronized (lock) {
//            System.out.println("Handling client: " + clientSocket);
//
//            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
//
//                // Read the String message from the client
//                String clientMessage = in.readLine();
//                String[] splitInputString = studentController.getObligationSplitter(this, clientMessage);
//                String[] restString = studentController.getObligationRemover(this, splitInputString);
//
//                Thread.sleep(1000);
//
//                //responding to front --> splitInputString[0] = obligation & restString = rest of input
//                String response = allObligation(splitInputString[0], restString);
//                out.println("Server received: " + clientMessage);
//
//                clientSocket.close();
//                System.out.println("Connection closed: " + clientSocket);
//            } catch (IOException | InterruptedException e) {
//                System.out.println("Exception!");
//            } finally {
//                lock.notify();
//            }
//        }
//    }




    //Testing Project
    public StudentView(){//Testing Project
        this.clientSocket = null;
    }

    @Override
    public void run(){}

    public void RUN(){
        while (true){
            System.out.println("Enter front message:\n[1]:BREAK!");
            Scanner input = new Scanner(System.in);
            String inputString = input.next();
            if (inputString.equals("1")){
                break;
            }
            String[] splitInputString = studentController.getObligationSplitter(this, inputString);
            String[] restString = studentController.getObligationRemover(this, splitInputString);
            String response = allObligation(splitInputString[0], restString);
            System.out.println(response);
        }
    }
    //Testing Project

    private String allObligation(String obligation, String[] restString){
        switch (obligation){
            case "signup"://signup student
                return signup(restString);
            case "login":
                String loginWay = studentController.getStudentLoginWay(this, restString[0]);
                if (loginWay.equals("loginWithID")){
                    return loginWithId(restString, true);
                }else {
                    return loginWithUsername(restString);
                }
            case "changeUsername":
                return chaneUsername(restString);
            case "changePassword":
                return changePassword(restString);
            case null:
                return "error";
            default:
                //??
                break;
        }
        return null;
    }

    private String changePassword(String[] restString){
        //restString = studentId//newPassword//oldPassword
        if (studentController.getNoStudentFoundById(this, restString[0])){
            return "noStudentFoundInServer";
        }
        if (studentController.getPasswordIsWrongForId(this, restString[0], restString[2])){
            return "passwordIsWrong";
        }
        studentController.getChangePassword(this, restString[0], restString[1]);
        return "successful";
    }

    private String chaneUsername(String[] restString){
        //restString = lastUsername//newUsername//password
        if (studentController.getNoStudentFoundByUsername(this, restString[0])){
            return "noStudentFoundInServer";
        }
        if (studentController.getStudentUsernameNotValid(this, restString[1])){
            return "usernameExist";
        }
        String studentId = studentController.getStudentIdByUsername(this, restString[0]);
        if (studentController.getPasswordIsWrongForId(this, studentId, restString[2])){
            return "passwordIsWrong";
        }
        studentController.getChangeUsername(this, studentId, restString[1]);
        return "successful";
    }

    private String loginWithId(String[] restString, boolean needCheck){
        //restString = studentId//password
        if (needCheck && studentController.getNoStudentFoundById(this, restString[0])){
            return "noStudentFoundInServer";
        }
        if (studentController.getStudentNotSignedUp(this, restString[0])){
            return "notSignedUp";
        }
        if (studentController.getPasswordIsWrongForId(this, restString[0], restString[1])){
            return "passwordIsWrong";
        }
        String studentName = studentController.getReturnName(this, restString[0]);
        return "successful//" + studentName;
    }

    private String loginWithUsername(String[] restString){
        //restString = username//password
        if (studentController.getNoStudentFoundByUsername(this, restString[0])){
            return "noStudentFoundInServer";
        }
        restString[0] = studentController.getStudentIdByUsername(this, restString[0]);
        return loginWithId(restString, false);
    }

    private String signup(String[] restString){
        //restString = studentId//username//password
        if (studentController.getNoStudentFoundById(this, restString[0])){
            return "noStudentFoundInServer";
        }
        if (studentController.getStudentAlreadySignedUp(this, restString[0])){
            return "alreadySignedUp";
        }
        if (studentController.getStudentUsernameNotValid(this, restString[1])){
            return "usernameExist";
        }
        studentController.getSignupStudent(this, restString[0], restString[1], restString[2]);
        String studentName = studentController.getReturnName(this, restString[0]);
        return "successful//" + studentName;
    }



}
