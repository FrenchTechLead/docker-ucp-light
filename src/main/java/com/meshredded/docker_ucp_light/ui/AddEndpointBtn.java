package com.meshredded.docker_ucp_light.ui;

import com.meshredded.docker_ucp_light.models.UcpServer;
import com.meshredded.docker_ucp_light.services.AuthService;
import com.meshredded.docker_ucp_light.utils.Store;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddEndpointBtn {

	public static CustomButton getComponent(Scene scene, Stage primaryStage) {
		CustomButton btn = new CustomButton(scene, "Add UCP Server");
		btn.setOnAction(event -> {
			log.debug("Add UCP Server Btn Clicked");
			StackPane secondaryLayout = new StackPane();
			Scene secondScene = new Scene(secondaryLayout, 400, 180);

			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(5);
			grid.setHgap(5);

			final TextField endpoint = new TextField();
			endpoint.setPromptText("Endpoint Ex : http://ucp-server.com");
			endpoint.setPrefColumnCount(15);
			GridPane.setConstraints(endpoint, 0, 0);
			grid.getChildren().add(endpoint);

			final TextField username = new TextField();
			username.setPromptText("Username, Ex: JohnDoe");
			GridPane.setConstraints(username, 0, 1);
			grid.getChildren().add(username);

			final PasswordField  password = new PasswordField ();
			password.setPrefColumnCount(15);
			password.setPromptText("Password, Ex: dadada");
			GridPane.setConstraints(password, 0, 2);
			grid.getChildren().add(password);

			final TextField label = new TextField();
			label.setPrefColumnCount(15);
			label.setPromptText("Label, Ex : DEV SERVER");
			GridPane.setConstraints(label, 0, 3);
			grid.getChildren().add(label);

			CustomButton submit = new CustomButton(secondScene, "Add Server");
			GridPane.setConstraints(submit, 1, 0);
			grid.getChildren().add(submit);

			Label errorLabel = new Label("All fields are required.");
			errorLabel.setVisible(false);
			GridPane.setConstraints(errorLabel, 1, 1);
			grid.getChildren().add(errorLabel);

			
			endpoint.setText("https://ucpdmz.dev");
			username.setText("MECHERI");
			password.setText("");
			label.setText("PREPROD");
			submit.setOnAction(event2 -> {
				if (isValidInputs(endpoint, username, password, label)) {
					UcpServer server = new UcpServer(endpoint.getText(), username.getText(), password.getText(),
							label.getText());
					AuthService s = new AuthService();
					try {
						log.info("Adding new UCP Server : " + server.toString());
						String token = s.getAuthToken(server).getAuth_token();
						server.setAccessToken(token);
						Store.servers.add(server);
						
						errorLabel.setText("success");
						errorLabel.setVisible(true);
					} catch (Exception e) {
						log.error("Error on adding UCP Server" + e.getMessage());
						errorLabel.setText(e.getMessage());
						errorLabel.setVisible(true);
					}
					
					

				} else {
					errorLabel.setVisible(true);
				}

			});

			secondaryLayout.getChildren().add(grid);

			// New window (Stage)
			Stage newWindow = new Stage();
			newWindow.setTitle("Add UCP Server");
			newWindow.setScene(secondScene);

			// Specifies the modality for new window.
			newWindow.initModality(Modality.WINDOW_MODAL);

			// Specifies the owner Window (parent) for new window
			newWindow.initOwner(primaryStage);

			newWindow.show();

		});
		return btn;
	}

	private static boolean isValidInputs(TextField... fields) {
		for (TextField f : fields) {
			if (f.getText().equals("") || f.getText().equals(null))
				return false;
		}
		return true;
	}

}
