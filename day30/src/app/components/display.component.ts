import { Component, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {

  @Input()
  image = "/assets/bulldog_0.jpg"

  @Input()
  caption = "Bulldog puppy"

  @Output()
  clicked = new Subject<string>()

  addToCart = true
  fontSize = "1em"
  styleClass = "thumbnail-0"

  private count = 0

  constructor() { }

  ngOnInit(): void { }

  enter() {
    this.fontSize = "2em"
    this.addToCart = false
  }
  exit() {
    this.fontSize = "1em"
    this.addToCart = true
  }

  process() {
    // Send the notification out
    console.info('button clicked')
    this.clicked.next(this.image)
  }

}
