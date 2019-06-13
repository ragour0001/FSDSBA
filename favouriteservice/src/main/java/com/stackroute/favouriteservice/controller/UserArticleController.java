package com.stackroute.favouriteservice.controller;


import com.stackroute.favouriteservice.domain.Article;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.ArticleAlreadyExistsException;
import com.stackroute.favouriteservice.exception.ArticleNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistsException;
import com.stackroute.favouriteservice.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/favouriteservice")
public class UserArticleController {

    private UserArticleService userArticleService;
    private ResponseEntity responseEntity;

    public UserArticleController() {
    }

    @Autowired
    public UserArticleController(UserArticleService userArticleService) {
        this.userArticleService = userArticleService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistsException {

        try {
            userArticleService.registerUser(user);
            responseEntity = new ResponseEntity<User>(user , HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }

        return responseEntity;
    }

    @PostMapping("/user/{userName}/article")
    public ResponseEntity<?> saveUserArticleToFavouriteList(@RequestBody Article article , @PathVariable("userName") String userName) throws ArticleAlreadyExistsException {
        try {
            User user = userArticleService.saveUserArticleToFavouriteList(article , userName);
            responseEntity = new ResponseEntity(user , HttpStatus.CREATED);
        } catch (ArticleAlreadyExistsException e) {
            throw new ArticleAlreadyExistsException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @DeleteMapping("user/{userName}/{articleId}")
    public ResponseEntity<?> deleteUserArticleFromFavouriteList(@PathVariable ("userName") String userName , @PathVariable ("articleId") String articleId) throws ArticleNotFoundException {

        try {
            User user = userArticleService.deleteUserArticleFromFavouriteList(userName , articleId);
            responseEntity = new ResponseEntity(user , HttpStatus.OK);
        } catch (ArticleNotFoundException e) {
            throw new ArticleNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PatchMapping("user/{userName}/article")
    public ResponseEntity<?> updateCommentForArticle( @RequestBody Article article , @PathVariable ("userName") String userName) throws ArticleNotFoundException {

        try {
            userArticleService.updateCommentForArticle(article.getComments() , article.getArticleId() , userName);
            responseEntity = new ResponseEntity(article , HttpStatus.OK);
        } catch (ArticleNotFoundException e) {
            throw new ArticleNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("user/{userName}/articles")
    public ResponseEntity<?> getAllUserArticleFromFavouriteList(@PathVariable ("userName") String userName) {

        try {
            responseEntity = new ResponseEntity(userArticleService.getAllUserArticleFromFavouriteList(userName), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
