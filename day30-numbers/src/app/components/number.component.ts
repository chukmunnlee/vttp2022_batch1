import { Component, Input, OnInit, Output, ɵpatchComponentDefWithScope } from '@angular/core';
import { ConnectableObservable, Subject } from 'rxjs';

@Component({
  selector: 'app-number',
  templateUrl: './number.component.html',
  styleUrls: ['./number.component.css']
})
export class NumberComponent implements OnInit {

  @Input()
  set current(n: number) {
    console.info(">>>> set: ", n)
    this._current = n
    this.updateImage(n)
  }
  get current(): number {
    return this._current
  }

  private _current = 0

  @Output()
  onImageClicked = new Subject<number>()

  image!: string
  //image = `/assets/numbers/number${this.current}.jpg`

  constructor() { }

  ngOnInit(): void {
    this.updateImage(this._current)
  }

  imageClicked() {
    console.info(`---- current: ${this._current}`)
    this.onImageClicked.next(this._current)
  }

  prev() {
    this._current--
    if (this._current < 0)
      this._current = 30
    this.updateImage(this._current)
  }

  next() {
    this._current++
    //this.current = this.current % 31
    this._current %= 31
    this.updateImage(this._current)
  }

  private updateImage(n: number) {
    console.info(">>> number: ", n)
    this.image = `/assets/numbers/number${n}.jpg`
  }

}
