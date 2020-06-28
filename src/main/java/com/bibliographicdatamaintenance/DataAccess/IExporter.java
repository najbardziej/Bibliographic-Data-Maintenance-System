package com.bibliographicdatamaintenance.DataAccess;

import com.bibliographicdatamaintenance.Models.Book;

import java.util.List;

public interface IExporter {
    public void exportToFile(List<Book> bookList, String path);
}
