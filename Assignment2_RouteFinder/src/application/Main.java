package application;
	
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	static Graph graph = new Graph(); // The main graph object for the program.
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,1920,1080);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/* 
	 * TODO: Test saving and loading. 
	 * If saving/loading the static graph instance doesn't work, create a set of instance
	 * variables used explicitly for saving and loading only.
	 */
	
	/**
	 * Saves the current graph setup to an XML file.
	 * @throws IOException
	 */
	public void saveGraph() throws IOException {
		FileOutputStream fos = new FileOutputStream(new File("customGraph.xml"));
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.writeObject(graph);
		encoder.close();
		fos.close();
	}
	
	/**
	 * Loads a graph from an XML file named customGraph.xml
	 * @throws FileNotFoundException if customGraph.xml is not found.
	 * @throws IOException
	 */
	public void loadGraph() throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream("customGraph.xml");
		XMLDecoder decoder = new XMLDecoder(fis);
		Graph loadedGraph = (Graph) decoder.readObject();
		graph = loadedGraph;
		decoder.close();
		fis.close();
	}
}
