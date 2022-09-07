import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';

import { LineItem, Order, orderDB } from '../models'

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

  @Input()
  set version(n: number) {
    this.orderIds = []
    for (let k in orderDB)
      this.orderIds.push(k)
  }

  orderIds: string[] = []
  orders = orderDB

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {

  }


}
