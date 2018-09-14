package br.com.a2cds.springboot.crudrest.example;

import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.a2cds.springboot.crudrest.example.endereco.Endereco;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBoot2CrudRestServiceBasicApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringBoot2CrudRestServiceBasicApplicationTests {

	@LocalServerPort
	private int port = 8080;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void contextLoads() throws Exception {
	}
	
	@Test
	public void testRetrieveEndereco() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/enderecos/0"),
				HttpMethod.GET, entity, String.class);

		String expected = "    {\n" + 
				"        \"id\": 0,\n" + 
				"        \"rua\": \"R. Henrique Braglia\",\n" + 
				"        \"numero\": \"257\",\n" + 
				"        \"complemento\": \"Ap. 46\",\n" + 
				"        \"cep\": \"02244-000\",\n" + 
				"        \"bairro\": \"Parada Inglesa\",\n" + 
				"        \"cidade\": \"São Paulo\",\n" + 
				"        \"estado\": \"SP\"\n" + 
				"    }";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void addCourse() {

		Endereco endereco = new Endereco((long) 1,"Av. Braz Leme", "1631", null, "02068-030", "Santana", "São Paulo", "SP");

		HttpEntity<Endereco> entity = new HttpEntity<Endereco>(endereco, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/enderecos"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		
		System.out.println(actual);

		assertTrue(actual.contains("/enderecos/"));
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
