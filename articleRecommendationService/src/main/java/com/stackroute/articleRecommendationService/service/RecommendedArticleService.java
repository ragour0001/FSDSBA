package com.stackroute.articleRecommendationService.service;




import com.stackroute.articleRecommendationService.domain.Article;
import com.stackroute.articleRecommendationService.exception.ArticleNotFoundException;

import java.util.List;

public interface RecommendedArticleService {

    Article saveArticleToRecommendedList(Article article) throws Exception;

  //  Article deleteArticleFromRecommendedList(String articleId) throws ArticleNotFoundException;
  Article deleteArticleFromRecommendedList(Article article) throws ArticleNotFoundException;

    List<Article> getAllArticleFromRecommendedList() throws Exception;


}
