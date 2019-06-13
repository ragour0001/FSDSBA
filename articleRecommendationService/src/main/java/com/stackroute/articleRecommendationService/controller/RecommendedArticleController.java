package com.stackroute.articleRecommendationService.controller;


import com.stackroute.articleRecommendationService.domain.Article;
import com.stackroute.articleRecommendationService.exception.ArticleNotFoundException;
import com.stackroute.articleRecommendationService.service.RecommendedArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/recommendedservice")
public class RecommendedArticleController {

    private RecommendedArticleService recommendedArticleService;
    private ResponseEntity responseEntity;

    public RecommendedArticleController() {
    }

    @Autowired
    public RecommendedArticleController(final RecommendedArticleService recommendedArticleService) {
        this.recommendedArticleService = recommendedArticleService;
    }

//    @PostMapping("/save")
//    public ResponseEntity<?> saveArticleToRecommendedList(@RequestBody Article article) throws Exception {
//
//        try {
//            recommendedArticleService.saveArticleToRecommendedList(article);
//            responseEntity = new ResponseEntity(article , HttpStatus.CREATED);
//        }
//        catch (Exception e) {
//            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//           // responseEntity = new ResponseEntity<>( "Error!!! Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return responseEntity;
//    }

//    @DeleteMapping("/article")
//    public ResponseEntity<?> deleteArticleFromRecommendedList(@RequestBody Article article) throws ArticleNotFoundException {
//
//        try {
//            recommendedArticleService.deleteArticleFromRecommendedList(article);
//
//            responseEntity = new ResponseEntity(article , HttpStatus.OK);
//        } catch (ArticleNotFoundException e) {
//            throw new ArticleNotFoundException();
//        } catch (Exception e) {
//            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//          //  responseEntity = new ResponseEntity( "Error!!! Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return responseEntity;
//    }

    @GetMapping("/articles")
    public ResponseEntity<?> getAllArticleFromRecommendedList() {

        try {
            responseEntity = new ResponseEntity(recommendedArticleService.getAllArticleFromRecommendedList(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
           // responseEntity = new ResponseEntity( "Error!!! Try after sometime" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

}
