/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.health.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Amar
 */
public class HealthcareClient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/health/ui/login/Login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/health/ui/main/MainScreen.fxml"));
        stage.setTitle("Healthcare Welcome Page");
        //stage.setWidth(650);
        //stage.setHeight(800);
        stage.setResizable(false);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
