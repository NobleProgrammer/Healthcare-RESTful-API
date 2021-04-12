/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.health.ui.main;

import com.health.data.model.Account;
import com.health.data.model.HealthcareEngine;
import com.health.data.model.Topic;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 * @author Amar
 */
public class MainScreenController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<Account> tableView;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField passTextField;
    @FXML
    private Button createButton;
    @FXML
    private ListView<HBoxCell> list;

    //--------------------DATA MODEL DATA MEMBERS-------------------------------
    private HealthcareEngine engine;
    private Account loggedAccount;
    private ArrayList<String> topicTitles;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = new HealthcareEngine();
        topicTitles = new ArrayList<>();
        createContent();
    }

    public void setEngine(HealthcareEngine engine, Account account) {
        this.engine = engine;
        this.loggedAccount = account;
        //System.out.println(loggedAccount.getUsername() + " " + loggedAccount.getId());
    }

    @FXML
    public void handleOptionsAction() {
        try {
            System.out.println("dfl;kghmdlfknhm");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/health/ui/options/Options.fxml"));
            Parent root = loader.load();
            com.health.ui.options.OptionsController viewTopicController = loader.getController();
            loader.setController(viewTopicController);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Topic Operations");
            viewTopicController.setEngine(engine, loggedAccount,this);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public static class HBoxCell extends HBox {
          Label label = new Label();
          Button button = new Button();

          HBoxCell(String labelText, Button button) {
               super();
               this.button = button;
               label.setText(labelText);
               label.setMaxWidth(Double.MAX_VALUE);
               HBox.setHgrow(label, Priority.ALWAYS);
               button.setText("Read Topic");
               this.getChildren().addAll(label, button);
          }
     }

     public void createContent() {
          //BorderPane layout = new BorderPane();
          list.getItems().clear();
          ArrayList<Topic> topics = engine.getTopics();
          List<HBoxCell> arrayList = new ArrayList<>();
          for (int i = 0; i < topics.size(); i++) {
              Button button = new Button();
              Topic topic = topics.get(i);
              button.setOnAction(e -> loadViewTopicScreen(topic));
              arrayList.add(new HBoxCell(topic.getTitle(),button));
          }
          ObservableList<HBoxCell> myObservableList = FXCollections.observableList(arrayList);
          list.setItems(myObservableList);
     }
    
    
    public void loadViewTopicScreen(Topic topic) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/health/ui/topic/ViewTopic.fxml"));
            Parent root = loader.load();
            com.health.ui.topic.ViewTopicController viewTopicController = loader.getController();
            loader.setController(viewTopicController);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(topic.getTitle());
            viewTopicController.setEngine(engine, topic);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
