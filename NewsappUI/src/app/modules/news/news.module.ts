import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularmaterialModule } from '../angularmaterial/angularmaterial.module';
import { AppRoutingModule } from '../../app-routing.module';
import { CardContainerComponent } from './components/card-container/card-container.component';
import { CardComponent } from './components/card/card.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { FavouriteListComponent } from './components/favourite-list/favourite-list.component';
import { DialogComponent } from './components/dialog/dialog.component';
import { NewsService } from '../news/news.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './interceptor.service';
import { RecommededListComponent } from './components/recommeded-list/recommeded-list.component';




@NgModule({
  declarations: [
    CardContainerComponent, 
    CardComponent, 
    HeaderComponent, 
    FooterComponent, 
    FavouriteListComponent, 
    DialogComponent, 
    RecommededListComponent
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    AngularmaterialModule,
    AppRoutingModule    
  ],
  exports: [
    CardContainerComponent,
    AppRoutingModule,
    HeaderComponent,
    FooterComponent,
    FavouriteListComponent,
    RecommededListComponent
  ],
  providers: [
    NewsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ],
  entryComponents: [
    DialogComponent
  ],
})
export class NewsModule { }
