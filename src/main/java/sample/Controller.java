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

import java.lang.reflect.*;

import java.io.*;
import java.util.Scanner;

public class Controller {

    @FXML
    private Button buttonOpenXml;

    @FXML
    private Button buttonSaveFile;

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
    private Tab modifyTab;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField publisherTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private Button modifySaveButoon;

    @FXML
    private Tab exportTab;

    @FXML
    private TextField fileNameTextBox;

    @FXML
    private ComboBox<?> extensionComboBox;

    @FXML
    private Button exportSaveButton;

    @FXML
    private ListView<String> listOpenedXml;

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
        xml.javaObjectToXmlFile(oj, file.getAbsolutePath());
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
