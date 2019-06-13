import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../news.service';
import { MatSnackBar } from '@angular/material';
import { Article } from '../../article';

@Component({
  selector: 'app-recommeded-list',
  templateUrl: './recommeded-list.component.html',
  styleUrls: ['./recommeded-list.component.css']
})
export class RecommededListComponent implements OnInit {

  articles: Array<Article>;
  favouriteData = false;
  recommendedData = true;

  constructor(
    private newsService: NewsService,
    private matSnackbar: MatSnackBar
  ) { }

  ngOnInit() {
    const message = "RecommendedList is empty";
    this.newsService.getAllArticlesforRecommendedList().subscribe(data => {
      this.articles = data;
      if(data == null || data.length === 0) {
        this.matSnackbar.open(message, " ", {
          duration: 1000
        });
      }
    });
  }


}
