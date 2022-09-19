import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  constructor() { }

  ngOnInit(): void {
    console.info('++++ ngOnInit')
  }

  ngOnDestroy(): void {
    console.info('---- ngOnDestroy')
  }

}
