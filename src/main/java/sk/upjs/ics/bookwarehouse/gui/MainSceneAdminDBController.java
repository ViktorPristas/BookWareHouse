package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;

public class MainSceneAdminDBController {
    
     private final BookFxModel bookFxModel = new BookFxModel();

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
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        // no new controller created for the book editation..
        // might cause problems when fxModel needed
        editBookButton.setOnAction(eh -> {

            NewBookSceneController controller = new NewBookSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("EditBookScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse: Upraviť knihu");
                stage.initModality(Modality.APPLICATION_MODAL);
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
    }
}
