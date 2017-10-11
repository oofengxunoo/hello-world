package readlinglist;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import tools.JacksonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ReadlinglistApplicationTests {

	@Value("${local.server.port}")
	private int port;

	private RestOperations restTemplate = new RestTemplate();


	@Test(expected = HttpClientErrorException.class)
	public void contextLoads() {
		try {
			restTemplate.getForObject("http://localhost:{port}/bogusPage/akuu", String.class, port);
			fail("Should result in HTTP 404");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			throw e;
		}
	}

	@Test
	public void testInsertData(){
		Book book = new Book();
		book.setAuthor("testAuthor");
		book.setDescription("testDesc");
		book.setIsbn("testISBN");
		book.setReader("testReader");
		book.setTitle("testTitle");
		MultiValueMap<String, String> request = JacksonUtil.object2MultiValueMap(book);
		restTemplate.postForObject("http://localhost:{port}/Cart", request, String.class, port);
	}

}
