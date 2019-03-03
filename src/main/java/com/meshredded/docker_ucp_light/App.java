package com.meshredded.docker_ucp_light;

import com.meshredded.docker_ucp_light.ui.AddEndpointBtn;
import com.meshredded.docker_ucp_light.ui.Console;
import com.meshredded.docker_ucp_light.ui.ContainersColumn;
import com.meshredded.docker_ucp_light.ui.ServersColumn;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends Application {
	
	private static final String CSS_PATH = "/styles.css";
	public static Console console = new Console();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		log.debug("App loading ...");
		primaryStage.setTitle("UCP - light");
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());
		
		
		VBox vbox =  new VBox(10);
		vbox.getChildren().add(AddEndpointBtn.getComponent(scene, primaryStage));
		
		
		HBox hbox =  new HBox(10);
		hbox.getChildren().addAll(
				ServersColumn.getComponent(scene),
				ContainersColumn.getComponent(scene),
				console
				);
		
		
		vbox.getChildren().add(hbox);
		
		root.getChildren().addAll(vbox);

		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
}
