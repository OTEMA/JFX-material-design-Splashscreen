package main;

import db.DbHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import transition.FadeTransition;

/**
 *
 * @author Theonlysmartboy <Theonlysmartboy@github.com>
 */
public class SplashScreenController implements Initializable {

    private Task copyWorker;
    @FXML
    private AnchorPane parent;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator pi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBar.setProgress(0);
        pi.setProgress(0);
        copyWorker = createWorker();
        progressBar.progressProperty().unbind();
        pi.progressProperty().unbind();
        progressBar.progressProperty().bind(copyWorker.progressProperty());
        pi.progressProperty().bind(copyWorker.progressProperty());
        FadeTransition.applyFadeTransition(parent, Duration.seconds(5), (e) -> {

            new Thread(copyWorker).start();
        });
    }

    private Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(1000);
                    updateProgress(i + 1, 10);
                    if (pi.getProgress() == 1.0) {
                        Platform.runLater(() -> {
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("/Auth/Login.fxml"));
                                parent.getChildren().removeAll();
                                parent.getChildren().setAll(root);
                                 DbHelper.createNewDatabase("test.db");
                            } catch (IOException ex) {
                                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                    }
                }
                return true;
            }
        };
    }

}
