package com.xsqt.test;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ProxyHttp {

	private String proxyHost = "http://172.19.63.85:8086";

	public void send() throws IOException, NoSuchAlgorithmException, KeyManagementException {
		SSLContext sslcontext = SSLContext.getInstance("SSL");
		sslcontext.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new SecureRandom());
		HttpClient client = HttpClients.custom().setSSLContext(sslcontext).build();
		HttpPost post = new HttpPost(proxyHost);
		post.addHeader("X-FORWARD-TO", "https://ec-t.lanmaoly.com/lanmao-ec-test/index");
		HttpResponse resp = client.execute(post);
		IOUtils.copy(resp.getEntity().getContent(), new FileOutputStream(new File("d:/1.txt")));
	}

	public static void main(String[] args){
		try {
			new ProxyHttp().send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
