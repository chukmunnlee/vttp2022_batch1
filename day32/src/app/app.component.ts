import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { TodoComponent } from './components/todo.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {

  @ViewChild(TodoComponent)
  todoComp!: TodoComponent

  @ViewChild('todo')
  todoElemRef!: ElementRef

  @ViewChildren(TodoComponent)
  todoComps!: QueryList<TodoComponent>

  value: number = 3
  numList: number[] = []

  ngOnInit(): void {
    console.info('>>> onInit todoComp:', this.todoComp)
    for (let i = 0; i < 5; i++)
      this.numList.push(Math.floor(Math.random() * 100))
  }

  ngAfterViewInit(): void {
    console.info('>>> ngAfterViewInit todoComp:', this.todoComp)
    console.info('>>> ngAfterViewInit todoElemRef:', this.todoElemRef.nativeElement)
    console.info('>>> ngAfterViewInit todoComps:', this.todoComps.length)
  }

  saveTodo() {
    const todo = this.todoComp.getValues()
    console.info('>>>> saveTodo:', todo)
  }

  randomize() {
    this.value = Math.floor(Math.random() * 100)
    // Create a new array
    const tmp: number[] = [ ...this.numList, this.value ]
    // Assign the new array to the binding
    this.numList = tmp
  }
}
