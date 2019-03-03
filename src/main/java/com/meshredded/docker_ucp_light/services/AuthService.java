package com.meshredded.docker_ucp_light.services;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.meshredded.docker_ucp_light.models.AuthToken;
import com.meshredded.docker_ucp_light.models.UcpServer;
import com.meshredded.docker_ucp_light.models.UsernamePassword;

public class AuthService extends RestService {
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public AuthToken getAuthToken(UcpServer s) {
		
        return client.resource(s.getEndpoint() + "/auth/login")
        		.type(MediaType.APPLICATION_JSON)
        		.post(AuthToken.class, new UsernamePassword(s.getUsername(), s.getPassword()));
    }

}
