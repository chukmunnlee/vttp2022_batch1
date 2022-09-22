import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-number',
  templateUrl: './number.component.html',
  styleUrls: ['./number.component.css']
})
export class NumberComponent implements OnInit, OnDestroy {

  num = 0
  sub$!: Subscription

  constructor(private ar: ActivatedRoute) { }

  ngOnInit(): void {
    console.info('>>>> in ngOnInit = ', this.ar.snapshot.params['num'])
    if (this.ar.snapshot.params['num']) {
      this.num = this.ar.snapshot.params['num']
      this.sub$ = this.ar.params.subscribe(v => {
        console.info('>subscribe: ', v)
        // @ts-ignore
        this.num = v.num
      })
    }
  }

  ngOnDestroy(): void {
      this.sub$.unsubscribe()
  }

}
