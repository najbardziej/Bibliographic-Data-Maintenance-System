package sample;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;


public class RtfExport {
    // from Java Object to rtf file
    public void javaObjectToRtfFile(MyJavaObject obj, String path) {
        RTFEditorKit rtfParser = new RTFEditorKit();
        Document document = rtfParser.createDefaultDocument();
        try {
            document.insertString(document.getLength(), "Title: " + obj.getTitle() + '\n', null);
            document.insertString(document.getLength(), "Author: " + obj.getAuthor() + '\n', null);
            document.insertString(document.getLength(), "Publisher: " + obj.getPublisher() + '\n', null);
            document.insertString(document.getLength(), "Year: " + obj.getYear() + '\n', null);
        } catch (BadLocationException e) {
            System.err.println("Error: bad cursor position in document");
            e.printStackTrace();
        }

        File file;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println("Error during open file");
            e.printStackTrace();
        }

        try {
            rtfParser.write(fos, document, 0, document.getLength());
        } catch (IOException e) {
            System.err.println("Error during saving to file");
            e.printStackTrace();
        } catch (BadLocationException e) {
            System.err.println("Error: bad cursor position in file");
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
