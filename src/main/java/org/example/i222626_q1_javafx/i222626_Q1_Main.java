package org.example.i222626_q1_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class i222626_Q1_Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader SceneLoader = new FXMLLoader(getClass().getResource("i222626_Q1.fxml"));
        Parent root = (Parent) SceneLoader.load();

        i222626_Q1_Controller ctrlrPointer = (i222626_Q1_Controller) SceneLoader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
