package com.bibliographicdatamaintenance.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

@JsonIgnoreProperties(value = {"checkBox", "filename"})
public class Book
{
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty publisher;
    private short year;
    private CheckBox checkBox;
    private SimpleStringProperty filename;

    public Book(SimpleStringProperty title, SimpleStringProperty author, SimpleStringProperty publisher, short year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.checkBox = new CheckBox();
        this.filename = new SimpleStringProperty("");
    }

    public Book(String newTitle, String newAuthor, String newPublisher, short newYear) {
        this.title = new SimpleStringProperty(newTitle);
        this.author = new SimpleStringProperty(newAuthor);
        this.publisher = new SimpleStringProperty(newPublisher);
        this.year = newYear;
        this.checkBox = new CheckBox();
        this.filename = new SimpleStringProperty("");
    }

    public Book() {
    }

    public String getTitle() { return title.get();}
    public String getAuthor() { return author.get();}
    public String getPublisher() { return publisher.get();}
    public short getYear() { return year;}
    public CheckBox getCheckBox() { return checkBox;}
    public String getFilename() { return filename.get();}

    public void setTitle(String newTitle) { this.title = new SimpleStringProperty(newTitle); }
    public void setAuthor(String newAuthor) { this.author = new SimpleStringProperty(newAuthor); }
    public void setPublisher(String newPublisher) { this.publisher = new SimpleStringProperty(newPublisher); }
    public void setYear(short newYear) { this.year = newYear; }
    public void setCheckBox(CheckBox checkBox) { this.checkBox = checkBox; }
    public void setFilename(String filename) { this.filename = new SimpleStringProperty(filename);}

    @Override
    public String toString() {
        try {
            return (this.getTitle() + " " + this.getAuthor() + " " + this.getPublisher() + " " + this.getYear() + " " + this.getFilename());
        } catch(Exception NullPointerException) {
            return (this.getTitle() + " " + this.getAuthor() + " " + this.getPublisher() + " " + this.getYear());
        }
    }

}