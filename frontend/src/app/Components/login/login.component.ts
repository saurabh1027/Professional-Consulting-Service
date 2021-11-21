import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/Models/Employee';
import { EmployeeService } from 'src/app/Services/employee.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  employee:Employee = new Employee(0,'','','','','');

  constructor(
    private employeeService:EmployeeService,
    private router:Router
  ) { }

  ngOnInit(): void {
    sessionStorage.removeItem('employee')
  }

  login(){
    this.employeeService.login(this.employee.email,this.employee.password).subscribe(data=>{
      this.employee = data;
      sessionStorage.setItem('employee',JSON.stringify(this.employee));
      this.router.navigate(['','dashboard'])
    },error=>{
      if(error.status==401){
        alert("Password did not matched!")
      }else if(error.status == 400){
        alert("No record found with email "+this.employee.email)
      }
    })
  }

}