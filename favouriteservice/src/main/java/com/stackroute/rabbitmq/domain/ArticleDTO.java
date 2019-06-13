package com.stackroute.rabbitmq.domain;


public class ArticleDTO {


    private String articleId;

    private String comments;

    private  String author;

    private String title;

    private String description;

    private String articleUrl;

    private String urlToImage;

    private String publishedAt;

    private String content;

    private SourceDTO source;

    public ArticleDTO() {
    }

    public ArticleDTO(String articleId, String comments, String author, String title, String description, String articleUrl, String urlToImage, String publishedAt, String content, SourceDTO source) {
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

    public SourceDTO getSource() {
        return source;
    }

    public void setSource(SourceDTO source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
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
