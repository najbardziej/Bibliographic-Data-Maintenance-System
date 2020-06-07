package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("sample"));
        stage.setTitle("Bibliographic Data Maintenance");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

        //MyJavaObject my = new MyJavaObject();
        //my.setTitle("Tytuł");
        //my.setAuthor("Autor");
        //my.setPublisher("Wydawnictwo");
        //my.setYear((short)2020);


//        XmlImportExport xml = new XmlImportExport();
//        xml.javaObjectToXmlFile(my);

        //BibTeXExport bib = new BibTeXExport();
        //bib.javaObjectToBiBTeXFile(my);

        //RtfExport rtf = new RtfExport();
        //rtf.javaObjectToRtfFile(my);

        //TxtExport txt = new TxtExport();
        //txt.javaObjectToTxtFile(my);

        //DocxExport docx = new DocxExport();
        //docx.javaObjectToDocxFile(my);


    }
}
