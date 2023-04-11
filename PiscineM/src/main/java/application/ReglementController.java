package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import piscine.Main;
 

public class ReglementController {

    @FXML
    private Button retour;

    @FXML
    void Retour() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Accueil.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}