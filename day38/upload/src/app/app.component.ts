import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDrawer, MatDrawerContainer } from '@angular/material/sidenav';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  @ViewChild(MatDrawer)
  drawer!: MatDrawer

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
  }

  toggleSidebar() {
    this.drawer.toggle()
  }
}
