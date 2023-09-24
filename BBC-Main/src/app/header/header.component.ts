import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  expandedAbbreviation: string = 'BBC';
  constructor() { }

  ngOnInit(): void {
  }

  expandAbbreviation() {
    this.expandedAbbreviation = 'Bharat Bijlee Corporation';
  }

  resetAbbreviation() {
    this.expandedAbbreviation = 'BBC';
  }
  
}
