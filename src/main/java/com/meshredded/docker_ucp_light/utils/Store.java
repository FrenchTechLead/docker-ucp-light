package com.meshredded.docker_ucp_light.utils;

import com.meshredded.docker_ucp_light.models.UcpContainer;
import com.meshredded.docker_ucp_light.models.UcpServer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {
	public static final ObservableList<UcpServer> servers = FXCollections.observableArrayList();
	public static final ObservableList<UcpContainer> containers = FXCollections.observableArrayList();

}
