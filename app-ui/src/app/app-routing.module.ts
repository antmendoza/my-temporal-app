import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TreatmentComponent} from "./treatment/treatment.component";
import {TasksComponent} from "./tasks/tasks.component";

const routes: Routes = [
  { path: 'workflows', component: TreatmentComponent },
  { path: 'tasks', component: TasksComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
