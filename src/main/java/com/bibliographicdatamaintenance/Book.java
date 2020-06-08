package com.bibliographicdatamaintenance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

@JsonIgnoreProperties(value = {"checkBox"})
public class Book
{
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty publisher;
    private short year;
    private CheckBox checkBox;

    public Book(SimpleStringProperty title, SimpleStringProperty author, SimpleStringProperty publisher, short year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.checkBox = new CheckBox();
    }

    public Book(String newTitle, String newAuthor, String newPublisher, short newYear) {
        this.title = new SimpleStringProperty(newTitle);
        this.author = new SimpleStringProperty(newAuthor);
        this.publisher = new SimpleStringProperty(newPublisher);
        this.year = newYear;
        this.checkBox = new CheckBox();
    }

    public Book() {
    }

    public String getTitle() { return title.get();}
    public String getAuthor() { return author.get();}
    public String getPublisher() { return publisher.get();}
    public short getYear() { return year;}
    public CheckBox getCheckBox() { return checkBox;}

    public void setTitle(String newTitle) { this.title = new SimpleStringProperty(newTitle); }
    public void setAuthor(String newAuthor) { this.author = new SimpleStringProperty(newAuthor); }
    public void setPublisher(String newPublisher) { this.publisher = new SimpleStringProperty(newPublisher); }
    public void setYear(short newYear) { this.year = newYear; }
    public void setCheckBox(CheckBox checkBox) { this.checkBox = checkBox; }

    @Override
    public String toString() {
        return (this.getTitle() + " " + this.getAuthor() + " " + this.getPublisher() + " " + this.getYear());
    }
}
