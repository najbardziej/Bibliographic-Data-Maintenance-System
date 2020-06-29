package com.bibliographicdatamaintenance.Controllers;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import com.bibliographicdatamaintenance.DataAccess.*;
import com.bibliographicdatamaintenance.Models.Bibliography;
import com.bibliographicdatamaintenance.Models.Book;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.converter.ShortStringConverter;
import javafx.scene.control.TableColumn.*;


public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button buttonOpenXml;

    @FXML
    private Button buttonCloseXml;

    @FXML
    private Button buttonSaveFile;

    @FXML
    private MenuButton buttonExportFile;

    @FXML
    private MenuItem menuButtonExportToBib;

    @FXML
    private MenuItem menuButtonExportToDocx;

    @FXML
    private MenuItem menuButtonExportToRtf;

    @FXML
    private MenuItem menuButtonExportToTxt;

    @FXML
    private Button deleteRow;

    @FXML
    private CheckBox selectAllCheckbox;

    @FXML
    private TableView<Book> tableViewBooks;

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
    private ListView<String> listOpenedXml;

    @FXML
    private boolean observableArrayList;

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Zaznaczanie wierszy
        TableView.TableViewSelectionModel selectionModel = tableViewBooks.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        // Określenie, która kolumna zawiera określony atrybut obiektu
        tableViewSelectColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableViewTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableViewAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableViewPublisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        tableViewYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        // Umożliwienie edycji kolumn w TableView
        tableViewBooks.setEditable(true);
        tableViewTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewAuthorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewPublisherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewYearColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ShortStringConverter()));
    }

    @FXML
    void openXml(ActionEvent event) throws IOException {
        // Utworzenie okna do wybierania plików (tylko .xml)
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML Files", "*.xml")
        );

        // Pokazanie okna i zwrócenie wybranego pliku do zmiennej
        List<File> fileList;
        try {
            fileList = fileChooser.showOpenMultipleDialog(borderPane.getScene().getWindow());
            for(File selectedFile : fileList) {
                try {
                    // Konwersja z pliku .xml do obiektu
                    InputStream inputStream = new FileInputStream(selectedFile);
                    Bibliography bibliography = XmlImportExport.deserializeXmlFile(inputStream);
                    inputStream.close();
                    for(Book book : bibliography.getBookList()) {
                        book.setCheckBox(new CheckBox());
                        book.setFilename(selectedFile.getName());
                        tableViewBooks.getItems().add(book);
                    }
                    listOpenedXml.getItems().add(selectedFile.getName());
                } catch(UnrecognizedPropertyException e) {
                    Alert notPickedFileAlert = new Alert(Alert.AlertType.ERROR,
                            "This file has incorrect format", ButtonType.OK);
                    notPickedFileAlert.showAndWait();
                }
            }
        } catch(NullPointerException e) {
            System.out.println("Nie wybrano pliku");
        }
    }


    @FXML
    void closeXml(ActionEvent event) {
        int indexOfFileToClose = listOpenedXml.getSelectionModel().getSelectedIndex();
        String fileToClose = listOpenedXml.getSelectionModel().getSelectedItem();
        if (fileToClose != null) {
            for (Book book : tableViewBooks.getItems()) {
                if (book.getFilename().equals(fileToClose)) {
                    Platform.runLater(() -> tableViewBooks.getItems().remove(book));
                }
            }
            listOpenedXml.getItems().remove(indexOfFileToClose);
        } else {
            Alert notPickedFileAlert = new Alert(Alert.AlertType.ERROR,
                    "You have not chosen any file to close", ButtonType.OK);
            notPickedFileAlert.showAndWait();
        }
    }

    File chooseOutputFile(String extension) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter;
        extFilter = new FileChooser.ExtensionFilter(extension + " Files", "*" + extension);
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("*" + extension);
        return fileChooser.showSaveDialog(borderPane.getScene().getWindow());
    }

    List<Book> createListOfSelectedBooks() {
        List<Book> selectedBooks = new ArrayList<>();
        for(Book book : tableViewBooks.getItems()) {
            if(book.getCheckBox().isSelected()) {
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }

    @FXML
    void saveXml(ActionEvent event) throws IOException {
        List<Book> bookListToSave = createListOfSelectedBooks();
        if (bookListToSave.size() > 0) {
            File file;
            try {
                file = chooseOutputFile(".xml");
                Bibliography bibliography = new Bibliography(bookListToSave);
                XmlImportExport.serializeToXmlFile(bibliography, file.getAbsolutePath());
            } catch(NullPointerException e) {
                System.out.println("Nie wybrano pliku");
            }
        } else {
            Alert notSelectAnyRowAlert = new Alert(Alert.AlertType.ERROR,
                    "You have not chosen any book. Use checkbox to select any book.", ButtonType.OK);
            notSelectAnyRowAlert.showAndWait();
        }
    }

    @FXML
    void exportToFile(ActionEvent event) {
        Bibliography bibliography = new Bibliography(createListOfSelectedBooks());
        if (bibliography.getBookList().size() > 0) {
            try {
                String extension = ((MenuItem)event.getSource()).getText();
                File file = chooseOutputFile(extension);
                IExporter exporter = ExporterFactory.getExporter(extension);
                bibliography.exportToFile(exporter, file.getAbsolutePath());
            } catch(NullPointerException e) {
                System.out.println("Nie wybrano pliku");
            }
        } else {
            Alert notSelectAnyRowAlert = new Alert(Alert.AlertType.ERROR,
                    "You have not chosen any book. Use checkbox to select any book.", ButtonType.OK);
            notSelectAnyRowAlert.showAndWait();
        }
    }

    @FXML
    void selectAllCheckboxes(ActionEvent event) {
        ObservableList<Book> productList;
        productList = tableViewBooks.getItems();
        if(selectAllCheckbox.isSelected()) {
            for(Book book : productList)
                book.getCheckBox().setSelected(true);
        } else {
            for(Book book : productList)
                book.getCheckBox().setSelected(false);
        }
    }

    @FXML
    void deleteSelectedRows(ActionEvent event) {
        int selectedRows = 0;
        for(Book book : tableViewBooks.getItems()) {
            if(book.getCheckBox().isSelected()) {
                Platform.runLater(() -> tableViewBooks.getItems().remove(book));
                selectedRows++;
            }
        }
        if (selectedRows == 0) {
            Alert notSelectAnyRowAlert = new Alert(Alert.AlertType.ERROR,
                    "You have not chosen any book. Use checkbox to select any book.", ButtonType.OK);
            notSelectAnyRowAlert.showAndWait();
        }
    }

    @FXML
    void addRecordToTable(ActionEvent event) {
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
            tableViewBooks.getItems().add(book);
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
    void showAbout(ActionEvent event) {
        Alert notPickedFileAlert = new Alert(Alert.AlertType.INFORMATION,
                "Bibliographic Data Maintenance System", ButtonType.OK);
        notPickedFileAlert.showAndWait();
    }

    public void changeTitleCellEvent(CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu title w obiekcie
        Book selectedObject = tableViewBooks.getSelectionModel().getSelectedItem();
        selectedObject.setTitle(cellEditEvent.getNewValue().toString());
    }

    public void changeAuthorCellEvent(CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu author w obiekcie
        Book selectedObject = tableViewBooks.getSelectionModel().getSelectedItem();
        selectedObject.setAuthor(cellEditEvent.getNewValue().toString());
    }

    public void changePublisherCellEvent(CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu publisher w obiekcie
        Book selectedObject = tableViewBooks.getSelectionModel().getSelectedItem();
        selectedObject.setPublisher(cellEditEvent.getNewValue().toString());
    }

    public void changeYearCellEvent(CellEditEvent cellEditEvent) {
        // Zmiana wartości atrybutu year w obiekcie
        Book selectedObject = tableViewBooks.getSelectionModel().getSelectedItem();
        selectedObject.setYear(Short.parseShort(cellEditEvent.getNewValue().toString()));
    }
}