package sample;

import java.io.*;

public class BibTeXExport {
    // from Java Object to bib file
    public void javaObjectToBiBTeXFile(MyJavaObject obj) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("my_java_object.bib"));
            bw.write("@Book{\n");
            bw.write("\tauthor =    " + obj.getAuthor() + ",\n");
            bw.write("\ttitle =     " + obj.getTitle() + ",\n");
            bw.write("\tpublisher = " + obj.getPublisher() + ",\n");
            bw.write("\tyear =      " + obj.getYear() + "\n");
            bw.write("}\n");
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
