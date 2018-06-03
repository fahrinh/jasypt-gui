package id.nurul.fahri.jasyptgui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainForm {

    private StringProperty textInput = new SimpleStringProperty("");
    private StringProperty textPassword = new SimpleStringProperty("");
    private StringProperty textOutput = new SimpleStringProperty();

    public String getTextInput() {
        return textInput.get();
    }

    public StringProperty textInputProperty() {
        return textInput;
    }

    public void setTextInput(String textInput) {
        this.textInput.set(textInput);
    }

    public String getTextPassword() {
        return textPassword.get();
    }

    public StringProperty textPasswordProperty() {
        return textPassword;
    }

    public void setTextPassword(String textPassword) {
        this.textPassword.set(textPassword);
    }

    public String getTextOutput() {
        return textOutput.get();
    }

    public StringProperty textOutputProperty() {
        return textOutput;
    }

    public void setTextOutput(String textOutput) {
        this.textOutput.set(textOutput);
    }
}
