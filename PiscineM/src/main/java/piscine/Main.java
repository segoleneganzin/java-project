package piscine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage stage;
	@Override
    public void start(Stage racine) throws Exception {
        // Nœud racine.
    	Main.stage = racine;
        //String pwd = System.getProperty("user.dir");
        //System.out.println("Le répertoire courant est : " + pwd);
        Parent root = FXMLLoader.load(getClass().getResource("../ihm/Accueil.fxml"));
        // Configuration de la scène.
        Scene scene = new Scene(root,546,439);
        // Configuration de la fenêtre.
        racine.setScene(scene);
        racine.setTitle("Bienvenue aux Piscines Vannetaises");
        racine.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}