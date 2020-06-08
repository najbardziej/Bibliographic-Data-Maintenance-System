package com.bibliographicdatamaintenance.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.*;

@JacksonXmlRootElement(localName = "bibliography")
public class Bibliography {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "book")
    private List<Book> myList;

    public Bibliography(List<Book> myList) {
        this.myList = myList;
    }

    public Bibliography() {
    }

    public List<Book> getMyList() {
        return this.myList;
    }

    public void setMyList(List<Book> myList) {
        this.myList = myList;
    }
}
