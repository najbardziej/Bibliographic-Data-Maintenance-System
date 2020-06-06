package sample;

import javafx.beans.property.SimpleStringProperty;

public class MyJavaObject
{
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty publisher;
    private short year;
//    private CheckBox remark;

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
}
