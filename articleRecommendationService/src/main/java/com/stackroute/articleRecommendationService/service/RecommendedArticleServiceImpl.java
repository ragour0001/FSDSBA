package com.stackroute.articleRecommendationService.service;


import com.stackroute.articleRecommendationService.domain.Article;
import com.stackroute.articleRecommendationService.exception.ArticleNotFoundException;
import com.stackroute.articleRecommendationService.repository.RecommendedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RecommendedArticleServiceImpl implements RecommendedArticleService {



    private RecommendedArticleRepository recommendedArticleRepository;

    @Autowired
    public RecommendedArticleServiceImpl(RecommendedArticleRepository recommendedArticleRepository) {

        this.recommendedArticleRepository = recommendedArticleRepository;

    }

    //This method will save the recommended article using rabbitmq
    @Override
    public Article saveArticleToRecommendedList(Article article) throws Exception {
        int count = 1;

        Article fetchArticle = recommendedArticleRepository.findByArticleId(article.getArticleId());

        if(fetchArticle != null) {
            count = fetchArticle.getCount() + 1;
        }

        article.setCount(count);
        recommendedArticleRepository.save(article);

        return article;

    }

    @Override
    public Article deleteArticleFromRecommendedList(Article article) throws ArticleNotFoundException {

        int count = 0;

        Article fetchArticle = recommendedArticleRepository.findByArticleId(article.getArticleId());

        if(fetchArticle != null) {
            count = fetchArticle.getCount() - 1;

            if(count <= 0) {
                recommendedArticleRepository.delete(article);
            }
            else {
                fetchArticle.setCount(count);
                recommendedArticleRepository.save(fetchArticle);
            }

        }
        else {
            throw new ArticleNotFoundException();
        }

        return fetchArticle;
    }
    

    @Override
    public List<Article> getAllArticleFromRecommendedList() throws Exception {

        return recommendedArticleRepository.findAll();
    }

}
