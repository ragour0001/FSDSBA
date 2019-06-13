package com.stackroute.articleRecommendationService.service;


import com.stackroute.articleRecommendationService.domain.Article;
import com.stackroute.articleRecommendationService.domain.Source;
import com.stackroute.articleRecommendationService.exception.ArticleNotFoundException;
import com.stackroute.articleRecommendationService.repository.RecommendedArticleRepository;

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
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class RecommendedArticleServiceTest {

    @Mock
    private RecommendedArticleRepository recommendedArticleRepository;



    private Article article;
    private Source source;
    private List<Article> list;

    @InjectMocks
    RecommendedArticleServiceImpl recommendedArticleService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        source = new Source(1, "John");

        article = new Article("Article1", "new comments",1,
                "Author name","Article title","description",
                "new article url","url to image","published At",
                "new content", source);
        list = new ArrayList<>();
        list.add(article);


    }

    @After
    public void tearDown() {
        recommendedArticleRepository.deleteAll();
        list = null;
        article = null;
        source = null;

    }

    @Test
    public void testSaveArticleToRecommendedListSuccess() throws Exception {

        article = new Article("Article1", "new comments",1,
                "Author name","Article title","description",
                "new article url","url to image","published At",
                "new content", source);
        when(recommendedArticleRepository.findByArticleId(article.getArticleId())).thenReturn(article);
        Article fetchedArticle = recommendedArticleService.saveArticleToRecommendedList(article);
        Assert.assertEquals(fetchedArticle, article);
        verify(recommendedArticleRepository, timeout(1)).findByArticleId(article.getArticleId());
        verify(recommendedArticleRepository,times(1)).save(article);
    }

//    @Test
//    public void testDeleteArticleFromRecommendedList() throws ArticleNotFoundException {
//        when(recommendedArticleRepository.findByArticleId(article.getArticleId())).thenReturn(article);
//        Article fetchArticle = recommendedArticleService.deleteArticleFromRecommendedList(article);
//
//        Assert.assertEquals(fetchArticle,article);
//        verify(recommendedArticleRepository,times(1)).findByArticleId(article.getArticleId());
//        verify(recommendedArticleRepository,times(1)).save(article);
//    }



//    @Test
//    public void testGetAllArticleFromRecommendedList() throws Exception {
//        when(recommendedArticleRepository.findByArticleId(article.getArticleId())).thenReturn(article);
//        List<Article> fetchedList = recommendedArticleService.getAllArticleFromRecommendedList();
//        Assert.assertEquals(fetchedList, list);
//        verify(recommendedArticleRepository,times(1)).findByArticleId(article.getArticleId());
//    }


    @Test
    public void testGetAllArticleFromRecommendedList() throws Exception{
        when(recommendedArticleRepository.findAll()).thenReturn(list);
        List<Article> listFetched = recommendedArticleService.getAllArticleFromRecommendedList();
        Assert.assertEquals(list, listFetched);

        verify(recommendedArticleRepository,times(1)).findAll();

    }

}
