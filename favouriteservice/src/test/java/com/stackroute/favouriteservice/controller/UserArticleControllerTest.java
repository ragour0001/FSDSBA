package com.stackroute.favouriteservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.favouriteservice.domain.Article;
import com.stackroute.favouriteservice.domain.Source;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.ArticleAlreadyExistsException;
import com.stackroute.favouriteservice.service.UserArticleService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserArticleController.class)
public class UserArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserArticleService userArticleService;


    private Source source;
    private Article article;
    private User user;
    private List<Article> articleList;


    @Before
    public void setUp() {

        articleList = new ArrayList<>();
        source = new Source(1, "John");
        article = new Article("Article1", "new comments",
                "Author name","Article title","description",
                "new article url","url to image","published At",
                "new content", source);

        articleList.add(article);


        source = new Source(2, "Johnny");
        article = new Article("Article2", "new comments updated",
                "Author name2","Article title2","description2",
                "new article url","url to image","published At",
                "new content2", source);

        articleList.add(article);

        user = new User("John", "john@gmail.com", articleList);
    }

    @After
    public void tearDown() {

        source = null;
        article = null;
        user = null;

    }

    //any() is Mockito Argument Matchers - any()
    @Test
    public void testSaveArticleSuccess() throws Exception {

        when(userArticleService.saveUserArticleToFavouriteList(any(), eq(user.getUserName()))).thenReturn(user);

        mockMvc.perform(post("/api/v1/favouriteservice/user/{userName}/article", user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(article)))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(userArticleService, times(1)).saveUserArticleToFavouriteList(any(), eq(user.getUserName()));

    }

    @Test
    public void testSaveArticleFailure() throws Exception {

        when(userArticleService.saveUserArticleToFavouriteList(any(), eq(user.getUserName()))).thenThrow(ArticleAlreadyExistsException.class);

        mockMvc.perform(post("/api/v1/favouriteservice/user/{userName}/article", user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(article)))
                .andExpect(status().isConflict())
                .andDo(print());

        verify(userArticleService, times(1)).saveUserArticleToFavouriteList(any(), eq(user.getUserName()));

    }

    @Test
    public void testUpdateCommentSuccess() throws Exception {

        when(userArticleService.updateCommentForArticle(article.getComments(), article.getArticleId(), user.getUserName())).thenReturn(user);
        mockMvc.perform(patch("/api/v1/favouriteservice/user/{userName}/article", user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(article)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userArticleService, times(1)).updateCommentForArticle(article.getComments(),article.getArticleId(),user.getUserName());

    }

    @Test
    public void testDeleteArticle() throws Exception {

        when(userArticleService.deleteUserArticleFromFavouriteList(user.getUserName(), article.getArticleId())).thenReturn(user);
        mockMvc.perform(delete("/api/v1/favouriteservice/user/{userName}/{articleId}", user.getUserName(), article.getArticleId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(article)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userArticleService, times(1)).deleteUserArticleFromFavouriteList(user.getUserName(),article.getArticleId());

    }

    @Test
    public void testGetAllUserArticleFromFavouriteList() throws Exception {

        when(userArticleService.getAllUserArticleFromFavouriteList(user.getUserName())).thenReturn(articleList);
        mockMvc.perform(get("/api/v1/favouriteservice/user/{userName}/articles", user.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(article)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userArticleService, times(1)).getAllUserArticleFromFavouriteList(user.getUserName());

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
