import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-test-wo-jwt',
  imports: [CommonModule],
  templateUrl: './test-wo-jwt.html',
  styleUrls: ['./test-wo-jwt.scss']
})
export class TestWoJwt {

  testResponse: any;
  

  constructor(private http: HttpClient) {}

  testWOjwt() {
    this.http.get('http://localhost:8081/auth/testNoJwtJson', { responseType: 'text' }
    ).subscribe(response => {

      this.testResponse = response;

      
    });
  }

}
