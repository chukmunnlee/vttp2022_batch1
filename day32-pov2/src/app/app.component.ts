import { Component, OnInit } from '@angular/core';
import { v4 } from 'uuid'

import { Order, OrderDB } from './models'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

	orderDB: OrderDB = {}

	ngOnInit(): void {
	}

	processNewOrder(newOrder: Order) {
		const orderId = v4().substring(0, 8)
		newOrder.orderId = orderId
		this.orderDB = { ...this.orderDB, newOrder }
		console.info("... db: ", this.orderDB)
	}
}
