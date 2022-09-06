import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Item } from '../models';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  @Output()
  onNewItem = new Subject<Item>()

  form!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(3)]),
      unitPrice: this.fb.control<number>(.1, [ Validators.required, Validators.min(.1)]),
      quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1)])
    })
  }

  processForm() {
    const item: Item = this.form.value as Item
    console.info('>>> item: ', item)
    //this.form.reset()
    this.form = this.createForm()
    this.onNewItem.next(item)
  }

}
