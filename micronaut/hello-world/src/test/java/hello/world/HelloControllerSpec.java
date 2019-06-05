package hello.world;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HelloControllerSpec {
	private static EmbeddedServer server;
	private static HttpClient client;

	@BeforeAll
	static void setupServer() {
		server = ApplicationContext.run(EmbeddedServer.class);

		client = server
				.getApplicationContext()
				.createBean(HttpClient.class, server.getURL());
	}

	@AfterAll
	static void stopServer() {
		if (client != null) {
			client.stop();
		}

		if (server != null) {
			server.stop();
		}
	}

	@Test
	void testHelloWorldResponse() {
		String response = client.toBlocking()
				.retrieve(HttpRequest.GET("/hello"));
		assertEquals("Hello World", response); //)
	}
}
