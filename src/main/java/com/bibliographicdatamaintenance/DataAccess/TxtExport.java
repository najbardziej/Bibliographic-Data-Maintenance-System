package com.bibliographicdatamaintenance.DataAccess;

import com.bibliographicdatamaintenance.Models.Book;

import java.io.*;

public class TxtExport {
    // from Java Object to bib file
    public void javaObjectToTxtFile(Book book, String path) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write("Title: " + book.getTitle() + '\n');
            bw.write("Author: " + book.getAuthor() + '\n');
            bw.write("Publisher: " + book.getPublisher() + '\n');
            bw.write("Year: " + book.getYear() + '\n');
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
