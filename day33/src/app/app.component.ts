import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { lastValueFrom, take } from 'rxjs';
import { Todo } from './models';
import { TodoService } from './services/todo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private todoSvc: TodoService) { }

  getData() {
    this.todoSvc.getTodo(Math.floor(Math.random() * 20) + 1)
      .then(this.take10)
      .then(this.processTodo)
      .catch(err => {
        console.error('error: ', err)
      })
  }

  completedTasks(todo: Todo[]) {
    return todo.filter(v => v.completed)
  }

  take10(todo: Todo[]) {
    return todo.slice(0, 10)
  }
  processTodo(todo: Todo[]) {
    console.info('>>> processing todos: ', todo)
  }
}
