import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-greetings',
  templateUrl: './greetings.component.html',
  styleUrls: ['./greetings.component.css']
})
export class GreetingsComponent implements OnInit {

  @Input()
  name = "barney"

  myStyle = {'red-font': false, 'blue-font': false}

  constructor() {
    console.info('in constructor: ', this.name)
  }

  ngOnInit(): void {
    console.info('in ngOnInit: ', this.name)
    if ((this.name.length % 2) == 0)
      this.myStyle['red-font'] = true
    else
      this.myStyle['blue-font'] = true
  }

}
