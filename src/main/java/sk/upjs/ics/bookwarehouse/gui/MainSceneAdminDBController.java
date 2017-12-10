package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;

public class MainSceneAdminDBController {

    private final BookFxModel bookFxModel = new BookFxModel();
    private BookFxModel selectedBookFxModel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button editBookButton;

    @FXML
    private Button addNewBookButton;

    @FXML
    private TableView<BookFxModel> simpleTableView;

    @FXML
    private ComboBox<String> schoolClassComboBox;

    @FXML
    void initialize() {
        backButton.setOnAction(eha -> {
            MainSceneAdminController controller = new MainSceneAdminController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("MainSceneAdmin.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        addNewBookButton.setOnAction(eh -> {

            NewBookSceneController controller = new NewBookSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("NewBookScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                

                // toto sa vykona az po zatvoreni okna
                stage.setOnHidden(handler -> {
                    fillSimpleTable(true);
                });
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        editBookButton.setOnAction(eh -> {

            AdminEditBookSceneController controller = new AdminEditBookSceneController(selectedBookFxModel);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("EditBookScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("BookWareHouse: Upraviť knihu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                

                // toto sa vykona az po zatvoreni okna
                stage.setOnHidden(handler -> {
                    fillSimpleTable(true);
                });
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        fillSimpleTable(false);

        // naplnenie combobxu
        schoolClassComboBox.getItems().addAll("<Všetko>", "1", "2", "3", "4", "5", "6", "7", "8", "9", "I. G", "II. G", "III. G", "IV. G");

        searchButton.setOnAction(eh -> {
            String selectedClass = schoolClassComboBox.getValue();

            if (bookFxModel.getBooks().size() > 0) {
                bookFxModel.loadFilteredBooksToModel(selectedClass);
            }

        });
    }
    
    public void fillSimpleTable(boolean b) {
        simpleTableView.getItems().clear();
        simpleTableView.getColumns().clear();
        if (bookFxModel.getBooks().size() > 0 || b) {
            bookFxModel.loadBooksToModel();
        }

        simpleTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookFxModel>() {
            @Override
            public void changed(ObservableValue<? extends BookFxModel> observable, BookFxModel oldValue, BookFxModel newValue) {
                selectedBookFxModel = simpleTableView.getSelectionModel().getSelectedItem();
                if (selectedBookFxModel == null) {
                    editBookButton.setDisable(true);
                } else {
                    editBookButton.setDisable(false);
                }
            }
        });

        TableColumn<BookFxModel, String> titleCol = new TableColumn<>("Nazov");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        simpleTableView.getColumns().add(titleCol);

        TableColumn<BookFxModel, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        simpleTableView.getColumns().add(authorCol);

        TableColumn<BookFxModel, Integer> yearCol = new TableColumn<>("Rok vydania");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));
        simpleTableView.getColumns().add(yearCol);

        TableColumn<BookFxModel, Integer> schoolClassCol = new TableColumn<>("Rocnik");
        schoolClassCol.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        simpleTableView.getColumns().add(schoolClassCol);

        TableColumn<BookFxModel, Integer> numberInStockCol = new TableColumn<>("Pocet na sklade");
        numberInStockCol.setCellValueFactory(new PropertyValueFactory<>("numberInStock"));
        simpleTableView.getColumns().add(numberInStockCol);

        TableColumn<BookFxModel, Integer> numberOfUsedCol = new TableColumn<>("Pocet rozdanych");
        numberOfUsedCol.setCellValueFactory(new PropertyValueFactory<>("numberOfUsed"));
        simpleTableView.getColumns().add(numberOfUsedCol);

        TableColumn<BookFxModel, Boolean> isUsedCol = new TableColumn<>("Používa sa");
        isUsedCol.setCellValueFactory(new PropertyValueFactory<>("isUsedString"));
        simpleTableView.getColumns().add(isUsedCol);

        simpleTableView.setItems(bookFxModel.getBooksModel());
    }
}
