import { Component, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';

import { LineItem, Order } from '../models'

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  @Output()
  onNewOrder = new Subject<Order>()

  form!: FormGroup
  lineItems!: FormArray

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    // @ts-ignore
    this.form = this.createOrder(null)
  }

  addItem() {
    this.lineItems.push(this.createLineItem())
  }

  removeItem(i: number) {
    this.lineItems.removeAt(i)
  }

  saveOrder() {
    const order: Order = this.form.value as Order
    this.form = this.createOrder()
    console.info(">>>> order: ", order)
    this.onNewOrder.next(order)
  }

  createOrder(): FormGroup {
    this.lineItems = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>(''),
      mobile: this.fb.control<string>(''),
      lineItems: this.lineItems
    })
  }

  createLineItems(items: LineItem[] = []): FormArray {
    //return this.fb.array(items.map(li => this.createLineItem(li)))
    return this.fb.array([])
  }

  createLineItem(): FormGroup {
    return this.fb.group({
      item: this.fb.control<string>(''),
      quantity: this.fb.control<number>(1)
    })
  }
}
