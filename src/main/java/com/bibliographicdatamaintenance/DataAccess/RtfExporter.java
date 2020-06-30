package com.bibliographicdatamaintenance.DataAccess;

import com.bibliographicdatamaintenance.Models.Book;

import java.io.*;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;


public class RtfExporter implements IExporter{
    public void exportToFile(List<Book> bookList, String path) {
        RTFEditorKit rtfParser = new RTFEditorKit();
        Document document = rtfParser.createDefaultDocument();
        try {
            for(Book book : bookList) {
                document.insertString(document.getLength(),
                        book.getAuthor() + ", " + book.getTitle() + ", " + book.getPublisher() + ", " + book.getYear(),
                        null);
                document.insertString(document.getLength(), "\n", null);
            }
        } catch (BadLocationException e) {
            System.err.println("Error: bad cursor position in document");
            e.printStackTrace();
        }

        File file = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
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
