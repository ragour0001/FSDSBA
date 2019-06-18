import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
//import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { AuthenticationService } from '../../../authentication/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  enableLogout: boolean = true;

  // route = ['login' , 'register'];
  
  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) { }

  logout() {
    this.authService.logout();
     console.log("logout called!!!");
     this.router.navigate(["/login"]); 
     this.enableLogout = false;
  }

  ngOnInit() {
  }

}
