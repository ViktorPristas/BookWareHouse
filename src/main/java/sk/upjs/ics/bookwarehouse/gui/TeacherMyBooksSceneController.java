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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.business.DefaultBookLendingXLSManager;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.BookLendingFxModel;

public class TeacherMyBooksSceneController {

    private final BookLendingFxModel bookLendingFxModel = new BookLendingFxModel();
    private Teacher teacher = DaoFactory.INSTANCE.getTeacherDao().findById(UserIdentificationManager.getId());

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button backButton;

    @FXML
    private TableView<BookLendingFxModel> simpleTableView;

    @FXML
    private Button exportToExcelButton;

    @FXML
    void initialize() {
        exportToExcelButton.setOnAction(eh -> {
            DefaultBookLendingXLSManager.exportMyBookLendingsToXls(teacher);
        });
        
        backButton.setOnAction(eh -> {
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
                        Image logo = new Image(getClass().getResourceAsStream("LogoBWH.png"));
        stage.getIcons().add(logo);
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        if (bookLendingFxModel.getLendings().size() > 0) {
            bookLendingFxModel.loadBookLendingToModel(UserIdentificationManager.getId());
        }

        TableColumn<BookLendingFxModel, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        simpleTableView.getColumns().add(authorCol);

        TableColumn<BookLendingFxModel, String> titleCol = new TableColumn<>("Názov");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        simpleTableView.getColumns().add(titleCol);

        TableColumn<BookLendingFxModel, Integer> yearOfReturnCol = new TableColumn<>("Rok vrátenia");
        yearOfReturnCol.setCellValueFactory(new PropertyValueFactory<>("yearOfReturn"));
        simpleTableView.getColumns().add(yearOfReturnCol);

        TableColumn<BookLendingFxModel, Integer> lendedCol = new TableColumn<>("Počet rozdaných");
        lendedCol.setCellValueFactory(new PropertyValueFactory<>("lended"));
        simpleTableView.getColumns().add(lendedCol);

        TableColumn<BookLendingFxModel, Integer> returnedCol = new TableColumn<>("Počet vrátených");
        returnedCol.setCellValueFactory(new PropertyValueFactory<>("returned"));
        simpleTableView.getColumns().add(returnedCol);

        TableColumn<BookLendingFxModel, String> commentCol = new TableColumn<>("Komentár");
        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        simpleTableView.getColumns().add(commentCol);

        TableColumn<BookLendingFxModel, String> approvedCol = new TableColumn<>("Potvrdené");
        approvedCol.setCellValueFactory(new PropertyValueFactory<>("approvedString"));
        simpleTableView.getColumns().add(approvedCol);

        simpleTableView.setItems(bookLendingFxModel.getBookLendingsModel());

    }
}
