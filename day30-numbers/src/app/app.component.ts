import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  current = 0
  selectedNumbers: number[] = []
  selectedNumberText!: string

  ngOnInit(): void {
    this.reset()
  }

  imageClicked(n: number) {
    console.info(">>>> selected: ", n)
    this.selectedNumbers.push(n)
    this.selectedNumberText = this.selectedNumbers.join(", ")
  }

  reset() {
    this.current = Math.floor(Math.random() * 31)
    this.selectedNumberText = "No selection yet"
  }
}
