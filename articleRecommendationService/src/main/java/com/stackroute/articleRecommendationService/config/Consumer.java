package com.stackroute.articleRecommendationService.config;



import com.stackroute.articleRecommendationService.domain.Article;
import com.stackroute.articleRecommendationService.domain.Source;
import com.stackroute.articleRecommendationService.service.RecommendedArticleServiceImpl;
import com.stackroute.rabbitmq.domain.ArticleDTO;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  @Autowired
  private RecommendedArticleServiceImpl recommendedArticleService;

  @RabbitListener(queues = "article_add_queue")
  public void getArticleDtoFromRabbitMqForAdd(ArticleDTO articleDTO) throws Exception {

    Article article = new Article();
    Source source = new Source();

    source.setSourceId(articleDTO.getSource().getSourceId());
    source.setSourceName(articleDTO.getSource().getSourceName());

    article.setArticleId(articleDTO.getArticleId());
    article.setComments(articleDTO.getComments());
    article.setAuthor(articleDTO.getAuthor());
    article.setTitle(articleDTO.getTitle());
    article.setDescription(articleDTO.getDescription());
    article.setArticleUrl(articleDTO.getArticleUrl());
    article.setUrlToImage(articleDTO.getUrlToImage());
    article.setPublishedAt(articleDTO.getPublishedAt());
    article.setContent(articleDTO.getContent());
    article.setSource(source);

    recommendedArticleService.saveArticleToRecommendedList(article);

  }

  @RabbitListener(queues = "article_delete_queue")
  public void getArticleDtoFromRabbitMqForDelete(ArticleDTO articleDTO) throws Exception{

    Article article = new Article();
    Source source = new Source();

    source.setSourceId(articleDTO.getSource().getSourceId());
    source.setSourceName(articleDTO.getSource().getSourceName());

    article.setArticleId(articleDTO.getArticleId());
    article.setComments(articleDTO.getComments());
    article.setAuthor(articleDTO.getAuthor());
    article.setTitle(articleDTO.getTitle());
    article.setDescription(articleDTO.getDescription());
    article.setArticleUrl(articleDTO.getArticleUrl());
    article.setUrlToImage(articleDTO.getUrlToImage());
    article.setPublishedAt(articleDTO.getPublishedAt());
    article.setContent(articleDTO.getContent());
    article.setSource(source);

    recommendedArticleService.deleteArticleFromRecommendedList(article);
  }
}
