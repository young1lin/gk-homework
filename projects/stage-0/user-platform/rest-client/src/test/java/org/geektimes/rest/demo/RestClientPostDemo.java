package org.geektimes.rest.demo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/31 下午9:55
 * @version 1.0
 */
public class RestClientPostDemo {

	@Test
	public void testPost() {
		String postRequestParam = "name=张三&age=12";
		Entity<String> entity = Entity.entity(postRequestParam, MediaType.APPLICATION_JSON_TYPE);
		Client client = ClientBuilder.newClient();
		Response response = client
				.target("http://127.0.0.1:8080/hello/world")
				.request()
				.post(entity);

		String content = response.readEntity(String.class);
		System.out.println(content);
	}

}
