import { Component } from '@angular/core';
import { Order } from './models';
import { v4 } from 'uuid'

import { orderDB } from './models'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  version: number = 0

  processNewOrder(order: Order) {
    order.orderId = v4().substring(0, 8)
    orderDB[order.orderId] = order
    this.version++
  }
}
