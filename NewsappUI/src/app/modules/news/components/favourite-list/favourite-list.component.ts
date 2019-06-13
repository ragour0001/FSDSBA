import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../news.service';
import { MatSnackBar } from '@angular/material';
import { Article } from '../../article';

@Component({
  selector: 'app-favourite-list',
  templateUrl: './favourite-list.component.html',
  styleUrls: ['./favourite-list.component.css']
})
export class FavouriteListComponent implements OnInit {

  articles: Array<Article>;
  favouriteData = true;
  statuscode: number;

  constructor(
    private newsService: NewsService,
    private matSnackbar: MatSnackBar
  ) { }

  ngOnInit() {
    const message = "FavouriteList is empty";
    this.newsService.getAllArticlesforFavouriteList().subscribe(data => {
      this.articles = data;
      if(data == null || data.length === 0) {
        this.matSnackbar.open(message, " ", {
          duration: 1000
        });
      }
    });
  }

  deleteFromFavouriteList(article) {
    this.newsService.deleteArticleFromFavouriteList(article).subscribe(data => {
      console.log("Inside wishList component : deleted track :", data);
      const index = this.articles.indexOf(article);
      this.articles.splice(index,1);
      this.matSnackbar.open("successfully deleted", " ", {
        duration: 1000
      });
      //  this.newsService.deleteArticleFromRecommendedList(article).subscribe(
      //    deleteRecomdata => {
      //   console.log('recommended Article response : ', deleteRecomdata);
      //    });
    });

    return this.articles;
  }

  updateComments (article) {
    this.newsService.updateComments(article).subscribe(
      data => {
        console.log("update Comments data", data);
        this.matSnackbar.open("Successfully updated", " ", {
          duration: 1000
        });
      },
      error => {
        console.log("error", error);
      }
    );
  }


}
