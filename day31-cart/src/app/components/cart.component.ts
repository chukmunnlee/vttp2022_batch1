import { Component, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Subject } from 'rxjs';
import { Item } from '../models';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  @Input()
  set cart(c: Item[]) {
    this._cart = c
    this.calculateTotal()
  }

  _cart: Item[] = []
  total = 0.0

  @Output()
  onDeleteItem = new Subject<number>()

  constructor() { }

  ngOnInit(): void {
    this.calculateTotal()
  }

  deleteItem(idx: number) {
    console.info('>>> index: ', idx)
    this.onDeleteItem.next(idx)
  }

  private calculateTotal() {
    this.total = 0
    console.info('>>>> in calculate total')
    for (let i of this._cart) {
      this.total += i.quantity * i.unitPrice
    }
  }

}
