package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.BookEdit;
import sk.upjs.ics.bookwarehouse.LostBook;
import sk.upjs.ics.bookwarehouse.fxmodels.BookEditFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.LostBookFxModel;

public class SupALogsSceneController {
    
    private final LostBookFxModel lostBookFxModel = new LostBookFxModel();
    
    private final BookEditFxModel bookEditFxModel = new BookEditFxModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<LostBookFxModel> lostBookTableView;

    @FXML
    private TableView<BookEditFxModel> editedBookTableView;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        backButton.setOnAction(eh -> {
            SupAMainSceneController controller = new SupAMainSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("SupAMainScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
        
        if (lostBookFxModel.getLostBooks().size() > 0) {
            lostBookFxModel.loadBooksToModel();
        }
        
        TableColumn<LostBookFxModel, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        lostBookTableView.getColumns().add(authorCol);
        
        TableColumn<LostBookFxModel, String> titleCol = new TableColumn<>("Nazov");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        lostBookTableView.getColumns().add(titleCol);
        
        TableColumn<LostBookFxModel, String> schoolClassCol = new TableColumn<>("Rocnik");
        schoolClassCol.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        lostBookTableView.getColumns().add(schoolClassCol);
        
        TableColumn<LostBookFxModel, String> adminNameCol = new TableColumn<>("Admin");
        adminNameCol.setCellValueFactory(new PropertyValueFactory<>("nameOfAdmin"));
        lostBookTableView.getColumns().add(adminNameCol);
        
        TableColumn<LostBookFxModel, String> teacherNameCol = new TableColumn<>("Ucitel meno");
        teacherNameCol.setCellValueFactory(new PropertyValueFactory<>("nameOfTeacher"));
        lostBookTableView.getColumns().add(teacherNameCol);
        
        TableColumn<LostBookFxModel, String> teacherSurnameCol = new TableColumn<>("Ucitel priezvisko");
        teacherSurnameCol.setCellValueFactory(new PropertyValueFactory<>("surnameOfTeacher"));
        lostBookTableView.getColumns().add(teacherSurnameCol);
        
        TableColumn<LostBookFxModel, LocalDateTime> dateCol = new TableColumn<>("Datum");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        lostBookTableView.getColumns().add(dateCol);
        
        TableColumn<LostBookFxModel, Integer> numberCol = new TableColumn<>("Pocet");
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        lostBookTableView.getColumns().add(numberCol);
        
        lostBookTableView.setItems(lostBookFxModel.getLostBooksFxModel());
        
        // second table
        
        if (bookEditFxModel.getBookEdits().size() > 0) {
            bookEditFxModel.loadBooksToModel();
        }
        
        TableColumn<BookEditFxModel, String> titleEditCol = new TableColumn<>("Nazov");
        titleEditCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        editedBookTableView.getColumns().add(titleEditCol);
        
        TableColumn<BookEditFxModel, String> authorEditCol = new TableColumn<>("Autor");
        authorEditCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        editedBookTableView.getColumns().add(authorEditCol);
        
       TableColumn<BookEditFxModel, String> schoolClassEditCol = new TableColumn<>("Rocnik");
        schoolClassEditCol.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        editedBookTableView.getColumns().add(schoolClassEditCol);
        
        TableColumn<BookEditFxModel, String> adminNameEditCol = new TableColumn<>("Admin");
        adminNameEditCol.setCellValueFactory(new PropertyValueFactory<>("nameOfAdmin"));
        editedBookTableView.getColumns().add(adminNameEditCol);
        
        TableColumn<BookEditFxModel, LocalDateTime> dateEditCol = new TableColumn<>("Datum");
        dateEditCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        editedBookTableView.getColumns().add(dateEditCol);
        
        TableColumn<BookEditFxModel, Integer> numberBeforeEditCol = new TableColumn<>("Pocet pred");
        numberBeforeEditCol.setCellValueFactory(new PropertyValueFactory<>("numbersBefore"));
        editedBookTableView.getColumns().add(numberBeforeEditCol);
        
        TableColumn<BookEditFxModel, Integer> numberAfterEditCol = new TableColumn<>("Pocet po");
        numberAfterEditCol.setCellValueFactory(new PropertyValueFactory<>("numbersAfter"));
        editedBookTableView.getColumns().add(numberAfterEditCol);
        
        editedBookTableView.setItems(bookEditFxModel.getBookEditsModel());
    }
}
