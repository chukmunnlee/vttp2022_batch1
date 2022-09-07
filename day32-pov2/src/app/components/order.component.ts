import { Component, Input, OnInit, Output } from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {Subject} from 'rxjs';
import {LineItem, Order} from '../models';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

	@Input()
	order!: Order

	@Output()
	onNewOrder = new Subject<Order>()

	orderForm!: FormGroup
	lineItemsArray!: FormArray

  constructor(private fb: FormBuilder) { }

	ngOnInit(): void { 
		this.orderForm = this.createOrder(this.order)
	}

	addItem() {
		this.lineItemsArray.push(this.createLineItem())
	}
	removeItem(idx: number) {
		this.lineItemsArray.removeAt(idx)
	}

	processOrder() {
		const order: Order = this.orderForm.value as Order
		console.info('>>> order: ', order)
		this.orderForm = this.createOrder()
		this.onNewOrder.next(order)
	}

	private createOrder(order?: Order): FormGroup {
		this.lineItemsArray = this.createLineItems(order?.lineItems || [])
		return this.fb.group({
			name: this.fb.control<string>(order?.name || ''),
			mobile: this.fb.control<string>(order?.mobile || ''),
			lineItems: this.lineItemsArray
		})
	}
	private createLineItems(lis: LineItem[] = []): FormArray {
		return this.fb.array(lis.map(li => this.createLineItem(li)))
	}
	private createLineItem(li?: LineItem): FormGroup {
		return this.fb.group({
			// @ts-ignore
			item: this.fb.control<string>(li?.item || ''),
			quantity: this.fb.control<number>(li?.quantity || 1),
		})
	}


}
