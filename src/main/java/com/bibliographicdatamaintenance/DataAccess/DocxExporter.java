package com.bibliographicdatamaintenance.DataAccess;

import java.io.*;
import java.util.List;

import com.bibliographicdatamaintenance.Models.Book;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class DocxExporter implements IExporter {
    public void exportToFile(List<Book> bookList, String path) {
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
                run.setText("Title: " + book.getTitle());
                run.addBreak();
                run.setText("Author: " + book.getAuthor());
                run.addBreak();
                run.setText("Publisher: " + book.getPublisher());
                run.addBreak();
                run.setText("Year: " + book.getYear());
                run.addBreak();
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
