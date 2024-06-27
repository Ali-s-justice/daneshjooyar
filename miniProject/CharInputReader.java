import java.io.IOException;
import java.util.Scanner;

public class CharInputReader {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter line number of your caption\n[0]:Go back");
            int repeat;
            try {
                repeat = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input!\n");
                continue;
            }

            if (repeat == 0) {
                input.close();
                break;
            }

            input.nextLine(); // Consume the newline character after reading the integer

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= repeat; i++) {
                System.out.println("Enter line " + i + " :");
                stringBuilder.append(input.nextLine()).append("\n");
            }

            String paragraph = stringBuilder.toString();
            System.out.println(paragraph);
            System.out.println("The caption has been set successfully!\n");

        }
    }

}