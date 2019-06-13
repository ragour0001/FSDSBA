package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.favouriteservice.domain.Article;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.ArticleAlreadyExistsException;
import com.stackroute.favouriteservice.exception.ArticleNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistsException;
import com.stackroute.favouriteservice.repository.UserArticleRepository;
import com.stackroute.rabbitmq.domain.ArticleDTO;
import com.stackroute.rabbitmq.domain.SourceDTO;
import com.stackroute.rabbitmq.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserArticleServiceImpl implements UserArticleService {

   private Producer producer;

    private UserArticleRepository userArticleRepository;

    @Autowired
    public UserArticleServiceImpl(UserArticleRepository userArticleRepository, Producer producer) {

        this.userArticleRepository = userArticleRepository;
        this.producer = producer;
    }

    // This method will register the new User
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        User fetchedUserObj = userArticleRepository.findByUserName(user.getUserName());
        if (fetchedUserObj != null) {
            throw new UserAlreadyExistsException();
        }
        else {
            userArticleRepository.save(user);
            producer.sendMessageToRabbitMq(userDTO);

        }

        return user;
    }

    //This method will save the favourite article
    @Override
    public User saveUserArticleToFavouriteList(Article article, String userName) throws ArticleAlreadyExistsException {

        User fetchUser = userArticleRepository.findByUserName(userName);

        List<Article> fetchArticles = fetchUser.getArticleList();

        ArticleDTO articleDTO = new ArticleDTO();
        SourceDTO sourceDTO = new SourceDTO();
        sourceDTO.setSourceId(article.getSource().getSourceId());
        sourceDTO.setSourceName(article.getSource().getSourceName());

        articleDTO.setArticleId(article.getArticleId());
        articleDTO.setComments(article.getComments());
        articleDTO.setAuthor(article.getAuthor());
        articleDTO.setArticleUrl(article.getArticleUrl());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setUrlToImage(article.getUrlToImage());
        articleDTO.setContent(article.getContent());
        articleDTO.setPublishedAt(article.getPublishedAt());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setSource(sourceDTO);

        if (fetchArticles != null) {
            for (Article articleObj : fetchArticles) {
                if (articleObj.getArticleId().equals(article.getArticleId()))
                {
                    throw new ArticleAlreadyExistsException();
                }
            }

            fetchArticles.add(article);
            fetchUser.setArticleList(fetchArticles);

//          UserDTO userDTO = new UserDTO();
//          userDTO.setUserName(userName);
//          userDTO.setEmail(fetchUser.getEmail());


          List list = new ArrayList();
          list.add(fetchArticles);
        //  userDTO.setArticleList(list);

            userArticleRepository.save(fetchUser);

            producer.sendMessageToRabbitMqArticleObject(articleDTO);
        }
        else {
            fetchArticles = new ArrayList<>();
            fetchArticles.add(article);
            fetchUser.setArticleList(fetchArticles);

//            UserDTO userDTO = new UserDTO();
//            userDTO.setUserName(userName);
//            userDTO.setEmail(fetchUser.getEmail());
            List list = new ArrayList();
            list.add(fetchArticles);
           // userDTO.setArticleList(list);

            userArticleRepository.save(fetchUser);

            producer.sendMessageToRabbitMqArticleObject(articleDTO);

        }

        return fetchUser;
    }

    //This method will delete the user's article from favourite article list
    @Override
    public User deleteUserArticleFromFavouriteList(String userName, String articleId) throws ArticleNotFoundException {

        User fetchUser = userArticleRepository.findByUserName(userName);

        List<Article> fetchArticles = fetchUser.getArticleList();


        if (fetchArticles.size() > 0) {
            for (int i = 0; i < fetchArticles.size(); i++) {
                if (articleId.equals(fetchArticles.get(i).getArticleId())) {

                    Article article = fetchArticles.get(i);
                    ArticleDTO articleDTO = new ArticleDTO();
                    SourceDTO sourceDTO = new SourceDTO();
                    sourceDTO.setSourceId(article.getSource().getSourceId());
                    sourceDTO.setSourceName(article.getSource().getSourceName());

                    articleDTO.setArticleId(article.getArticleId());
                    articleDTO.setComments(article.getComments());
                    articleDTO.setAuthor(article.getAuthor());
                    articleDTO.setArticleUrl(article.getArticleUrl());
                    articleDTO.setTitle(article.getTitle());
                    articleDTO.setUrlToImage(article.getUrlToImage());
                    articleDTO.setContent(article.getContent());
                    articleDTO.setPublishedAt(article.getPublishedAt());
                    articleDTO.setDescription(article.getDescription());
                    articleDTO.setSource(sourceDTO);

                    fetchArticles.remove(i);

                    fetchUser.setArticleList(fetchArticles);
                    userArticleRepository.save(fetchUser);
                    producer.sendMessageToRabbitMqArticleObjectForDelete(articleDTO);
                    break;
                }
            }
        }
        else {
            throw new ArticleNotFoundException();
        }
        return fetchUser;
    }

    @Override
    public User updateCommentForArticle(String comments, String articleId, String userName) throws ArticleNotFoundException {

        User fetchUser = userArticleRepository.findByUserName(userName);
        List<Article> fetchList = fetchUser.getArticleList();
        if (fetchList.size() > 0)
        {
            for (int i = 0; i < fetchList.size(); i++)
            {
                if (articleId.equals(fetchList.get(i).getArticleId()))
                {
                    fetchList.get(i).setComments(comments);

                    userArticleRepository.save(fetchUser);
                    break;
                }
            }
        }
        else {
            throw new ArticleNotFoundException();
        }
        return fetchUser;
    }

    @Override
    public List<Article> getAllUserArticleFromFavouriteList(String userName) throws Exception {
        User user = userArticleRepository.findByUserName(userName);

        return user.getArticleList();
    }


}
