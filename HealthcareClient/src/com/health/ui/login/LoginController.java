package com.health.ui.login;

import com.health.data.model.Account;
import com.health.data.model.HealthcareEngine;
import com.health.ui.main.MainScreenController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class LoginController implements Initializable {
    
    @FXML
    JFXTextField usernameSignup;
    @FXML
    JFXPasswordField passwordSignup;
    @FXML
    JFXButton signupButton;
    //-------------------------------------
    @FXML
    JFXTextField usernameLogin;
    @FXML
    JFXPasswordField passwordLogin;
    @FXML
    JFXButton loginButton;
    @FXML
    AnchorPane pane;
    @FXML
    StackPane stackPane;
    HealthcareEngine engine;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        engine = new HealthcareEngine();
    }    
    
    @FXML
    void signUpHandler(ActionEvent event) throws IOException, ClassNotFoundException {
        if (usernameSignup.getText().equals("") || passwordSignup.getText().equals("")) {
            makeOkAlert("Sign up failed.\nPlease fill all the fields");
        } else if (engine.searchAccount(usernameSignup.getText()) != null) {
            makeOkAlert("Sign up failed.\nThis username isn't available. Please try another.");
        } else {
            engine.createAccount(usernameSignup.getText(), passwordSignup.getText());
            makeOkAlert("You have signed up successfully!");
        }
    }
    
    @FXML
    void loginHandler(ActionEvent event) throws IOException, ClassNotFoundException {
        
        if (usernameLogin.getText().equals("") || passwordLogin.getText().equals("")) {
            makeOkAlert("Logging in failed.\nPlease fill all the fields.");
            return;
        }
        Account temp = engine.searchAccount(usernameLogin.getText());
        if (temp == null) {
            makeOkAlert("Logging in failed.\nThis username is not signed in. Please sign in first.");
        } else {
            if (temp.getUsername().equals(usernameLogin.getText()) && temp.getPassword().equals(passwordLogin.getText())) {
                loadMainScreen(temp);
            }
            else {
                makeOkAlert("Logging in failed.\nIncorrect username or password, please try again.");
            }
        }
    }
    
    public void loadMainScreen(Account account) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/health/ui/main/MainScreen.fxml"));
            Parent root = loader.load();
            com.health.ui.main.MainScreenController mainScreenController = loader.getController();
            loader.setController(mainScreenController);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Healthcare Forum (" + account.getUsername() + ")");
            mainScreenController.setEngine(engine , account);
            stage.show();
            Stage primaryStage = (Stage) this.pane.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void makeOkAlert(String text) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("Ok");
        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            dialog.close();
        });
        dialogLayout.setHeading(new Label(text));
        dialogLayout.setActions(button);
        dialog.show();
        pane.setEffect(blur);
        dialog.setOnDialogClosed(new EventHandler<JFXDialogEvent>() {
            @Override
            public void handle(JFXDialogEvent event) {
                pane.setEffect(null);
            }
        });
    }
    
}
