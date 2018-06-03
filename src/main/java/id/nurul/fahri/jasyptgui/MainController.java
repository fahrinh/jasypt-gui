package id.nurul.fahri.jasyptgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtPassword;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnEncrypt;

    @FXML
    private Button btnDecrypt;

    private MainForm mainForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainForm = new MainForm();

        this.txtInput.textProperty().bindBidirectional(mainForm.textInputProperty());
        this.txtPassword.textProperty().bindBidirectional(mainForm.textPasswordProperty());
        this.txtOutput.textProperty().bindBidirectional(mainForm.textOutputProperty());

        this.txtInput.addEventFilter(KeyEvent.KEY_PRESSED, new TabTraversalEventHandler());
        this.txtPassword.addEventFilter(KeyEvent.KEY_PRESSED, new TabTraversalEventHandler());
        this.txtOutput.addEventFilter(KeyEvent.KEY_PRESSED, new TabTraversalEventHandler());

    }

    public void onClickEncrypt(ActionEvent event) {

        if (this.txtInput.getText().isEmpty() || this.txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Input or Password can not be empty", ButtonType.OK).showAndWait();
            return;
        }

        final EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setPassword(mainForm.getTextPassword());

        final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setConfig(config);

        mainForm.setTextOutput(encryptor.encrypt(mainForm.getTextInput()));
    }

    public void onClickDecrypt(ActionEvent event) {
        if (this.txtInput.getText().isEmpty() || this.txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Input or Password can not be empty", ButtonType.OK).showAndWait();
            return;
        }

        final EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setPassword(mainForm.getTextPassword());

        final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setConfig(config);

        try {
            mainForm.setTextOutput(encryptor.decrypt(mainForm.getTextInput()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Input can not be decrypted", ButtonType.OK).showAndWait();
        }
    }

    public void onClickCopyToClip(ActionEvent event) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(mainForm.getTextOutput());
        clipboard.setContent(content);
    }


}
