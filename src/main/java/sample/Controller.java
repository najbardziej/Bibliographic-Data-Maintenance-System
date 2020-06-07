package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;
import javafx.util.converter.ShortStringConverter;
import java.lang.String;

import java.lang.reflect.*;

import java.io.*;
import java.util.Scanner;

public class Controller {

    @FXML
    private Button buttonOpenXml;

    @FXML
    private Button buttonCloseXml;

    @FXML
    private Button buttonSaveFile;

    @FXML
    private Button deleteRow;

    @FXML
    private CheckBox selectAllCheckbox;

    @FXML
    private TableView<MyJavaObject> tableView;

    @FXML
    private TableColumn<MyJavaObject, String>tableViewSelectColumn;

    @FXML
    private TableColumn<MyJavaObject, String> tableViewTitleColumn;

    @FXML
    private TableColumn<MyJavaObject, String> tableViewAuthorColumn;

    @FXML
    private TableColumn<MyJavaObject, String> tableViewPublisherColumn;

    @FXML
    private TableColumn<MyJavaObject, Short> tableViewYearColumn;

    @FXML
    private TextField titleTextFieldToAdd;

    @FXML
    private TextField authorTextFieldToAdd;

    @FXML
    private TextField publisherTextFieldToAdd;

    @FXML
    private TextField yearTextFieldToAdd;

    @FXML
    private TextField fileNameTextBox;

    @FXML
    private ComboBox<?> extensionComboBox;

    @FXML
    private Button exportSaveButton;

    @FXML
    private ListView<String> listOpenedXml;

    @FXML
    private boolean observableArrayList;

    @FXML
    public void initialize() {
        // Określenie, która kolumna zawiera określony atrybut obiektu
        tableViewTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableViewAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableViewPublisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        tableViewYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        // Umożliwienie edycji kolumn w TableView
        tableView.setEditable(true);
        tableViewTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewAuthorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewPublisherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewYearColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ShortStringConverter()));
    }

    @FXML
    void open_xml(ActionEvent event) throws IOException {
        // Utworzenie okna do wybierania plików (tylko .xml)
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML Files", "*.xml")
        );

        // Pokazanie okna i zwrócenie wybranego pliku do zmiennej
        File selectedFile = fileChooser.showOpenDialog(buttonOpenXml.getScene().getWindow());
        if (selectedFile != null) {
            // Dodanie nazwy pliku do ListView
            listOpenedXml.getItems().add(selectedFile.getName());


            // Konwersja z pliku .xml do obiektu
            XmlImportExport xml = new XmlImportExport();
            InputStream inputStream = new FileInputStream(selectedFile);
            String xml_line = xml.xmlFileToString(inputStream);
            inputStream.close();
            MyJavaObject my = xml.xmlStringToJavaObject(xml_line);

            // Dodanie obiketu do TableView
            tableView.getItems().add(my);

            System.out.println(xml_line);
        }
    }
    @FXML
    void close_xml(ActionEvent event) throws IOException{
        int index = listOpenedXml.getSelectionModel().getSelectedIndex();
        if(index!=-1){
            buttonCloseXml.getScene().getWindow();
            listOpenedXml.getItems().remove(index);
            tableView.getItems().remove(index);
        }
    }

    @FXML
    void add_record(ActionEvent event) throws IOException{
        String title = titleTextFieldToAdd.getText();
        String author = authorTextFieldToAdd.getText();
        String publisher = publisherTextFieldToAdd.getText();
        short year = Short.parseShort(yearTextFieldToAdd.getText());
        if (!title.equals("") && !author.equals("") && !publisher.equals("") && year>0) {
            MyJavaObject my = new MyJavaObject();
            my.setTitle(title);
            my.setAuthor(author);
            my.setPublisher(publisher);
            my.setYear(year);
            tableView.getItems().add(my);
        }
    }

    @FXML
    void delete_row(ActionEvent event) throws IOException{
        int index = tableView.getSelectionModel().getSelectedIndex();
        if(index!=-1){
            tableView.getItems().remove(index);
        }
    }

    @FXML
    void exportToFile(ActionEvent event) throws IOException{
        String extension = (String) extensionComboBox.getValue();
        ObservableList<MyJavaObject> productsList;
        productsList = tableView.getItems();
        MyJavaObject my = productsList.get(0);  //zmiana na czekboksy;)
        FileChooser fileChooser = new FileChooser();
        if(extension.equals(".docx")){
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("docx Files", "*.docx");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(exportSaveButton.getScene().getWindow());
            DocxExport docx = new DocxExport();
            docx.javaObjectToDocxFile(my, file.getAbsolutePath());
        }
        else if (extension.equals(".bib")){
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("bib Files", "*.bib");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(exportSaveButton.getScene().getWindow());
            BibTeXExport rtf = new BibTeXExport();
            rtf.javaObjectToBiBTeXFile(my, file.getAbsolutePath());
        }
        else{
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("rtf Files", "*.rtf");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(exportSaveButton.getScene().getWindow());
            RtfExport rtf = new RtfExport();
            rtf.javaObjectToRtfFile(my, file.getAbsolutePath());
        }
    }

    @FXML
    void save_xml(ActionEvent event) throws IOException {
        ObservableList<MyJavaObject> productsList;
        productsList = tableView.getSelectionModel().getSelectedItems();
        // getting selected object
        MyJavaObject obj = productsList.get(0);

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(buttonSaveFile.getScene().getWindow());

        //MyJavaObject obj = tableView.getSelectionModel().getSelectedItem();
        XmlImportExport xml = new XmlImportExport();
        xml.javaObjectToXmlFile(obj, file.getAbsolutePath());
    }

    public void changeTitleCellEvent(TableColumn.CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu title w obiekcie
        MyJavaObject selectedObject = tableView.getSelectionModel().getSelectedItem();
        selectedObject.setTitle(cellEditEvent.getNewValue().toString());
    }

    public void changeAuthorCellEvent(TableColumn.CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu author w obiekcie
        MyJavaObject selectedObject = tableView.getSelectionModel().getSelectedItem();
        selectedObject.setAuthor(cellEditEvent.getNewValue().toString());
    }

    public void changePublisherCellEvent(TableColumn.CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu publisher w obiekcie
        MyJavaObject selectedObject = tableView.getSelectionModel().getSelectedItem();
        selectedObject.setPublisher(cellEditEvent.getNewValue().toString());
    }

    public void changeYearCellEvent(TableColumn.CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu year w obiekcie
        MyJavaObject selectedObject = tableView.getSelectionModel().getSelectedItem();
        selectedObject.setYear(Short.parseShort(cellEditEvent.getNewValue().toString()));
    }

}
