import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        SeznamiUV seznamiUV = new SeznamiUV();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String output;

        try {
            do {
                if (seznamiUV.getCount() == - 1 && !seznamiUV.isReset() && seznamiUV.getCountFind() == - 1 &&
                        seznamiUV.getCountRem() == - 1) {
                    System.out.print("command> ");
                }
                input = br.readLine();
                output = seznamiUV.processInput(input);
                if (seznamiUV.getCount() == - 1 && seznamiUV.getCountFind() == - 1 && seznamiUV.getCountRem() == - 1) {
                    System.out.println(output);
                } else
                    System.out.print(output);
            }
            while (!input.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            System.err.println(">> Invalid command");
            System.exit(1);
        }
    }
}
