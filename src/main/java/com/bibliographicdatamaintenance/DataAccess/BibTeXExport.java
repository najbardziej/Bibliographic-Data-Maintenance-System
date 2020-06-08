package com.bibliographicdatamaintenance.DataAccess;

import com.bibliographicdatamaintenance.Models.Book;

import java.io.*;

public class BibTeXExport {
    // from Java Object to bib file
    public void javaObjectToBiBTeXFile(Book book, String path) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write("@Book{\n");
            bw.write("\tauthor =    " + book.getAuthor() + ",\n");
            bw.write("\ttitle =     " + book.getTitle() + ",\n");
            bw.write("\tpublisher = " + book.getPublisher() + ",\n");
            bw.write("\tyear =      " + book.getYear() + "\n");
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
