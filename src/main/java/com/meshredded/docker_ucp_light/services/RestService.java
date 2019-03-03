package com.meshredded.docker_ucp_light.services;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RestService {
	
	ClientConfig clientConfig;
	Client client;
	
	RestService(){
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManagerImpl()};
		try {
		    SSLContext sc = SSLContext.getInstance("TLS");
		    sc.init(null, trustAllCerts, new SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		    log.error(e.getMessage());
		    e.printStackTrace();
		}
		
		
		clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		client = Client.create(clientConfig);
		client.addFilter(new LoggingFilter(System.out));
		
	}

}

class X509TrustManagerImpl implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
	
}
