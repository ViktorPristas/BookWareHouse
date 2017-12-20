package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.BookEdit;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.LostBook;
import sk.upjs.ics.bookwarehouse.ManagerFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.business.BookLendingManager;
import sk.upjs.ics.bookwarehouse.fxmodels.BookEditFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.BookLendingFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.LostBookFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.TeacherFxModel;

public class SupALogsSceneController {

    private final LostBookFxModel lostBookFxModel = new LostBookFxModel();
    private final TeacherFxModel teacherFxModel = new TeacherFxModel();
    private final BookEditFxModel bookEditFxModel = new BookEditFxModel();
    private final BookLendingFxModel bookLendingFxModel = new BookLendingFxModel();
    private TeacherFxModel selectedTeacherFxModel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<LostBookFxModel> lostBookTableView;

    @FXML
    private TableView<BookEditFxModel> editedBookTableView;

    @FXML
    private TableView<BookLendingFxModel> requestsTableView;

    @FXML
    private ComboBox<String> teacherComboBox;

    @FXML
    private Button searchButton;

    @FXML
    private Label numberLabel;

    @FXML
    private Button deleteForTeacherButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        numberLabel.setText("" + bookLendingFxModel.getNumberOfLostForTeacher());

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
                Image logo = new Image(getClass().getResourceAsStream("LogoBWH.png"));
                stage.getIcons().add(logo);
                stage.show();

            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        deleteForTeacherButton.setOnAction(eh -> {
            AlertBoxDeleteTeacherLostBooksController controller = new AlertBoxDeleteTeacherLostBooksController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("AlertBoxDeleteTeacherLostBooks.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                Image logo = new Image(getClass().getResourceAsStream("LogoBWH.png"));
                stage.getIcons().add(logo);
                stage.show();

                stage.setOnHidden(eha -> {
                    BookLendingManager manager = ManagerFactory.INSTANCE.getBookLendingManager();
                    Teacher t = null;
                    List<Teacher> list = DaoFactory.INSTANCE.getTeacherDao().getAll();
                    String selectedTeacher = teacherComboBox.getValue();
                    for (Teacher teacher : list) {
                        String formatedNameOfTeacher = teacher.getName() + " " + teacher.getSurname();
                        if (formatedNameOfTeacher.equals(selectedTeacher)) {
                            t = teacher;
                            break;
                        }
                    }
                    if (t != null) {
                        manager.deleteByTeacher(t);
                        fillLostBookTable(true);
                        fillRequestsTable(true);
                    }
                });
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        fillLostBookTable(false);

        fillEditedBookTable();

        fillComboBox();

        fillRequestsTable(false);

    }

    public void fillComboBox() {
        List<String> nameOfTeachers = teacherFxModel.getTeacherList();

        nameOfTeachers.add(
                0, "<Všetko>");
        teacherComboBox.getItems()
                .addAll(nameOfTeachers);

        searchButton.setOnAction(eh -> {
            String selectedTeacher = teacherComboBox.getValue();
            if (bookLendingFxModel.getLendings().size() > 0) {
                bookLendingFxModel.loadFilteredTeachersToModel(selectedTeacher);

            }
            numberLabel.setText("" + bookLendingFxModel.getNumberOfLostForTeacher());
        }
        );
    }

    public void fillRequestsTable(boolean b) {
        requestsTableView.getItems().clear();
        requestsTableView.getColumns().clear();
        if (bookLendingFxModel.getLendings().size() > 0 || b) {
            bookLendingFxModel.loadLendingForAdminToModel();
        }

        TableColumn<BookLendingFxModel, String> authorLendingCol = new TableColumn<>("Autor");
        authorLendingCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        requestsTableView.getColumns().add(authorLendingCol);

        TableColumn<BookLendingFxModel, String> titleLendingCol = new TableColumn<>("Názov");
        titleLendingCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        requestsTableView.getColumns().add(titleLendingCol);

        TableColumn<BookLendingFxModel, String> teacherNameLendingCol = new TableColumn<>("Meno učiteľa");
        teacherNameLendingCol.setCellValueFactory(new PropertyValueFactory<>("nameOfTeacher"));
        requestsTableView.getColumns().add(teacherNameLendingCol);

        TableColumn<BookLendingFxModel, String> teacherSurnameLendingCol = new TableColumn<>("Priezvisko učiteľa");
        teacherSurnameLendingCol.setCellValueFactory(new PropertyValueFactory<>("surnameOfTeacher"));
        requestsTableView.getColumns().add(teacherSurnameLendingCol);

        TableColumn<BookLendingFxModel, Integer> yearOfReturnLendingCol = new TableColumn<>("Rok vrátenia");
        yearOfReturnLendingCol.setCellValueFactory(new PropertyValueFactory<>("yearOfReturn"));
        requestsTableView.getColumns().add(yearOfReturnLendingCol);

        TableColumn<BookLendingFxModel, Integer> lendedCol = new TableColumn<>("Počet rozdaných");
        lendedCol.setCellValueFactory(new PropertyValueFactory<>("lended"));
        requestsTableView.getColumns().add(lendedCol);

        TableColumn<BookLendingFxModel, Integer> returnedCol = new TableColumn<>("Počet vrátených");
        returnedCol.setCellValueFactory(new PropertyValueFactory<>("returned"));
        requestsTableView.getColumns().add(returnedCol);

        TableColumn<BookLendingFxModel, String> commentCol = new TableColumn<>("Komentár");
        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        requestsTableView.getColumns().add(commentCol);

        TableColumn<BookLendingFxModel, Integer> lostCol = new TableColumn<>("Počet stratených");
        lostCol.setCellValueFactory(new PropertyValueFactory<>("lost"));
        requestsTableView.getColumns().add(lostCol);

        TableColumn<BookLendingFxModel, String> approvedCol = new TableColumn<>("Potvrdené");
        approvedCol.setCellValueFactory(new PropertyValueFactory<>("approvedString"));
        requestsTableView.getColumns().add(approvedCol);

        requestsTableView.setItems(bookLendingFxModel.getBookLendingsModel());
    }

    public void fillEditedBookTable() {
        if (bookEditFxModel.getBookEdits().size() > 0) {
            bookEditFxModel.loadBooksToModel();
        }

        TableColumn<BookEditFxModel, String> titleEditCol = new TableColumn<>("Názov");
        titleEditCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        editedBookTableView.getColumns().add(titleEditCol);

        TableColumn<BookEditFxModel, String> authorEditCol = new TableColumn<>("Autor");
        authorEditCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        editedBookTableView.getColumns().add(authorEditCol);

        TableColumn<BookEditFxModel, String> schoolClassEditCol = new TableColumn<>("Ročník");
        schoolClassEditCol.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        editedBookTableView.getColumns().add(schoolClassEditCol);

        TableColumn<BookEditFxModel, String> adminNameEditCol = new TableColumn<>("Admin");
        adminNameEditCol.setCellValueFactory(new PropertyValueFactory<>("nameOfAdmin"));
        editedBookTableView.getColumns().add(adminNameEditCol);

        TableColumn<BookEditFxModel, LocalDateTime> dateEditCol = new TableColumn<>("Dátum");
        dateEditCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        editedBookTableView.getColumns().add(dateEditCol);

        TableColumn<BookEditFxModel, Integer> numberBeforeEditCol = new TableColumn<>("Počet pred");
        numberBeforeEditCol.setCellValueFactory(new PropertyValueFactory<>("numberBefore"));
        editedBookTableView.getColumns().add(numberBeforeEditCol);

        TableColumn<BookEditFxModel, Integer> numberAfterEditCol = new TableColumn<>("Počet po");
        numberAfterEditCol.setCellValueFactory(new PropertyValueFactory<>("numberAfter"));
        editedBookTableView.getColumns().add(numberAfterEditCol);

        editedBookTableView.setItems(bookEditFxModel.getBookEditsModel());
    }

    public void fillLostBookTable(boolean b) {

        lostBookTableView.getItems().clear();
        lostBookTableView.getColumns().clear();
        if (lostBookFxModel.getLostBooks().size() > 0 || b) {
            lostBookFxModel.loadBooksToModel();
        }

        TableColumn<LostBookFxModel, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        lostBookTableView.getColumns().add(authorCol);

        TableColumn<LostBookFxModel, String> titleCol = new TableColumn<>("Názov");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        lostBookTableView.getColumns().add(titleCol);

        TableColumn<LostBookFxModel, String> schoolClassCol = new TableColumn<>("Ročník");
        schoolClassCol.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
        lostBookTableView.getColumns().add(schoolClassCol);

        TableColumn<LostBookFxModel, String> adminNameCol = new TableColumn<>("Admin");
        adminNameCol.setCellValueFactory(new PropertyValueFactory<>("nameOfAdmin"));
        lostBookTableView.getColumns().add(adminNameCol);

//        TableColumn<LostBookFxModel, String> teacherCol = new TableColumn<>("Ucitel");
//        lostBookTableView.getColumns().add(teacherCol);
        TableColumn<LostBookFxModel, String> teacherNameCol = new TableColumn<>("Meno učiteľa");
        teacherNameCol.setCellValueFactory(new PropertyValueFactory<>("nameOfTeacher"));
        lostBookTableView.getColumns().add(teacherNameCol);

        TableColumn<LostBookFxModel, String> teacherSurnameCol = new TableColumn<>("Priezvisko učiteľa");
        teacherSurnameCol.setCellValueFactory(new PropertyValueFactory<>("surnameOfTeacher"));
        lostBookTableView.getColumns().add(teacherSurnameCol);

//        teacherCol.getColumns().add(teacherNameCol);
//        teacherCol.getColumns().add(teacherSurnameCol);
        TableColumn<LostBookFxModel, LocalDateTime> dateCol = new TableColumn<>("Dátum");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        lostBookTableView.getColumns().add(dateCol);

        TableColumn<LostBookFxModel, Integer> numberCol = new TableColumn<>("Počet");
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        lostBookTableView.getColumns().add(numberCol);

        lostBookTableView.setItems(lostBookFxModel.getLostBooksFxModel());
    }
}
