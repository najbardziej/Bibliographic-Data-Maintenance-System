package com.bibliographicdatamaintenance.Models;

import com.bibliographicdatamaintenance.DataAccess.IExporter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.*;

@JacksonXmlRootElement(localName = "bibliography")
public class Bibliography {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "book")
    private List<Book> bookList;

    public Bibliography(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Bibliography() {
    }

    public List<Book> getBookList() {
        return this.bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void exportToFile(IExporter exporter, String path) {
        exporter.exportToFile(this.bookList, path);
    }
}
