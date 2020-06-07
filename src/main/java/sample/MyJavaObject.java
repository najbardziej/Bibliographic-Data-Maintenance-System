package sample;

import javafx.beans.property.SimpleStringProperty;

public class MyJavaObject
{
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty publisher;
    private short year;
//    private CheckBox remark;

    public MyJavaObject(SimpleStringProperty title, SimpleStringProperty author, SimpleStringProperty publisher, short year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    public MyJavaObject(String newTitle, String newAuthor, String newPublisher, short newYear) {
        this.title = new SimpleStringProperty(newTitle);
        this.author = new SimpleStringProperty(newAuthor);
        this.publisher = new SimpleStringProperty(newPublisher);
        this.year = newYear;
    }

    public MyJavaObject() {
    }

    public String getTitle() { return title.get();}
    public String getAuthor() { return author.get();}
    public String getPublisher() { return publisher.get();}
    public short getYear() { return year;}
//    public CheckBox getRemark() {return remark;}

    public void setTitle(String newTitle) { this.title = new SimpleStringProperty(newTitle); }
    public void setAuthor(String newAuthor) { this.author = new SimpleStringProperty(newAuthor); }
    public void setPublisher(String newPublisher) { this.publisher = new SimpleStringProperty(newPublisher); }
    public void setYear(short newYear) { this.year = newYear; }
//    public void setRemark(CheckBox remark){ this.remark = remark; }

    @Override
    public String toString() {
        return (this.getTitle() + " " + this.getAuthor() + " " + this.getPublisher() + " " + this.getYear());
    }
}
