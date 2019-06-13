package com.stackroute.favouriteservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;


public class Article {


    @Id
   private String articleId;

   private String comments;

   @JsonProperty("author")
   private  String author;

   @JsonProperty("title")
   private String title;

   @JsonProperty("description")
   private String description;

   @JsonProperty("url")
   private String articleUrl;

   @JsonProperty("urlToImage")
   private String urlToImage;

   @JsonProperty("publishedAt")
   private String publishedAt;

   @JsonProperty("content")
   private String content;

   private Source source;

    public Article() {
    }

    public Article(String articleId, String comments, String author, String title, String description, String articleUrl, String urlToImage, String publishedAt, String content, Source source) {
        this.articleId = articleId;
        this.comments = comments;
        this.author = author;
        this.title = title;
        this.description = description;
        this.articleUrl = articleUrl;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.source = source;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", comments='" + comments + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                ", source=" + source +
                '}';
    }
}
