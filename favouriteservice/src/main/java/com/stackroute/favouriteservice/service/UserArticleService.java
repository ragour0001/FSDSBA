package com.stackroute.favouriteservice.service;



import com.stackroute.favouriteservice.domain.Article;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.ArticleAlreadyExistsException;
import com.stackroute.favouriteservice.exception.ArticleNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserArticleService {

    User saveUserArticleToFavouriteList(Article article, String userName) throws ArticleAlreadyExistsException;

    User deleteUserArticleFromFavouriteList(String userName, String articleId) throws ArticleNotFoundException;

    User updateCommentForArticle(String comments, String articleId, String userName) throws ArticleNotFoundException;

    List<Article> getAllUserArticleFromFavouriteList(String userName) throws Exception;

    User registerUser(User user) throws UserAlreadyExistsException;
}
