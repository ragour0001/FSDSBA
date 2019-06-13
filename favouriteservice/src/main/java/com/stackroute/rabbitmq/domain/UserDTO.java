package com.stackroute.rabbitmq.domain;

import java.util.List;

public class UserDTO {

  private String userName;
  private String email;
  private String password;
  private List<ArticleDTO> articleList;


  public UserDTO() {
  }

  public UserDTO(String userName, String email, String password) {
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  public List<ArticleDTO> getArticleList() {
    return articleList;
  }

  public void setArticleList(List<ArticleDTO> articleList) {
    this.articleList = articleList;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
      "userName='" + userName + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
