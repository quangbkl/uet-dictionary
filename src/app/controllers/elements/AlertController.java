package app.controllers.elements;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AlertController {

    @FXML
    private Label alert_header, alert_content;

    public void initData(String heading, String content) {
        alert_header.setText(heading);
        alert_content.setText(content);
    }
}
