package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPaneController implements Initializable {
    @FXML
    private TextField spelling_input, explain_input;

    @FXML
    public void handleChangeInput(KeyEvent event) {
        if (event.getSource() == spelling_input) {
            System.out.println("Change spelling.");
        } else if (event.getSource() == explain_input) {
            System.out.println("Change explain.");
        }
//        System.out.println("Change input add.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
