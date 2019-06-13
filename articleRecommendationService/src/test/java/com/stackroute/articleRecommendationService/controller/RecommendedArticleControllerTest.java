package com.stackroute.articleRecommendationService.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.articleRecommendationService.domain.Article;
import com.stackroute.articleRecommendationService.service.RecommendedArticleService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommendedArticleController.class)
public class RecommendedArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecommendedArticleService recommendedArticleService;

    private List<Article>  articleList;
    Article article;

    @Before
    public void setUp() {

        articleList = new ArrayList<>();

    }

    @After
    public void tearDown() {


    }

    @Test
    public void tesGetAllArticleFromRecommendedList() throws Exception {
        when(recommendedArticleService.getAllArticleFromRecommendedList()).thenReturn(articleList);
        mockMvc.perform(get("/api/v1/recommendedservice/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(article)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(recommendedArticleService, times(1)).getAllArticleFromRecommendedList();
    }


    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "JSON processing error!!!";
        }

        return result;
    }

}
