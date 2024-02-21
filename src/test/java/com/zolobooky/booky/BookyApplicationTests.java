package com.zolobooky.booky;

import com.zolobooky.booky.books.BookController;
import com.zolobooky.booky.dashboard.DashboardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookyApplicationTests {

	@Autowired
	private BookController bookController;

	@Autowired
	private DashboardController dashboardController;

	@Test
	void contextLoads() throws Exception {
		// sample tests
		assertThat(dashboardController).isNotNull();
		assertThat(bookController).isNotNull();
	}

}
