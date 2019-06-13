package com.stackroute.favouriteservice.repository;


import com.stackroute.favouriteservice.domain.Article;
import com.stackroute.favouriteservice.domain.Source;
import com.stackroute.favouriteservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserArticleRepositoryTest {

    @Autowired
    private UserArticleRepository userArticleRepository;

    private Article article;
    private User user;

    @Before
    public void setUp() {

        Source source = new Source(1, "John");

        article = new Article("Article1", "new comments",
                "Author name","Article title","description",
                "new article url","url to image","published At",
                "new content", source);


        List<Article> list = new ArrayList<>();
        list.add(article);
        user = new User("John123", "john@gmail.com", list);
    }

    @After
    public void tearDown() {

        userArticleRepository.deleteAll();
    }

    @Test
    public void  testSaveUserArticle() {

        userArticleRepository.save(user);

        User fetchUser = userArticleRepository.findByUserName(user.getUserName());
        List<Article> list = fetchUser.getArticleList();
        Assert.assertEquals(list.get(0).getArticleId(), user.getArticleList().get(0).getArticleId());
    }


}
