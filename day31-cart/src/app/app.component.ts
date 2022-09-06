import { Component } from '@angular/core';
import { Item } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  cart: Item[] = []

  newItem(newItem: Item) {
    console.info('>>>> newItem: ', newItem)
    this.cart = [ ...this.cart, newItem ]
  }

  itemDeleted(idx: number) {
    const tmp: Item[] = [ ...this.cart ]
    tmp.splice(idx, 1)
    this.cart = tmp
  }
}
