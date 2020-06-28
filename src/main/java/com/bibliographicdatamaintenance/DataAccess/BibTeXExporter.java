package com.bibliographicdatamaintenance.DataAccess;

import com.bibliographicdatamaintenance.Models.Book;

import java.io.*;
import java.util.List;

public class BibTeXExporter implements IExporter {
    public void exportToFile(List<Book> bookList, String path) {
        BufferedWriter bw = null;
        int bibTexKey = 1;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            for(Book book : bookList) {
                bw.write("@book{" + bibTexKey + ",\n");
                bw.write("\tauthor = \"" + book.getAuthor() + "\",\n");
                bw.write("\ttitle = \"" + book.getTitle() + "\",\n");
                bw.write("\tpublisher = \"" + book.getPublisher() + "\",\n");
                bw.write("\tyear = \"" + book.getYear() + "\"\n");
                bw.write("}\n\n");
                bibTexKey++;
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
