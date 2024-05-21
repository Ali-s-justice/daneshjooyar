import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.*;

public class Model {
    private static Model model = null;
    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();
    private Model(){

    }
    public static Model getModel(){
         if (model ==null){
             model = new Model();
         }
         return model;
    }
    // Admin
    public boolean AdminNameValidation(String NewAdminName){//Admin Name Validation
        ArrayList<String> AllAdminsName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\admins.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split(":");
                AllAdminsName.add(Info[0]);
            }
            bufferedReader.close();
            return !AllAdminsName.contains(NewAdminName);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean AdminPasswordValidation(String NewPassword, String username){
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(NewPassword);

        if (NewPassword.contains(username)) {
            return false;
        }

        return m.matches();
    }

    //Making Admin
    public void AdminMaker(String name, String Password){
        Admin NewAdmin = new Admin(name,Password);
        admins.add(NewAdmin);
    }
}
