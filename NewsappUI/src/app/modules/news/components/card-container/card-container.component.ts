import { Component, OnInit } from '@angular/core';

import { Article } from './../../article';
import { Source } from './../../source';
import { NewsService } from '../../news.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from "@angular/material";

@Component({
  selector: 'app-card-container',
  templateUrl: './card-container.component.html',
  styleUrls: ['./card-container.component.css']
})
export class CardContainerComponent implements OnInit {

  articles: Array<Article>;
  articleObj: Article;
  sourceObj: Source;
  countryName: string;
  id: number;
  statusCode: number;
  errorStatus: string;
  sourceName: string;
  titleName: string;
  searchArticles: Array<Article>;

  constructor(
    private newsService: NewsService,
    private routes: ActivatedRoute,
    private matSnackbar: MatSnackBar
  ) { 
    this.articles = [];
  }

  ngOnInit() {
    const tempData = this.routes.data.subscribe(newdata => {
      this.countryName = newdata.country;
      console.log('country name is : ', this.countryName);
    });
   
    this.newsService.getArticleDetails(this.countryName).subscribe(articles => {
      console.log(articles);
      this.articles = [];
      const data = articles['articles'];
      this.id = 0;

      data.forEach(targetData => {
        this.id ++;
        this.articleObj = new Article();
        this.sourceObj = new Source();
        this.sourceObj = targetData["source"];
        this.articleObj = targetData;
        this.articleObj.source = this.sourceObj;
        this.articleObj.articleId = this.countryName.slice(0, 3) + this.id;
        
       // this.articleObj.articleId = this.articleObj.title;

        this.articles.push(this.articleObj);
        this.searchArticles = this.articles;
      });
    });
  }

  onKey(event: any) {
    this.titleName = event.target.value;
    console.log("Article titleName  : ", this.titleName);

    const result = this.searchArticles.filter(article => {
      return article.title.match(this.titleName);
    });
    console.log(result, 'Filtered data');
    this.articles = result;
  }

  addToFavouriteList(article) {
    console.log('inside card container component', article);

    this.newsService.addArticleToFavouriteList(article).subscribe(
      data => {
      console.log('response : ', data);

      this.statusCode = data.status;
      if(this.statusCode === 201) {
        console.log("Success", this.statusCode);
        this.matSnackbar.open("Article Successfully added!!!", " ", {
          duration: 1000
        });
        // this.newsService.addArticleToRecommendedList(article).subscribe(
        //  saveRecomdata => {
        // console.log('recommended Article response : ', saveRecomdata);
        //  });
      }
    },
    error => {
      this.errorStatus = `${error.status}`;
      const errorMsg = `${error.error.message}`;
      this.statusCode = parseInt(this.errorStatus, 10);
      if(this.statusCode === 409) {
        this.matSnackbar.open(errorMsg, "", {
          duration: 1000
        });
        this.statusCode = 0;
      }
    });

  }

}
