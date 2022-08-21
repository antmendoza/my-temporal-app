import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TreatmentComponent} from "./treatment/treatment.component";

const routes: Routes = [
  { path: 'treatments', component: TreatmentComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
