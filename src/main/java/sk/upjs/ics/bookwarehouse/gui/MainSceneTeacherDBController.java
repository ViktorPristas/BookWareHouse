package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;

public class MainSceneTeacherDBController {

    private final BookFxModel bookFxModel = new BookFxModel();
    private BookFxModel actualBookFxModel;

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
            ConfirmLendingSceneController controller = new ConfirmLendingSceneController(actualBookFxModel);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ConfirmLendingScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        backButton.setOnAction(eha -> {
            MainSceneTeacherController controller = new MainSceneTeacherController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("MainSceneTeacher.fxml"));
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

        if (bookFxModel.getBooks().size() > 0) {
            bookFxModel.loadBooksToModel();
        }

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
        yearCol.setCellValueFactory(new PropertyValueFactory<>("numberInStock"));
        simpleTableView.getColumns().add(numberInStockCol);

        TableColumn<BookFxModel, Integer> numberOfUsedCol = new TableColumn<>("Pocet rozdanych");
        numberOfUsedCol.setCellValueFactory(new PropertyValueFactory<>("numberOfUsed"));
        simpleTableView.getColumns().add(numberOfUsedCol);

        simpleTableView.setItems(bookFxModel.getBooksModel());

        // naplnenie combobxu
        schoolClassComboBox.getItems().addAll("<Všetko>", "1", "2", "3", "4", "5", "6", "7", "8", "9", "I. G", "II. G", "III. G", "IV. G");

        searchButton.setOnAction(eh -> {
            String selectedClass = schoolClassComboBox.getValue();

            if (bookFxModel.getBooks().size() > 0) {
                bookFxModel.loadFilteredBooksToModel(selectedClass);
            }

        });

    }
}
