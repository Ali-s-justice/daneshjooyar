import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CharInputReader {

    public static void main(String[] args) {

        String[] splitInputString = new String[4];
        splitInputString[0] = "signup";
        splitInputString[1] = "studentId";
        splitInputString[2] = "username";
        splitInputString[3] = "password";
        ArrayList<String> temp = new ArrayList<>(List.of(splitInputString));
        temp.removeFirst();
        String[] strings = temp.toArray(new String[0]);
        System.out.println(strings[0]);
    }

}