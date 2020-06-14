package com.bibliographicdatamaintenance.DataAccess;

import java.io.*;
import java.util.List;

import com.bibliographicdatamaintenance.Models.Book;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class DocxExport {
    // from Java Object to docx file
    public void javaObjectToDocxFile(List<Book> bookList, String path) {
        File file = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error during open file");
            e.printStackTrace();
        }

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        try {
            for(Book book : bookList) {
                run.setText(book.getAuthor() + ", " + book.getTitle() + ", " + book.getPublisher() + ", " + book.getYear());
                run.addBreak();
            }
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
