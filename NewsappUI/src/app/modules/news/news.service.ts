import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from './article';
import { USER_NAME } from '../authentication/authentication.service';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NewsService {


  thirdPartyApi: string;
  apiKey: string;
  springEndPoint: string;
  recommendSpringPoint: string;
  userName: string;

  constructor(private httpClient: HttpClient) { 

    // this.thirdPartyApi = 'https://newsapi.org/v2/top-headlines?country=us&category=business';
    this.thirdPartyApi = 'https://newsapi.org/v2/top-headlines?country=';
    this.apiKey = '&apiKey=ad08af8b20ec41eca1171fb6b11e6f14';
    // this.springEndPoint = 'http://localhost:8072/api/v1/favouriteservice/';
    this.springEndPoint = 'http://localhost:8073/favouriteservice/api/v1/favouriteservice/';
    this.recommendSpringPoint = 'http://localhost:8073/articleRecommendationService/api/v1/recommendedservice/';
    // localhost:8073/favouriteservice/api/v1/favouriteservice/user/Sudheer/article
    // localhost:8073/articleRecommendationService/api/v1/recommendedservice/save 
    //localhost:8073/articleRecommendationService/api/v1/recommendedservice/article
   
  }

  getArticleDetails(country: string): Observable<any> {
    const url = `${this.thirdPartyApi}${country}${this.apiKey}`;

    return this.httpClient.get(url);
  }

  addArticleToFavouriteList(article: Article) {
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + "user/" + this.userName + "/article";
    console.log("addArticleToFavList url: " + url);
    return this.httpClient.post(url, article, {
      observe: "response"
    });
  }

  // addArticleToRecommendedList(article: Article) {
  //   this.userName = sessionStorage.getItem(USER_NAME);
  //   const url = this.recommendSpringPoint + "/save";
  //   console.log("addArticleToRecommendedList url: " + url);
  //   return this.httpClient.post(url, article, {
  //     observe: "response"
  //   });
  // }

  getAllArticlesforFavouriteList(): Observable<Article[]> {
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + "user/" + this.userName + "/articles";
    
    return this.httpClient.get<Article[]>(url);
  }

  getAllArticlesforRecommendedList(): Observable<Article[]> {
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.recommendSpringPoint + "/articles";
    
    return this.httpClient.get<Article[]>(url);
  }

  deleteArticleFromFavouriteList(article: Article) {
    this.userName = sessionStorage.getItem(USER_NAME);
    // const url = this.springEndPoint + "user/" + this.userName + "/article";
    const url = this.springEndPoint + "user/" + this.userName + "/" + article.articleId;
    
  //   const options = {
  //    headers: new HttpHeaders({
  //      'Content-Type': 'application/json',
  //    }),
  //    body: article
  //  };
   console.log("In delete :", article);
  // return this.httpClient.delete(url, options);
   return this.httpClient.delete(url);
  //  return this.httpClient.delete(url, { responseType: "text"});
  }

  // deleteArticleFromRecommendedList(article: Article) {
  //   console.log("inside deleteArticleFromRecommendedList");
  //   this.userName = sessionStorage.getItem(USER_NAME);
  //   // const url = this.springEndPoint + "user/" + this.userName + "/article";
  //   const url = this.recommendSpringPoint + "article";
  //  console.log("In recommended delete :", article);
  //  console.log("In recommended delete URL :", url);
  //  return this.httpClient.delete(url);
  
  // }

  updateComments(article) {
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.springEndPoint + "user/" + this.userName + "/article";
    return this.httpClient.patch(url, article, { observe: "response"});

    // const id = article.articleId;
    // const url = this.springEndPoint + "article/" + `${id}`;
    // return this.httpClient.put(url, article, { observe: "response"});
  }


  filterArtists(articles: Array<Article>, sourcetName: string) {
    const results = articles.filter(article => {
      return article.source.name.match(sourcetName);
    });
    console.log("Filtered data :", results);
    return results;
  }
  
}
