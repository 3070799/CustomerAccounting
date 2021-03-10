package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DB.ConnectorJDBC;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Customer Accounting");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Config.reedConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Config.login);
        System.out.println(Config.pass);
        System.out.println(Config.DATABASE_URL);
        System.out.println(Config.USER);
        System.out.println(Config.PASSWORD);
        ConnectorJDBC.criateNewTeble();
        launch(args);
    }
}
