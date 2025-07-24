import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int recordCount = 1;

        do {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter last name");
            String email = SafeInput.getNonZeroLenString(in, "Enter email");
            int birthYear = SafeInput.getRangedInt(in, "Enter year of birth", 1900, 2024);
            String idNumber = String.format("%06d", recordCount);
            String cvs = String.join("," , firstName, lastName, idNumber, email, String.valueOf(birthYear));
            records.add(cvs);
            recordCount++;
        } while (SafeInput.getYNConfirm(in, "Would you like to enter another record?"));
        String fileName = SafeInput.getNonZeroLenString(in, "Enter file name (without extension)") + ".csv";
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "src", fileName);
        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : records) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("CSV file saved successfully to " + file.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing the file!");
            e.printStackTrace();
        }
    }
}


