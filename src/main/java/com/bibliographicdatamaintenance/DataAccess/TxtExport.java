package com.bibliographicdatamaintenance.DataAccess;

import com.bibliographicdatamaintenance.Models.Book;

import java.io.*;
import java.util.List;

public class TxtExport {
    // from Java Object to bib file
    public void javaObjectToTxtFile(List<Book> bookList, String path) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            for(Book book : bookList) {
                bw.write(book.getAuthor() + ", " + book.getTitle() + ", " + book.getPublisher() + ", " + book.getYear());
                bw.write("\n");
            }
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
