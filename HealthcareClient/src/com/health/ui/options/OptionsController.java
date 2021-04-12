/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.health.ui.options;

import com.health.data.model.Account;
import com.health.data.model.HealthcareEngine;
import com.health.data.model.Topic;
import com.health.ui.main.MainScreenController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Amar
 */
public class OptionsController implements Initializable {

    @FXML private TextField creaeteTopicTitle;
    @FXML private TextArea topicBody;
    @FXML private Button createButton;
    @FXML private TextField searchTextField;
    @FXML private Button searchButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML StackPane stackPane;
    @FXML AnchorPane pane;
    
    //-------------------------------------------------------------------
    private HealthcareEngine engine;
    private Account loggedAccount;
    private MainScreenController mainScreen;
    
    public void setEngine(HealthcareEngine engine, Account account , MainScreenController mainScreen) {
        this.engine = engine;
        this.loggedAccount = account;
        this.mainScreen = mainScreen;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    public void createButtonHandler(){
        try {
            /*if (creaeteTopicTitle.getText().equals("") || topicBody.equals("")) {
            makeOkAlert("Topic title or body cannot be empty.");
            return;
            }
            try {
            Topic topic = engine.createTopic(creaeteTopicTitle.getText(), topicBody.getText(), loggedAccount);
            if (topic != null) {
            makeOkAlert("Topic is created successfully.");
            mainScreen.createContent();
            }
            else {
            makeOkAlert("Topic is not created, try again.");
            }
            } catch (FileNotFoundException ex) {
            makeOkAlert("An error is occured while creating the topic.");
            }*/
            engine.createTopic("GG", "iudkasdlkasd;lfjsdlkhgjsljkhgsjhbg;ojsubhgsoghnuv", loggedAccount);
            mainScreen.createContent();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    public void searchButtonHandler(){
        if (searchTextField.getText().equals("")) {
            makeOkAlert("Please enter topic title to be searched.");
            return;
        }
        
        Topic topic = engine.searchTopic(searchTextField.getText());
        if (topic != null) {
            makeOkAlert("Topic is found, you can update or delete.");
        }
        else {
            makeOkAlert("Topic is not found, make sure you typed the title correctly.");
        }
        
    }
    
    @FXML
    public void deleteButtonHandler(){
        if (searchTextField.getText().equals("")) {
            makeOkAlert("Please enter topic title to be deleted.");
            return;
        }
        Topic topic = engine.deleteTopic(engine.searchTopic(searchTextField.getText()).getId());
        if (topic != null) {
            makeOkAlert("Topic is deleted successfully.");
            mainScreen.createContent();
        }
        else {
            makeOkAlert("Topic is not found, try again.");
        }
    }
    
    @FXML
    public void updateButtonHandler(){
        if (searchTextField.getText().equals("")) {
            makeOkAlert("Please enter topic title to be updated.");
            return;
        }
        if (creaeteTopicTitle.getText().equals("") || topicBody.equals("")) {
            makeOkAlert("Topic title or body cannot be empty.");
            return;
        }
        
        Topic topic = engine.updateTopic(engine.searchTopic(searchTextField.getText()).getId(),creaeteTopicTitle.getText(),topicBody.getText(),loggedAccount);
        if (topic != null) {
            makeOkAlert("Topic is updated successfully.");
            mainScreen.createContent();
        }
        else {
            makeOkAlert("Topic is not updated, try again.");
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
