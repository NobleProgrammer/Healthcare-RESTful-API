
package com.health.ui.topic;

import com.health.data.model.Account;
import com.health.data.model.HealthcareEngine;
import com.health.data.model.Topic;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ViewTopicController implements Initializable {
    
    @FXML private Label titleLabel;
    @FXML private Label authorLabel;
    @FXML private TextArea textArea;
    
    private HealthcareEngine engine;
    private Topic topic;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setEngine(HealthcareEngine engine,Topic topic){
        this.engine = engine;
        this.topic = topic;
        this.titleLabel.setText(this.topic.getTitle());
        this.authorLabel.setText(this.topic.getAuthor().getUsername());
        this.textArea.setText(this.topic.getBody());
    }
    
}
