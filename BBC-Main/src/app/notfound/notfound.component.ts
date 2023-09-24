import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-notfound',
  templateUrl: './notfound.component.html',
  styleUrls: ['./notfound.component.css']
})
export class NotfoundComponent implements OnInit {

  constructor(private service: HttpService, private router: Router) { }

  ngOnInit(): void {
  }
 onClick() {
    this.router.navigate(['']);
  }

}
