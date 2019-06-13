import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Article } from '../../article';
import { MatDialog } from '@angular/material';
import { DialogComponent } from '../../components/dialog/dialog.component';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input()
  article: Article;

  @Input()
  favouriteData: boolean;

  @Input()
  recommendedData: boolean;


  @Output()
  addToFavouriteList = new EventEmitter();

  @Output()
  deleteFromFavouriteList=  new EventEmitter();

  @Output()
  updateComments = new EventEmitter();

  constructor(
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    console.log('favouriteData', this.favouriteData);
  }

  readNews() {
    console.log("inside readnews!!!");
    window.open(this.article.url,'_blank');
    // window.location.href = this.article.url;

  }

  addButtonClick(article) {
    console.log('Card component', article);
    this.addToFavouriteList.emit(article);
  }

  deleteButtonClick(article) {
    console.log('Card component delete button clicked ', article);
    this.deleteFromFavouriteList.emit(article);
  }

  addComments() {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '250px',
      data: {comments: this.article.comments}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log("The Result is ", result);
      if(typeof result != 'undefined' && result) {
        this.article.comments = result;
        this.updateComments.emit(this.article);
    }
    });  
  }

}
