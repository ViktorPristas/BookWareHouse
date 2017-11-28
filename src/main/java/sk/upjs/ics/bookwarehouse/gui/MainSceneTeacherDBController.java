package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;
import sk.upjs.ics.bookwarehouse.storage.BookDao;

public class MainSceneTeacherDBController {
    
    private final BookDao bookDao = DaoFactory.INSTANCE.getBookDao();    
    private ObservableList<Book> booksModel;
    private BookFxModel bookFxModel = new BookFxModel();
    
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
    private ListView<Book> booksListView;


    @FXML
    void initialize() {
        lendButton.setOnAction(eh -> {
            ConfirmLendingSceneController controller = new ConfirmLendingSceneController();
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
        
        List<Book> books = bookDao.getAll();
        System.out.println("nic" + books.size());
        bookFxModel.setBooks(books);
        booksModel = FXCollections.observableArrayList(books);
        booksListView.setItems(booksModel);
    }
}
