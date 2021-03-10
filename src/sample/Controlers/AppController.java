package sample.Controlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.DB.CRUDClient;
import sample.model.Client;

public class AppController {
    CRUDClient crudClient = new CRUDClient();

    @FXML
    private TextField text_filed_lastname;

    @FXML
    private TextField text_filed_name;

    @FXML
    private TextField text_filed_surname;

    @FXML
    private TableView<Client> table_client;

    @FXML
    private TableColumn<Client, Integer> id_column;

    @FXML
    private TableColumn<Client, String> lastname_column;

    @FXML
    private TableColumn<Client, String> name_column;

    @FXML
    private TableColumn<Client, String> surname_column;

    @FXML
    private TableColumn<Client, Date> date_of_birth_column;

    @FXML
    private TableColumn<Client, Integer> INN_column;

    @FXML
    private TableColumn<Client, String> family_status_column;

    @FXML
    private TableColumn<Client, String> type_of_education_column;

    @FXML
    private TableColumn<Client, Boolean> vip_column;

    @FXML
    private Button batton_add;

    @FXML
    private CheckBox check_box_vip;

    @FXML
    private TextField text_filed_type_of_education;

    @FXML
    private TextField text_filed_family_status;

    @FXML
    private TextField text_filed_INN;

    @FXML
    private DatePicker date_picker_date_of_birth;

    @FXML
    private Button batton_update;

    @FXML
    private Button batton_delite;

    @FXML
    private Label lable_id;

    @FXML
    private Button batton_drop;

    @FXML
    private Button button_close;

    @FXML
    void initialize() {
        viewTable();

        getSelectedRow();

        button_close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) button_close.getScene().getWindow();
                stage.close();
            }
        });

        batton_add.setOnAction(actionEvent -> {
                    criateClient();
                }
        );
        batton_update.setOnAction(actionEvent -> {
            updateClient();
        });
        batton_delite.setOnAction(actionEvent -> {
            deleteClient();
        });
        batton_drop.setOnAction(actionEvent -> {
            drop();
        });

    }

    private void drop() {
        text_filed_lastname.setText("");
        text_filed_name.setText("");
        text_filed_surname.setText("");
        date_picker_date_of_birth.setValue(null);
        text_filed_INN.setText(String.valueOf(""));
        text_filed_family_status.setText("");
        text_filed_type_of_education.setText("");
        check_box_vip.setSelected(false);
    }

    void criateClient() {
        Client client = new Client();
        client.setLastName(text_filed_lastname.getText());
        client.setName(text_filed_name.getText());
        client.setSurName(text_filed_surname.getText());
        try {
            client.setDateOfBirth(new SimpleDateFormat("dd.MM.yyyy").parse(date_picker_date_of_birth.getEditor().getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        client.setInn(Integer.parseInt(text_filed_INN.getText()));
        client.setFamilyStatus(text_filed_family_status.getText());
        client.setTypeOfEducation(text_filed_type_of_education.getText());
        client.setVip(check_box_vip.isSelected());

        crudClient.criate(client);

        drop();

        table_client.setItems(crudClient.reedAll());
    }

    void updateClient() {
        Client client = new Client();
        client.setId(Integer.parseInt(lable_id.getText()));
        client.setLastName(text_filed_lastname.getText());
        client.setName(text_filed_name.getText());
        client.setSurName(text_filed_surname.getText());
        try {
            client.setDateOfBirth(new SimpleDateFormat("dd.MM.yyyy").parse(date_picker_date_of_birth.getEditor().getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        client.setInn(Integer.parseInt(text_filed_INN.getText()));
        client.setFamilyStatus(text_filed_family_status.getText());
        client.setTypeOfEducation(text_filed_type_of_education.getText());
        client.setVip(check_box_vip.isSelected());

        crudClient.update(client);

        drop();

        table_client.setItems(crudClient.reedAll());
    }

    void deleteClient() {
        crudClient.delete(Integer.parseInt(lable_id.getText()));

        drop();

        table_client.setItems(crudClient.reedAll());
    }

    void viewTable() {
        id_column.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        lastname_column.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        name_column.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        surname_column.setCellValueFactory(new PropertyValueFactory<Client, String>("surName"));
        date_of_birth_column.setCellValueFactory(new PropertyValueFactory<Client, Date>("dateOfBirth"));
        INN_column.setCellValueFactory(new PropertyValueFactory<Client, Integer>("inn"));
        family_status_column.setCellValueFactory(new PropertyValueFactory<Client, String>("familyStatus"));
        type_of_education_column.setCellValueFactory(new PropertyValueFactory<Client, String>("typeOfEducation"));
        vip_column.setCellValueFactory(new PropertyValueFactory<Client, Boolean>("vip"));

        table_client.setItems(crudClient.reedAll());
    }

    private void getSelectedRow() {
        int id;
        TableView.TableViewSelectionModel<Client> selectionModel = table_client.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Client>() {


            @Override
            public void changed(ObservableValue<? extends Client> observableValue, Client client, Client t1) {
                if (t1 != null) {
                    text_filed_lastname.setText(t1.getLastName());
                    text_filed_name.setText(t1.getName());
                    text_filed_surname.setText(t1.getSurName());
                    date_picker_date_of_birth.setValue(Instant.ofEpochMilli(t1.getDateOfBirth().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                    text_filed_INN.setText(String.valueOf(t1.getInn()));
                    text_filed_family_status.setText(t1.getFamilyStatus());
                    text_filed_type_of_education.setText(t1.getTypeOfEducation());
                    check_box_vip.setSelected(t1.getVip());
                    lable_id.setText(String.valueOf(t1.getId()));
                }
            }
        });

    }
}