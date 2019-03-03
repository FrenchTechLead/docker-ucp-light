package com.meshredded.docker_ucp_light.ui;

import java.util.List;

import com.meshredded.docker_ucp_light.models.UcpContainer;
import com.meshredded.docker_ucp_light.models.UcpServer;
import com.meshredded.docker_ucp_light.services.ContainersService;
import com.meshredded.docker_ucp_light.utils.Store;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ServersColumn {

	public static VBox getComponent(Scene scene) {
		final VBox vbox = new VBox();
		Store.servers.addListener((ListChangeListener.Change<? extends UcpServer> change)->{
			vbox.getChildren().clear();
			Label label = new Label("Servers");
			HBox hbox = new HBox(10);
			hbox.setPadding(new Insets(15, 0, 0, 15));
			hbox.getChildren().addAll(label);
			vbox.getChildren().add(hbox);
			for(UcpServer server : Store.servers) {
				CustomButton serverBtn = new CustomButton(scene, server.getLabel());
				serverBtn.setOnAction(action -> {
					ContainersService service = new ContainersService();
					Store.containers.clear();
					List<UcpContainer> containers = service.getContainers(server);
					containers.forEach(c-> c.setServer(server));
					Store.containers.addAll(containers);
				});
				HBox h = new HBox(10);
				h.setPadding(new Insets(15, 0, 0, 15));
				h.getChildren().addAll(serverBtn);
				vbox.getChildren().add(h);
				
			}
		});
		
		

		return vbox;
	}

}
