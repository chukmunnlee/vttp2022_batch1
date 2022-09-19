import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-num-list',
  templateUrl: './num-list.component.html',
  styleUrls: ['./num-list.component.css']
})
export class NumListComponent implements OnInit {

  nums: number[] = []

  constructor(private title: Title) { }

  ngOnInit(): void {
    this.title.setTitle("List of numbers")
    for (let i = 0; i < 31; i++)
      this.nums.push(i)
  }

}
