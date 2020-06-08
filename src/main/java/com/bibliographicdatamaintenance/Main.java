package com.bibliographicdatamaintenance;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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

    public static void main(String[] args) throws IOException {
        // create new Bibliography
        List<MyJavaObject> mjo = Arrays.asList(
                new MyJavaObject("Tytuł", "Autor", "Wydawnictwo", (short) 2020),
                new MyJavaObject("Tytuł2", "Autor2", "Wydawnictwo2", (short) 2020),
                new MyJavaObject("Tytuł3", "Autor3", "Wydawnictwo3", (short) 2020)
        );
        Bibliography mys = new Bibliography(mjo);
        System.out.println(Arrays.toString(mys.getMyList().toArray()));

        // from Java Object to XML File, String
        try {
            XmlImportExport.javaObjectToXmlFile(mys,"mys.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = XmlImportExport.javaObjectToXmlString(mys);
        System.out.println(s);

        // from XML String to Java Object
        Bibliography xms = XmlImportExport.xmlStringToJavaObject(s, Bibliography.class);
        System.out.println(Arrays.toString(xms.getMyList().toArray()));

        launch();

        //MyJavaObject my = new MyJavaObject();
        //my.setTitle("Tytuł");
        //my.setAuthor("Autor");
        //my.setPublisher("Wydawnictwo");
        //my.setYear((short)2020);


        //XmlImportExport xml = new XmlImportExport();
        //xml.javaObjectToXmlFile(my);

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
