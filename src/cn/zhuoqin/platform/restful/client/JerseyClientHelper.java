package cn.zhuoqin.platform.restful.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhuoqin.platform.config.SystemConfig;
import cn.zhuoqin.platform.http.factory.ConnectionFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

public class JerseyClientHelper {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private Client client = null;

	public Client getClient(String proxyHost, int proxyPort) {
		if (client == null) {
			URLConnectionClientHandler cc = new URLConnectionClientHandler(new ConnectionFactory(proxyHost, proxyPort));
			client = new Client(cc);
			client.setConnectTimeout(2000000);
		}

		return client;
	}

	public Client getClient() {
		if (client == null) {
			if (SystemConfig.getPara("UseProxy").trim().equalsIgnoreCase("true")) {
				String httpProxHost = SystemConfig.getPara("ProxyHost");
				int httpProxPort = Integer.parseInt(SystemConfig.getPara("ProxyPort"));
				URLConnectionClientHandler cc = new URLConnectionClientHandler(new ConnectionFactory(httpProxHost, httpProxPort));
				client = new Client(cc);
				client.setConnectTimeout(2000000);
			} else {
				client = Client.create(new DefaultClientConfig());
			}
		}

		return client;
	}
}