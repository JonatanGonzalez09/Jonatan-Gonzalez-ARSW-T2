package coronavirusAPITest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestTest {

	@Test
	public void shouldReturnAllCases() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
				.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
				.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
				.header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
				.asJson();
		
		assertNotNull(response.getBody());
		assertEquals((int)HttpStatus.SC_OK,response.getStatus());
	}
	
	@Test
	public void shouldReturnCaseByCountry() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
				.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada")
				.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
				.header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
				.asJson();
		
		assertNotNull(response.getBody());
		assertEquals((int)HttpStatus.SC_OK,response.getStatus());
	}

	@Test
	public void shouldReturnCoordinatesByCountry() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
				.get("https://restcountries-v1.p.rapidapi.com/name/China")
				.header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
				.header("x-rapidapi-key", "4a71d98902mshebb28f37a4e303ep14be52jsn5e65fb31a9e2")
				.asJson();
		assertNotNull(response.getBody());
		assertEquals((int)HttpStatus.SC_OK,response.getStatus());
	}

	@Test
	public void TestConcurrentAPI() {
		String uri = "https://restcountries-v1.p.rapidapi.com/name/China";
		int hilos = 5;
		List<TestConcurrent> listaConcurrente = new ArrayList<>();
		for (int i = 0; i < hilos; i++){
			listaConcurrente.add(new TestConcurrent(uri));
			listaConcurrente.get(i).start();
		}

		String uri2 = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada";
		List<TestConcurrent> listaConcurrente2 = new ArrayList<>();
		for (int i = 0; i < hilos; i++){
			listaConcurrente2.add(new TestConcurrent(uri2));
			listaConcurrente2.get(i).start();
		}

		String uri3 = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats";
		List<TestConcurrent> listaConcurrente3 = new ArrayList<>();
		for (int i = 0; i < hilos; i++){
			listaConcurrente3.add(new TestConcurrent(uri3));
			listaConcurrente3.get(i).start();
		}
	}

	/**
	 * Clase para generar la concurrencia en las solicitudes.
	 */
	private class TestConcurrent extends Thread{
		private String uri;
		private  MockMvc mvc;
		public TestConcurrent(String uri){
			this.uri = uri;
		}

		@Override
		public void run(){
			try {
				MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
						.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				int status = mvcResult.getResponse().getStatus();
				assertEquals(200, status);
			}catch (Exception e){
				fail();
			}
		}
	}
}
