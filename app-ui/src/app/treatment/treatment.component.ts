import { Component, OnInit } from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-treatment',
  templateUrl: './treatment.component.html',
  styleUrls: ['./treatment.component.css']
})
export class TreatmentComponent implements OnInit {
  patientId = new FormControl('');
  treatment =  new FormControl('');

  constructor() { }




  ngOnInit(): void {
  }


  enroll() {



  }
}
