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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.fxmodels.AdminFxModel;

public class SupAAdminsSceneController {

    private final AdminFxModel adminFxModel = new AdminFxModel();
     private AdminFxModel selectedAdminFxModel;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button backButton;

    @FXML
    private Button newAdminButton;

    @FXML
    private Button resetPasswordButton;
    
    @FXML
    private TableView<AdminFxModel> simpleTableView;

    @FXML
    void initialize() {
        simpleTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AdminFxModel>() {
            @Override
            public void changed(ObservableValue<? extends AdminFxModel> observable, AdminFxModel oldValue, AdminFxModel newValue) {
                selectedAdminFxModel = simpleTableView.getSelectionModel().getSelectedItem();
                if (selectedAdminFxModel == null) {
                    resetPasswordButton.setDisable(true);
                } else {
                    resetPasswordButton.setDisable(false);
                }
            }
        });

        
        newAdminButton.setOnAction(eh -> {
            SupANewAdminSceneController controller = new SupANewAdminSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("SupANewAdminScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        resetPasswordButton.setOnAction(eh -> {
            SupAResetPasswordSceneController controller = new SupAResetPasswordSceneController(selectedAdminFxModel);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("SupAResetPasswordScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        backButton.setOnAction(eh -> {
            SupAMainSceneController controller = new SupAMainSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("SupAMainScene.fxml"));
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
        
        if (adminFxModel.getAdmins().size() > 0) {
            adminFxModel.loadAdminToModel();
        }
        
        TableColumn<AdminFxModel, String> usernameCol = new TableColumn<>("Meno");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        simpleTableView.getColumns().add(usernameCol);
        
        TableColumn<AdminFxModel, String> emailCol = new TableColumn<>("E-mail");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        simpleTableView.getColumns().add(emailCol);
        
        simpleTableView.setItems(adminFxModel.getAdminsModel());
        
    }
}
