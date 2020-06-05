package sample;

import java.io.*;

public class TxtExport {
    // from Java Object to bib file
    public void javaObjectToTxtFile(MyJavaObject obj) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("my_java_object.txt"));
            bw.write("Title: " + obj.getTitle() + '\n');
            bw.write("Author: " + obj.getAuthor() + '\n');
            bw.write("Publisher: " + obj.getPublisher() + '\n');
            bw.write("Year: " + obj.getYear() + '\n');
        } catch(IOException e) {
            System.err.println("Error during saving to file");
            e.printStackTrace();
        }

        try {
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            System.err.println("Error: buffer can't be closed");
            e.printStackTrace();
        }
    }
}
