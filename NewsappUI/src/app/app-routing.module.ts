import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardContainerComponent } from './modules/news/components/card-container/card-container.component';
import { FavouriteListComponent } from './modules/news/components/favourite-list/favourite-list.component';
import { RegisterComponent } from './modules/authentication/components/register/register.component';
import { LoginComponent } from './modules/authentication/components/login/login.component';
import { AuthGuardService } from './modules/news/auth-guard.service';
import { RecommededListComponent } from './modules/news/components/recommeded-list/recommeded-list.component';

const routes: Routes = [
  {
    path: "Australia",
    component: CardContainerComponent,
    data : {country : "au"}
  },
  {
    path: "",
    component: LoginComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "logout",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "India",
    component: CardContainerComponent,
    data : {country : "in"},
    canActivate: [AuthGuardService]
  },
  {
    path: "USA",
    component: CardContainerComponent,
    data : {country : "us"},
    canActivate: [AuthGuardService]
  },

  {
    path: "FavouriteList",
    component: FavouriteListComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: "RecommendedList",
    component: RecommededListComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
