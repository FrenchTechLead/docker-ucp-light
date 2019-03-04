package com.meshredded.docker_ucp_light.ui;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.control.TextArea;

public class Console extends TextArea {

	public Console() {
		this.setEditable(false);
		this.setPrefSize(900, 900);
		this.getStyleClass().add("terminal");
	}

	void printJson(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.setText(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
