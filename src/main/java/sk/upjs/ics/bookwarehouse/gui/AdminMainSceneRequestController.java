// prepared with the help of https://stackoverflow.com/questions/30889732/javafx-tableview-change-row-color-based-on-column-value
package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.fxmodels.BookLendingFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.TeacherFxModel;

public class AdminMainSceneRequestController {

    private final BookLendingFxModel bookLendingFxModel = new BookLendingFxModel();
    private final TeacherFxModel teacherFxModel = new TeacherFxModel();
    private BookLendingFxModel selectedBookLendingFxModel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private ComboBox<String> teacherComboBox;

    @FXML
    private TableView<BookLendingFxModel> simpleTableView;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button returnBookButton;

    @FXML
    private Button editLendingButton;

    @FXML
    void initialize() {
        backButton.setOnAction(eh -> {
            AdminMainSceneController controller = new AdminMainSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("AdminMainScene.fxml"));
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

        simpleTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookLendingFxModel>() {
            @Override
            public void changed(ObservableValue<? extends BookLendingFxModel> observable, BookLendingFxModel oldValue, BookLendingFxModel newValue) {
                selectedBookLendingFxModel = simpleTableView.getSelectionModel().getSelectedItem();
                if (selectedBookLendingFxModel == null) {
                    editLendingButton.setDisable(true);
                } else {
                    editLendingButton.setDisable(false);
                }
            }
        });

        editLendingButton.setOnAction(eh
                -> {
            AdminEditLendingSceneController controller = new AdminEditLendingSceneController(selectedBookLendingFxModel);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("AdminEditLendingScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("BookWareHouse");
                Image logo = new Image(getClass().getResourceAsStream("LogoBWH.png"));
                stage.getIcons().add(logo);
                stage.show();

                stage.setOnHidden(handler -> {
                    fillSimpleTable(true);
                });

            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        );

        returnBookButton.setOnAction(eh
                -> {
            AdminReturnBookSceneController controller = new AdminReturnBookSceneController(selectedBookLendingFxModel);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("AdminReturnBookScene.fxml"));
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

                stage.setOnHidden(handler -> {
                    fillSimpleTable(true);
                });
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        );

        // filling the comboBox with teachers names
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
        }
        );

        fillSimpleTable(false);

    }

    public void fillSimpleTable(boolean b) {
        simpleTableView.getItems().clear();
        simpleTableView.getColumns().clear();
        if (bookLendingFxModel.getLendings().size() > 0 || b) {
            bookLendingFxModel.loadLendingForAdminToModel();
        }

        TableColumn<BookLendingFxModel, String> authorCol = new TableColumn<>("Autor");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        simpleTableView.getColumns().add(authorCol);

        TableColumn<BookLendingFxModel, String> titleCol = new TableColumn<>("Názov");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        simpleTableView.getColumns().add(titleCol);

        TableColumn<BookLendingFxModel, String> teacherNameCol = new TableColumn<>("Meno učiteľa");
        teacherNameCol.setCellValueFactory(new PropertyValueFactory<>("nameOfTeacher"));
        simpleTableView.getColumns().add(teacherNameCol);

        TableColumn<BookLendingFxModel, String> teacherSurnameCol = new TableColumn<>("Priezvisko učiteľa");
        teacherSurnameCol.setCellValueFactory(new PropertyValueFactory<>("surnameOfTeacher"));
        simpleTableView.getColumns().add(teacherSurnameCol);

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

        approvedCol.setCellFactory(column -> {
            return new TableCell<BookLendingFxModel, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<BookLendingFxModel> currentRow = getTableRow();

                    if (!isEmpty()) {

                        if (item != null && item.equals("potvrdené")) {
                            currentRow.setStyle("");
                        } else {
                            currentRow.setStyle("-fx-background-color:lightcoral");
                        }
                    }

                }
            };
        });

        simpleTableView.getColumns().add(approvedCol);

        simpleTableView.setItems(bookLendingFxModel.getBookLendingsModel());

    }

}
