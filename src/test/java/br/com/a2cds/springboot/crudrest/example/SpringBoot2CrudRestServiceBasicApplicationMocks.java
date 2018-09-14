package br.com.a2cds.springboot.crudrest.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.a2cds.springboot.crudrest.example.endereco.Endereco;
import br.com.a2cds.springboot.crudrest.example.endereco.EnderecoResource;
import br.com.a2cds.springboot.crudrest.example.endereco.EnderecoService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EnderecoResource.class, secure = false)
public class SpringBoot2CrudRestServiceBasicApplicationMocks {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EnderecoService enderecoService;
	
	Endereco mockEndereco = new Endereco((long) 1,"Av. Braz Leme", "1631", "Bl. A", "02068-030", "Santana", "São Paulo", "SP");
	
	String exampleEnderecoJson =  "    {\n" + 
			"        \"id\": 1,\n" + 
			"        \"rua\": \"Av. Braz Leme\",\n" + 
			"        \"numero\": \"1631\",\n" + 
			"        \"complemento\": \"Bl. A\",\n" + 
			"        \"cep\": \"02068-030\",\n" + 
			"        \"bairro\": \"Santana\",\n" + 
			"        \"cidade\": \"São Paulo\",\n" + 
			"        \"estado\": \"SP\"\n" + 
			"    }";
	
	@Test
	public void retrieveDetailsForEndereco() throws Exception {

		Mockito.when(
				enderecoService.retrieveEndereco(Mockito
						.anyLong())).thenReturn(mockEndereco);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/enderecos/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,rua:Av. Braz Leme,numero:1631,complemento: Bl. A,cep:02068-030,bairro:Santana,cidade:São Paulo,estado:SP}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void createEndereco() throws Exception {
		Endereco mockEndereco = new Endereco((long) 1,"Av. Braz Leme", "1631", "Bl. A", "02068-030", "Santana", "São Paulo", "SP");

		Mockito.when(
				enderecoService.addEndereco(Mockito
						.any(Endereco.class))).thenReturn(mockEndereco);

		//Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/enderecos")
				.accept(MediaType.APPLICATION_JSON).content(exampleEnderecoJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/enderecos/1", response
				.getHeader(HttpHeaders.LOCATION));

	}

}
