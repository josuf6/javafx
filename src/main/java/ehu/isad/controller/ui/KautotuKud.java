package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KautotuKud implements Initializable {

    // Reference to the main application.
    private Main mainApp;

    @FXML
    private ComboBox comboZerbitzua;

    @FXML
    private TextField txtErabiltzaile;

    @FXML
    private TextField txtPasahitza;

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void onClickKautotu(ActionEvent actionEvent) {
        System.out.println(txtErabiltzaile.getText() + ":" + txtPasahitza.getText());
        System.out.println(comboZerbitzua.getValue());

        if (ZerbitzuKud.getInstance().lortuZerbitzuak().contains(comboZerbitzua.getValue())) {
            if ("Flickr".equals(comboZerbitzua.getValue()) &&
                    "juanan".equals(txtErabiltzaile.getText()) &&
                    "pereira".equals(txtPasahitza.getText())) {

                mainApp.mainErakutsi();
            }
        }
        else if (!String.valueOf(comboZerbitzua.getValue()).isBlank() && comboZerbitzua.getValue() != null) {
            comboZerbitzua.getItems().add(comboZerbitzua.getValue().toString());
            ZerbitzuKud.getInstance().gehituZerbitzua(comboZerbitzua.getValue().toString());
        }
    }

    @FXML
    void onClickEzabatu(ActionEvent event) {
        if (comboZerbitzua.getValue() != null) {
            String izena = comboZerbitzua.getValue().toString();
            if (ZerbitzuKud.getInstance().lortuZerbitzuak().contains(izena)) {
                int pos = comboZerbitzua.getItems().indexOf(izena);
                comboZerbitzua.getItems().remove(pos);
                comboZerbitzua.getSelectionModel().clearSelection();
                ZerbitzuKud.getInstance().ezabatuZerbitzua(izena);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> herrialdeakList = ZerbitzuKud.getInstance().lortuZerbitzuak();
        ObservableList<String> herrialdeak = FXCollections.observableArrayList(herrialdeakList);

        comboZerbitzua.setItems( herrialdeak );
    }

}
