package com.meshredded.docker_ucp_light.services;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;

import com.meshredded.docker_ucp_light.models.UcpContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogsService extends RestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean downloadLogs(UcpContainer c, String filename) {
		log.debug("Downloading logs ...");
		try {
			InputStream is = client
					.resource(c.getServer().getEndpoint() + "/containers/" + c.getId() + "/logs?stdout=true&tail=all")
					.type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + c.getServer().getAccessToken())
					.get(InputStream.class);
			FileUtils.copyInputStreamToFile(is, new File(filename));
			log.info("Logs downloaded successfully.");
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

}
