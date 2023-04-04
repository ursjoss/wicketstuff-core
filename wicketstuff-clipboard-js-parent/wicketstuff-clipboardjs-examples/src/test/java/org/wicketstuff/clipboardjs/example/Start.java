/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.clipboardjs.example;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;


import java.time.Duration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class Start
{

	public static void main(final String[] args) throws Exception
	{
		System.setProperty("wicket.configuration", "development");

		Server server = new Server();

		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setSecureScheme("https");
		http_config.setSecurePort(8443);
		http_config.setOutputBufferSize(32768);

		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
		http.setPort(8080);
		http.setIdleTimeout(1000 * 60 * 60);

		server.addConnector(http);

		Resource keystore = Resource.newClassPathResource("/keystore");
		if (keystore != null && keystore.exists())
		{
			// if a keystore for a SSL certificate is available, start a SSL
			// connector on port 8443.
			// By default, the quickstart comes with a Apache Wicket Quickstart
			// Certificate that expires about half way september 2021. Do not
			// use this certificate anywhere important as the passwords are
			// available in the source.

			SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
			sslContextFactory.setKeyStoreResource(keystore);
			sslContextFactory.setKeyStorePassword("wicket");
			sslContextFactory.setKeyManagerPassword("wicket");

			HttpConfiguration https_config = new HttpConfiguration(http_config);
			https_config.addCustomizer(new SecureRequestCustomizer());

			ServerConnector https = new ServerConnector(server, new SslConnectionFactory(
					sslContextFactory, "http/1.1"), new HttpConnectionFactory(https_config));
			https.setPort(8443);
			https.setIdleTimeout(500000);

			server.addConnector(https);
			System.out.println("SSL access to the examples has been enabled on port 8443");
			System.out
					.println("You can access the application using SSL on https://localhost:8443");
			System.out.println();
		}

		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");

		// uncomment next lines if you want to test with session persistence
//		DefaultSessionCache sessionCache = new DefaultSessionCache(bb.getSessionHandler());
//		FileSessionDataStore sessionStore = new FileSessionDataStore();
//		sessionStore.setStoreDir(new File("./jetty-session-data"));
//		sessionCache.setSessionDataStore(sessionStore);
//		bb.getSessionHandler().setSessionCache(sessionCache);

		ServletContextHandler contextHandler = ServletContextHandler.getServletContextHandler(bb.getServletContext());
		JakartaWebSocketServletContainerInitializer.configure(contextHandler,
				(servletContext, container) -> container.addEndpoint(new WicketServerEndpointConfig()));

		// uncomment next line if you want to test with JSESSIONID encoded in the urls
//		((AbstractSessionManager) bb.getSessionHandler().getSessionManager()).setUsingCookies(false);

		server.setHandler(bb);

		// START JMX SERVER
		// MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		// MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		// server.addEventListener(mBeanContainer);
		// server.addBean(mBeanContainer);

		try
		{
			server.start();
			server.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(100);
		}
	}
}
