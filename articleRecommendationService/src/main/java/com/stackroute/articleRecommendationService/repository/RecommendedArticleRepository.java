package com.stackroute.articleRecommendationService.repository;

import com.stackroute.articleRecommendationService.domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecommendedArticleRepository extends MongoRepository<Article, String> {

   public Article findByArticleId(String articleId);

}
