package bms.player.beatoraja.launcher;

import bms.player.beatoraja.Config;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ObsConfigurationView implements Initializable {
  @FXML
  private CheckBox obsWsEnabled;
  @FXML
  private TextField obsWsHost;
  @FXML
  private Spinner<Integer> obsWsPort;
  @FXML
  private CheckBox obsWsAuth;
  @FXML
  private PasswordField obsWsPass;
  @FXML
  private Label obsTestResult;

  private Config config;
  private PlayConfigurationView main;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  public void init(PlayConfigurationView main) {
    this.main = main;
    obsWsPass.disableProperty().bind(obsWsAuth.selectedProperty().not());
  }

  public void update(Config config) {
    this.config = config;

    obsWsEnabled.setSelected(config.isUseObsWs());
    obsWsHost.setText(config.getObsWsHost());
    obsWsPort.getValueFactory().setValue(config.getObsWsPort());
    obsWsAuth.setSelected(config.isObsWsUseAuth());
    obsWsPass.setText(config.getObsWsPass());
    obsTestResult.setText("");
  }

  public void commit() {
    config.setUseObsWs(obsWsEnabled.isSelected());
    config.setObsWsHost(obsWsHost.getText());
    config.setObsWsPort(obsWsPort.getValue());
    config.setObsWsUseAuth(obsWsAuth.isSelected());
    config.setObsWsPass(obsWsPass.getText());
  }

  @FXML
  private void testObsConnection() {
    String host = obsWsHost.getText();
    int port = obsWsPort.getValue();
    boolean useAuth = obsWsAuth.isSelected();
    String password = obsWsPass.getText();

    try {
      // TODO: Implement OBS Websocket Test
      boolean success = true;
      if (success) {
        obsTestResult.setText("OK!");
      } else {
        obsTestResult.setText("Failed!");
      }
    } catch (Exception e) {
      obsTestResult.setText("Err: " + e.getMessage());
    }
  }
}

