package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException {
        MyJavaObject my = new MyJavaObject();
        my.setTitle("Tytuł");
        my.setAuthor("Autor");
        my.setPublisher("Wydawnictwo");
        my.setYear((short)2020);

        XmlImportExport xml = new XmlImportExport();
        xml.javaObjectToXmlFile(my);

        BibTeXExport bib = new BibTeXExport();
        bib.javaObjectToBiBTeXFile(my);

        RtfExport rtf = new RtfExport();
        rtf.javaObjectToRtfFile(my);

        TxtExport txt = new TxtExport();
        txt.javaObjectToTxtFile(my);

        DocxExport docx = new DocxExport();
        docx.javaObjectToDocxFile(my);

    }
}
