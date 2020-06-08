package com.bibliographicdatamaintenance.Controllers;

import java.io.*;
import java.util.List;

import com.bibliographicdatamaintenance.DataAccess.*;
import com.bibliographicdatamaintenance.Models.Bibliography;
import com.bibliographicdatamaintenance.Models.Book;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.converter.ShortStringConverter;
import java.lang.String;


public class MainController {

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
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, CheckBox> tableViewSelectColumn;

    @FXML
    private TableColumn<Book, String> tableViewTitleColumn;

    @FXML
    private TableColumn<Book, String> tableViewAuthorColumn;

    @FXML
    private TableColumn<Book, String> tableViewPublisherColumn;

    @FXML
    private TableColumn<Book, Short> tableViewYearColumn;

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

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
         // Zaznaczanie wierszy
        TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        // Dodanie przykładowych obiektów do listy TODO: usunąć
//        list = FXCollections.observableArrayList(
//                new Book(new CheckBox(), "t", "a", "w", (short)2)
//        );

       // Określenie, która kolumna zawiera określony atrybut obiektu
        tableViewSelectColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
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

        // Dodanie przykładowych obiektów do listy TODO: usunąć
//        list.add(new Book(new CheckBox(), "t", "a", "w", (short)2));
//        tableView.setItems(list);
    }

    @FXML
    void open_xml(ActionEvent event) throws IOException {
        // Utworzenie okna do wybierania plików (tylko .xml)
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML Files", "*.xml")
        );

        // Pokazanie okna i zwrócenie wybranego pliku do zmiennej
        List<File> fileList = fileChooser.showOpenMultipleDialog(buttonOpenXml.getScene().getWindow());
        if (fileList != null){
            for(File selectedFile : fileList){

                // Konwersja z pliku .xml do obiektu
                InputStream inputStream = new FileInputStream(selectedFile);
                String xml_line = XmlImportExport.xmlFileToString(inputStream);
                inputStream.close();
                try {
                    Bibliography bibliography = XmlImportExport.xmlStringToJavaObject(xml_line, Bibliography.class);
                    for(Book book : bibliography.getMyList()) {
                        book.setCheckBox(new CheckBox());
                        System.out.println(book.toString());
                        tableView.getItems().add(book);
                    }
                    listOpenedXml.getItems().add(selectedFile.getName());
                } catch(UnrecognizedPropertyException e) {
                    Alert notPickedFileAlert = new Alert(Alert.AlertType.ERROR,
                            "This file has incorrect format", ButtonType.OK);
                    notPickedFileAlert.showAndWait();
                }
            }
        }
    }

    @FXML
    void close_xml(ActionEvent event) {
        // TODO: obsługa wielu plików
        int index = listOpenedXml.getSelectionModel().getSelectedIndex();
        if(index!=-1) {
            buttonCloseXml.getScene().getWindow();
            listOpenedXml.getItems().remove(index);
            tableView.getItems().remove(index);
        } else {
            Alert notPickedFileAlert = new Alert(Alert.AlertType.ERROR,
                    "You have not chosen any file to remove", ButtonType.OK);
            notPickedFileAlert.showAndWait();
        }
    }

    @FXML
    void add_record(ActionEvent event) {
        String title = titleTextFieldToAdd.getText();
        String author = authorTextFieldToAdd.getText();
        String publisher = publisherTextFieldToAdd.getText();
        short year;
        try {
            year = Short.parseShort(yearTextFieldToAdd.getText());
            if(title.equals("") && author.equals("") && publisher.equals("") && year <= 0) {
                throw new RuntimeException("Cannot add empty fields");
            }
            Book book = new Book();
            book.setCheckBox(new CheckBox());
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setYear(year);
            tableView.getItems().add(book);
        } catch(NumberFormatException e) {
            Alert yearNotANumberAlert = new Alert(Alert.AlertType.ERROR,
                    "Wrong input: Year must be an integer value", ButtonType.OK);
            yearNotANumberAlert.showAndWait();
        } catch(RuntimeException e) {
            Alert emptyFieldsAlert = new Alert(Alert.AlertType.ERROR,
                    "Wrong input: At least one field must be filled", ButtonType.OK);
            emptyFieldsAlert.showAndWait();
        }
    }

    @FXML
    void delete_row(ActionEvent event) {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if(index!=-1) {
            tableView.getItems().remove(index);
        } else {
            Alert notPickedRecordAlert = new Alert(Alert.AlertType.ERROR,
                    "You have not chosen any record to remove", ButtonType.OK);
            notPickedRecordAlert.showAndWait();
        }
    }

    @FXML
    void exportToFile(ActionEvent event) {
        // TODO: obsługa wielu plików
        String extension = (String) extensionComboBox.getValue();
        ObservableList<Book> productsList;
        productsList = tableView.getItems();
        Book book = productsList.get(0);  // TODO: zmiana na czekboksy;)

        // Utworzenie okna do zapisywania pliku
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter;
        extFilter = new FileChooser.ExtensionFilter(extension + " Files", "*" + extension);
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("*" + extension);
        File file = fileChooser.showSaveDialog(exportSaveButton.getScene().getWindow());

        // Zapisywanie pliku w określonym formacie
        if(extension.equals(".docx")) {
            DocxExport docx = new DocxExport();
            docx.javaObjectToDocxFile(book, file.getAbsolutePath());
        } else if(extension.equals(".bib")) {
            BibTeXExport rtf = new BibTeXExport();
            rtf.javaObjectToBiBTeXFile(book, file.getAbsolutePath());
        } else if(extension.equals(".txt")) {
            TxtExport txt = new TxtExport();
            txt.javaObjectToTxtFile(book, file.getAbsolutePath());
        } else if(extension.equals(".rtf")) {
            RtfExport rtf = new RtfExport();
            rtf.javaObjectToRtfFile(book, file.getAbsolutePath());
        }
    }

    @FXML
    void save_xml(ActionEvent event) throws IOException {
        // TODO: obsługa wielu plików
        ObservableList<Book> productsList;
        productsList = tableView.getSelectionModel().getSelectedItems();
        // getting selected object
        Book book = productsList.get(0);  // TODO: zmiana na czekboksy;)

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(buttonSaveFile.getScene().getWindow());

        //Book book = tableView.getSelectionModel().getSelectedItem();
        XmlImportExport xml = new XmlImportExport();
        xml.javaObjectToXmlFile(book, file.getAbsolutePath());
    }

    public void changeTitleCellEvent(TableColumn.CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu title w obiekcie
        Book selectedObject = tableView.getSelectionModel().getSelectedItem();
        selectedObject.setTitle(cellEditEvent.getNewValue().toString());
    }

    public void changeAuthorCellEvent(TableColumn.CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu author w obiekcie
        Book selectedObject = tableView.getSelectionModel().getSelectedItem();
        selectedObject.setAuthor(cellEditEvent.getNewValue().toString());
    }

    public void changePublisherCellEvent(TableColumn.CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu publisher w obiekcie
        Book selectedObject = tableView.getSelectionModel().getSelectedItem();
        selectedObject.setPublisher(cellEditEvent.getNewValue().toString());
    }

    public void changeYearCellEvent(TableColumn.CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu year w obiekcie
        Book selectedObject = tableView.getSelectionModel().getSelectedItem();
        selectedObject.setYear(Short.parseShort(cellEditEvent.getNewValue().toString()));
    }

}
