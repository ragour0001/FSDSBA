package com.stackroute.articleRecommendationService.repository;



import com.stackroute.articleRecommendationService.domain.Article;
import com.stackroute.articleRecommendationService.domain.Source;
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
public class RecommendedArticleRepositoryTest {

    @Autowired
    private RecommendedArticleRepository recommendedArticleRepository;

    private Article article;

    @Before
    public void setUp() {

        Source source = new Source(1, "John");

        article = new Article("Article1", "new comments", 1,
                "Author name","Article title","description",
                "new article url","url to image","published At",
                "new content", source);


        List<Article> list = new ArrayList<>();
        list.add(article);

    }

    @After
    public void tearDown() {

        recommendedArticleRepository.deleteAll();
    }

    @Test
    public void  testSaveArticle() {

        recommendedArticleRepository.save(article);

        Article fetchArticle = recommendedArticleRepository.findByArticleId(article.getArticleId());

        Assert.assertEquals(article.getArticleId(), fetchArticle.getArticleId());
    }


}
