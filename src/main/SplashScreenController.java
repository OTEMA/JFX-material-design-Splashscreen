package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import transition.FadeTransition;

/**
 *
 * @author Theonlysmartboy <Theonlysmartboy@github.com>
 */
public class SplashScreenController implements Initializable {
@FXML
private AnchorPane parent;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       FadeTransition.applyFadeTransition(parent, Duration.seconds(5), (e)->{
           try {
               Parent root = FXMLLoader.load(getClass().getResource("/Auth/Login.fxml"));
               parent.getChildren().removeAll();
               parent.getChildren().setAll(root);
               
               
           } catch (IOException ex) {
               Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
    }

}
