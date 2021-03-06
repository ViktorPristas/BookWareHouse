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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;

public class TeacherMainSceneDBController {

    private final BookFxModel bookFxModel = new BookFxModel();
    private BookFxModel selectedBookFxModel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Book> booksTableView;

    @FXML
    private Button lendButton;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<BookFxModel> simpleTableView;

    @FXML
    private ComboBox<String> schoolClassComboBox;

    @FXML
    void initialize() {
        lendButton.setOnAction(eh -> {
            TeacherConfirmLendingSceneController controller = new TeacherConfirmLendingSceneController(selectedBookFxModel);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("TeacherConfirmLendingScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                Image logo = new Image(getClass().getResourceAsStream("LogoBWH.png"));
                stage.getIcons().add(logo);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                stage.setOnHidden(handler -> {
                    fillSimpleTable(true);
                });
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        simpleTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookFxModel>() {
            @Override
            public void changed(ObservableValue<? extends BookFxModel> observable, BookFxModel oldValue, BookFxModel newValue) {
                selectedBookFxModel = simpleTableView.getSelectionModel().getSelectedItem();
                if (selectedBookFxModel == null) {
                    lendButton.setDisable(true);
                } else {
                    lendButton.setDisable(false);
                }
            }
        });

        backButton.setOnAction(eha -> {
            TeacherMainSceneController controller = new TeacherMainSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("TeacherMainScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                Image logo = new Image(getClass().getResourceAsStream("LogoBWH.png"));
                stage.getIcons().add(logo);
                stage.show();

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

        TableColumn<BookFxModel, String> titleCol = new TableColumn<>("Názov");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        simpleTableView.getColumns().add(titleCol);

        TableColumn<BookFxModel, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        simpleTableView.getColumns().add(authorCol);

        TableColumn<BookFxModel, Integer> yearCol = new TableColumn<>("Rok vydania");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));
        simpleTableView.getColumns().add(yearCol);

        TableColumn<BookFxModel, Integer> schoolClassCol = new TableColumn<>("Ročník");
        schoolClassCol.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        simpleTableView.getColumns().add(schoolClassCol);

        TableColumn<BookFxModel, Integer> numberInStockCol = new TableColumn<>("Počet na sklade");
        numberInStockCol.setCellValueFactory(new PropertyValueFactory<>("numberInStock"));
        simpleTableView.getColumns().add(numberInStockCol);

        TableColumn<BookFxModel, Integer> numberOfUsedCol = new TableColumn<>("Počet rozdaných");
        numberOfUsedCol.setCellValueFactory(new PropertyValueFactory<>("numberOfUsed"));
        simpleTableView.getColumns().add(numberOfUsedCol);

        TableColumn<BookFxModel, Boolean> isUsedCol = new TableColumn<>("Používa sa");
        isUsedCol.setCellValueFactory(new PropertyValueFactory<>("isUsedString"));
        simpleTableView.getColumns().add(isUsedCol);

        simpleTableView.setItems(bookFxModel.getBooksModel());
    }
}
