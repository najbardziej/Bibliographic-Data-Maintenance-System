package sample;

import java.io.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class DocxExport {
    // from Java Object to docx file
    public void javaObjectToDocxFile(MyJavaObject obj, String path) {
        File file = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println("Error during open file");
            e.printStackTrace();
        }

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Title: " + obj.getTitle());
        run.addBreak();
        run.setText("Author: " + obj.getAuthor());
        run.addBreak();
        run.setText("Publisher: " + obj.getPublisher());
        run.addBreak();
        run.setText("Year: " + obj.getYear());
        run.addBreak();

        try {
            document.write(fos);
        } catch (IOException e) {
            System.err.println("Error: can't write to file");
            e.printStackTrace();
        }

        try {
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            System.err.println("Error: file can't be closed");
            e.printStackTrace();
        }
    }

}
