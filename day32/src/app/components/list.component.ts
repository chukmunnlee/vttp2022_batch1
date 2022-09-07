import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  @Input()
  set value(n: number) {
    this._value = n
    console.info("setting value: ", this._value)
  }
  get value(): number {
    return this._value
  }

  @Input()
  set numList(n: number[]) {
    this._numlist = n
    console.info("setting numList: ", this._numlist)
    this._value = this._numlist.reduce((acc, v) => acc + v)
  }
  get numList(): number[] {
    return this._numlist
  }

  _value: number = 0
  _numlist: number[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
