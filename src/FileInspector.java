import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                System.out.println("\nFile contents: ");
                while ((rec = reader.readLine()) != null) {
                    System.out.println(rec);
                    lineCount++;
                    Scanner lineScanner = new Scanner(rec);
                    while (lineScanner.hasNext()) {
                        lineScanner.next();
                        wordCount++;
                    }
                    charCount += rec.length();
                }
                reader.close();

                System.out.println("\nFile stats: ");
                System.out.println("File name: " + selectedFile.getName());
                System.out.println("Lines: " + lineCount);
                System.out.println("Words: " + wordCount);
                System.out.println("Characters: " + charCount);
            } else {
                System.out.println("No file selected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
