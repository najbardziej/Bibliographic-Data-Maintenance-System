package com.bibliographicdatamaintenance.DataAccess;

import com.bibliographicdatamaintenance.Models.Book;

import java.io.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;


public class RtfExport {
    // from Java Object to rtf file
    public void javaObjectToRtfFile(Book book, String path) {
        RTFEditorKit rtfParser = new RTFEditorKit();
        Document document = rtfParser.createDefaultDocument();
        try {
            document.insertString(document.getLength(), "Title: " + book.getTitle() + '\n', null);
            document.insertString(document.getLength(), "Author: " + book.getAuthor() + '\n', null);
            document.insertString(document.getLength(), "Publisher: " + book.getPublisher() + '\n', null);
            document.insertString(document.getLength(), "Year: " + book.getYear() + '\n', null);
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
