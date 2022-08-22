import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface UserTask {
  status: string;
  name: string;
  id: string;
}





@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  tasks: UserTask[] = [];
  displayedColumns: string[] = ['id', 'name', 'status', 'action'];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {

    const url = 'http://localhost:8090/tasks';
    this.http.get<any>(url).subscribe({
      next: data => {
        console.info('Response: ', data);
        this.tasks = data.tasks;
      },
      error: error => {
        console.error('There was an error!', error);
      }
    })
  }

  completeTask(id: string) {
    const url = 'http://localhost:8090/tasks/'+id;
    this.http.post<any>(url, {}).subscribe({
      next: data => {
        console.info('Response: ', data);
        this.tasks = data.tasks;
      },
      error: error => {
        console.error('There was an error!', error);
      }
    })
  }
}
