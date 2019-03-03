package com.meshredded.docker_ucp_light.ui;

import com.meshredded.docker_ucp_light.App;
import com.meshredded.docker_ucp_light.models.UcpContainer;
import com.meshredded.docker_ucp_light.services.LogsService;
import com.meshredded.docker_ucp_light.utils.Store;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContainersColumn {
	public static VBox getComponent(Scene scene) {
		final VBox vbox = new VBox();
		Store.containers.addListener((ListChangeListener.Change<? extends UcpContainer> change) -> {
			vbox.getChildren().clear();
			Label l = new Label("Containers (UP)");
			HBox hbox = new HBox(10);
			hbox.setPadding(new Insets(15, 0, 0, 15));
			hbox.getChildren().add(l);
			vbox.getChildren().add(hbox);
			for (UcpContainer container : Store.containers) {
				vbox.getChildren().add(getContainerRow(container, scene));
			}
		});

		return vbox;
	}

	public static HBox getContainerRow(UcpContainer container, Scene scene) {
		HBox h = new HBox(10);
		String stackname = container.getLabels().get("com.docker.stack.namespace");
		Label l = new Label(stackname);

		CustomButton showInfosBtn = new CustomButton(scene, "Show Infos");
		showInfosBtn.setOnAction(action -> {
			App.console.printJson(container);
		});

		CustomButton showLogsBtn = new CustomButton(scene, "Download Logs");
		showLogsBtn.setOnAction(action -> {
			App.console.setText("Downloading logs ...");
			LogsService service = new LogsService();
			String filename = stackname + "-" + container.getId().substring(0, 8) + ".log";
			boolean isDownloadOk = service.downloadLogs(container, filename);
			if (isDownloadOk) {
				App.console.setText("Logs downloaded to: " + System.getProperty("user.dir") + "\\" + filename);
			} else {
				App.console.setText("Download Faillure.");
			}
		});

		h.setPadding(new Insets(15, 0, 0, 15));
		h.getChildren().addAll(l, showInfosBtn, showLogsBtn);
		return h;
	}
}
