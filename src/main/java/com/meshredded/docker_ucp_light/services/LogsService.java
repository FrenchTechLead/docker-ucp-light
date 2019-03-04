package com.meshredded.docker_ucp_light.services;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.meshredded.docker_ucp_light.models.UcpContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogsService extends RestService {

	@GET
	@Produces("text/plain; charset=utf-8")
	public boolean downloadLogs(UcpContainer c, String filename) {
		log.debug("Downloading logs ...");
		try {
			InputStream is = client
					.resource(c.getServer().getEndpoint() + "/containers/" + c.getId() + "/logs?stdout=1&tail=all&stderr=1")
					.accept(MediaType.TEXT_PLAIN)
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + c.getServer().getAccessToken())
					.get(InputStream.class);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			FileWriter fw = new FileWriter(filename,true);
			while(true) {
				String line = reader.readLine();
				if(line == null)
					break;
				line = line.length() > 8 ? line.substring(8) : line;
			    fw.write(line + "\n");
			    			}
			fw.close();
			log.info("Logs downloaded successfully.");
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

}
