import java.io.*;

public class Admin {
    private String name;
    private String password;
    public Admin(String name, String password){
        this.name = name;
        this.password = password;
        Model.getModel().admins.add(this);
        //returning Name And Password to File
        try {
            FileWriter AdminFileWriter = new FileWriter("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\admins.txt", true);
            AdminFileWriter.write(name + ":" + password);
            AdminFileWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }




}
