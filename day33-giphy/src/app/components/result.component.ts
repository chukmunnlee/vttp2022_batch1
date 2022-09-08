import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { GiphyService } from '../services/giphy.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit, OnDestroy {

  images: string[] = []

  sub$!: Subscription

  constructor(private giphySvc: GiphyService) { }

  ngOnInit(): void {
    this.sub$ = this.giphySvc.onNewResult.subscribe(
      urls => {
        this.images = urls
      }
    )
  }

  ngOnDestroy(): void {
      this.sub$.unsubscribe()
  }

}
