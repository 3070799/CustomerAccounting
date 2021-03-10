package sample.Controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Config;
import sample.animation.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button sign_in_batton;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField login_field;

    @FXML
    private Label label_err;

    @FXML
    private Button button_close;

    private double xOffset;
    private double yOffset;

    @FXML
    void initialize() {
        button_close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) button_close.getScene().getWindow();
                stage.close();
            }
        });

        sign_in_batton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                label_err.setText("");
                if (login_field.getText().equals(Config.login) && password_field.getText().equals(Config.pass)) {

                    sign_in_batton.getScene().getWindow().hide();
                    final Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Controller.this.getClass().getResource("/sample/view/app.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = (Parent) loader.getRoot();
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
                            stage.setX(event.getScreenX() - xOffset);
                            stage.setY(event.getScreenY() - yOffset);
                        }
                    });
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.showAndWait();
                } else {
                    label_err.setText("неверно введены имя пользователя или пароль");
                    Shake errAnim = new Shake(label_err);
                    Shake loginAnim = new Shake(login_field);
                    Shake passAnim = new Shake(password_field);
                    errAnim.playAnim();
                    loginAnim.playAnim();
                    passAnim.playAnim();
                }
            }
        });

    }
}

