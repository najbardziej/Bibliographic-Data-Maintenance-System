package com.bibliographicdatamaintenance;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.*;

@JacksonXmlRootElement(localName = "bibliography")
public class Bibliography {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "book")
    private List<MyJavaObject> myList;

    public Bibliography(List<MyJavaObject> myList) {
        this.myList = myList;
    }

    public Bibliography() {
    }

    public List<MyJavaObject> getMyList() {
        return this.myList;
    }

    public void setMyList(List<MyJavaObject> myList) {
        this.myList = myList;
    }
}
