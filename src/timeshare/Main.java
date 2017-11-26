/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeshare;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Artista
 */
public class Main extends Application {
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        root.getStylesheets().add("/timeshare/Style.css");
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop(){
        System.out.println("System is shutting down");
        // save statistician logs
        RunningConfiguration.LOCAL_JKNODE.getStatistician().createLog();
        Platform.exit();
        System.exit(0);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        RunningConfiguration.run();   
        launch(args);
    }
    
    
}
