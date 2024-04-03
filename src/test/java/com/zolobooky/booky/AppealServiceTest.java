package com.zolobooky.booky;

import com.zolobooky.booky.appeals.AppealController;
import com.zolobooky.booky.appeals.AppealService;
import com.zolobooky.booky.appeals.dto.CreateAppealDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppealController.class)
public class AppealServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AppealService appealService;

	private final AppealAPITestAssets appealAPITestAssets = new AppealAPITestAssets();

	@Test
    void getAllAppealsTest() throws Exception {
        when(appealService.getAllAppeals()).thenReturn(appealAPITestAssets.getAllAppeals());
        mockMvc.perform(get("/v0/appeals")).andExpect(status().isOk());
    }

	@Test
    void getAppealByIdTest() throws Exception {
        when(appealService.getAppeal(3)).thenReturn(appealAPITestAssets.getAppeal());
        mockMvc.perform(get("/v0/appeals/3")).andExpect(status().isOk());
    }

	// @Test
	void postAppealTest() throws Exception {
		CreateAppealDTO createAppealDTO = new CreateAppealDTO(900, 42, new Date(2024, 4, 4));
		when(appealService.createAppeal(createAppealDTO)).thenReturn(appealAPITestAssets.postAppeal());

		mockMvc
			.perform(post("/v0/appeals").contentType(MediaType.APPLICATION_JSON)
				.content("{\"book_id\": 900, \"borrower_id\": 42, \"expected_completion_date\": \"2024-04-04\"}"))
			.andExpect(status().isOk());
	}

}
