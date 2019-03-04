package com.meshredded.docker_ucp_light.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UcpContainer {
	
	@JsonIgnore
	UcpServer server;

	@JsonProperty("Id")
	String id;
	
	@JsonProperty("Names")
	List<String> names;
	
	@JsonProperty("Image")
	String image;
	
	@JsonProperty("ImageID")
	String imageID;
	
	@JsonProperty("Command")
	String command;
	
	@JsonProperty("Created")
	Integer created;
	
	@JsonProperty("Ports")
	List<UcpPort> ports;
	
	@JsonProperty("Labels")
	Map<String, String> labels;
	
	@JsonProperty("State")
	String state;
	
	@JsonProperty("Status")
	String status;
	
	@JsonProperty("HostConfig")
	Object hostConfig;
	
	@JsonProperty("NetworkSettings")
	Object networkSettings;
	
	@JsonProperty("Mounts")
	List<Object> mounts;
}

@Data
class UcpPort {
	
	@JsonProperty("PrivatePort")
	Integer privatePort;
	
	@JsonProperty("Type")
	String type;
}
