package tools;

import static org.junit.Assert.*;

import org.junit.Test;

import readlinglist.Book;

public class JacksonUtilTest {

	@Test
	public void testObject2Json() {
		Book book = new Book();
		book.setAuthor("author");
		String result = JacksonUtil.object2Json(book);
		assertEquals("{\"id\":null,\"reader\":null,\"isbn\":null,\"title\":null,\"author\":\"author\",\"description\":null}", result);
	}

}
