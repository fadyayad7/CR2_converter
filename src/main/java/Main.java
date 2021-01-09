import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("I am running");

        BufferedReader reader = null;

        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows

        processBuilder.command("powershell.exe", "/c",  "Get-ChildItem input -Name");


        try {

            Process process = processBuilder.start();

            reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String line;
        while ((line = reader.readLine()) != null) {
//            processBuilder
//                    .command("powershell.exe", "/c",
//                            "cp input/" + line + " output/" + line.replace(".CR2", ".jpg"));

            processBuilder
                    .command("powershell.exe", "/c",
                            "cp input/" + line + " output/" + line.replace(".CR2", ".png"));

            Process process = processBuilder.start();

            BufferedReader reader1 =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line1;
            while ((line1 = reader1.readLine()) != null) {
                System.out.println(line1);
            }
            System.out.println(line);
        }
    }
}
