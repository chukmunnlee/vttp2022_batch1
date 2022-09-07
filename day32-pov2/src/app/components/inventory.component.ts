import { Component, Input, OnInit, Output } from '@angular/core';
import {Subject} from 'rxjs';
import {OrderDB} from '../models';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

	@Input()
	ordersDB!: OrderDB

	@Output()
	onEdit = new Subject<string>()

	constructor() { }

	ngOnInit(): void { }

	edit(key: string) {
		this.onEdit.next(key)
	}

}
