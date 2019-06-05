package hello.world;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HelloControllerSpec2 {
	private static EmbeddedServer server;
	private static HelloClient client;

	@BeforeAll
	static void setupServer() {

		server = ApplicationContext.run(EmbeddedServer.class);

		client = server
				.getApplicationContext()
				.getBean(HelloClient.class);
	}

	@AfterAll
	static void stopServer() {
		if (server != null) {
			server.stop();
		}
	}

	@Test
	void testHelloWorldResponse() {
		assertEquals("Hello World", client.hello().blockingGet());
	}
}
