import {Component, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-treatment',
  templateUrl: './treatment.component.html',
  styleUrls: ['./treatment.component.css']
})
export class TreatmentComponent implements OnInit {
  patientId = new FormControl('');
  treatment = new FormControl('');

  constructor(private http: HttpClient) {
  }


  ngOnInit(): void {
  }


  enroll() {

    const url = 'http://localhost:8090/patients/' + this.patientId.value + '/workflows';
    const body = {
      "treatmentId": this.treatment.value
    };
    this.http.post<any>(url, body).subscribe({
      next: data => {
        console.info('Response: ', data);
        this.resetFields();
      },
      error: error => {
        console.error('There was an error!', error);
        this.resetFields();
      }
    })


  }

  private resetFields() {
    this.patientId = new FormControl('');
    this.treatment = new FormControl('');

  }
}
