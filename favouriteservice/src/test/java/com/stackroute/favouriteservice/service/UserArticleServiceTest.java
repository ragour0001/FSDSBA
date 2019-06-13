package com.stackroute.favouriteservice.service;


import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.favouriteservice.domain.Article;
import com.stackroute.favouriteservice.domain.Source;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.ArticleAlreadyExistsException;
import com.stackroute.favouriteservice.exception.ArticleNotFoundException;
import com.stackroute.favouriteservice.repository.UserArticleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserArticleServiceTest {

    @Mock
    private UserArticleRepository userArticleRepository;

    @Mock
    private Producer producer;

    private User user;
    private Article article;

    private List<Article> list;

    @InjectMocks
    UserArticleServiceImpl userArticleService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Source source = new Source(1, "John");

        article = new Article("Article1", "new comments",
                "Author name","Article title","description",
                "new article url","url to image","published At",
                "new content", source);
        list = new ArrayList<>();
        list.add(article);
        user = new User("John123", "john@gmail.com", list);

    }

    @After
    public void tearDown() {
        userArticleRepository.deleteAll();
        list = null;
        article = null;
        user = null;
    }

    @Test
    public void testSaveUserArticleSuccess() throws ArticleAlreadyExistsException {
        user = new User("John156", "john@gmail.com", null);
        when(userArticleRepository.findByUserName(user.getUserName())).thenReturn(user);
        User fetchedUser = userArticleService.saveUserArticleToFavouriteList(article, user.getUserName());
        Assert.assertEquals(fetchedUser, user);
        verify(userArticleRepository, timeout(1)).findByUserName(user.getUserName());
        verify(userArticleRepository,times(1)).save(user);
    }

    @Test
    public void testDeleteUserArticleFromFavouriteList() throws ArticleNotFoundException {
        when(userArticleRepository.findByUserName(user.getUserName())).thenReturn(user);
        User fetchedUser = userArticleService.deleteUserArticleFromFavouriteList(user.getUserName(),article.getArticleId());
        Assert.assertEquals(fetchedUser,user);
        verify(userArticleRepository,times(1)).findByUserName(user.getUserName());
        verify(userArticleRepository,times(1)).save(user);
    }

    @Test
    public void testUpdateCommentForArticle() throws ArticleNotFoundException {
        when(userArticleRepository.findByUserName(user.getUserName())).thenReturn(user);
        User fetchedUser = userArticleService.updateCommentForArticle("new updated comments",article.getArticleId(),user.getUserName());
        Assert.assertEquals(fetchedUser.getArticleList().get(0).getComments(), "new updated comments");
        verify(userArticleRepository,times(1)).findByUserName(user.getUserName());
        verify(userArticleRepository,times(1)).save(user);
    }

    @Test
    public void testGetAllUserArticleFromFavouriteList() throws Exception {
        when(userArticleRepository.findByUserName(user.getUserName())).thenReturn(user);
        List<Article> fetchedList = userArticleService.getAllUserArticleFromFavouriteList(user.getUserName());
        Assert.assertEquals(fetchedList, list);
        verify(userArticleRepository,times(1)).findByUserName(user.getUserName());
    }

}
