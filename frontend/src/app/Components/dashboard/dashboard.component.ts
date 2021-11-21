import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/Models/Employee';
import { EmployeeService } from 'src/app/Services/employee.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  employee:Employee = new Employee(0,'','','','','');

  constructor(
    private router:Router,
    private employeeService:EmployeeService
  ) { }

  ngOnInit(): void {
    this.isLoggedIn()
  }

  isLoggedIn(){
    let employeeString:string|null = sessionStorage.getItem("employee")
    if(employeeString){
      this.employee = JSON.parse(employeeString)
      this.employeeService.getEmployee(this.employee.id).subscribe(data=>{
        this.employee = data
        sessionStorage.setItem('employee',JSON.stringify(this.employee))
      },error=>{
        if(error.status==400){
          alert(error)
        }
      })
    }else{
      alert("Unauthorized access! Please login")
      this.router.navigate(['','login'])
    }
  }

  updateEmployee(){
    this.employeeService.updateEmployee(this.employee).subscribe(data=>{
      alert(data)
      sessionStorage.setItem('employee',JSON.stringify(this.employee))
    },error=>{
      if(error.status == 400){
        alert(error)
      }
    })
  }

}
