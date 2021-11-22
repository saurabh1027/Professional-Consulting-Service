import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/Models/Employee';
import { EmployeeService } from 'src/app/Services/employee.service';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {
  employee:Employee = new Employee(0,'','','','Employee','',[])
  employees:Employee[] =[];
  newEmployee:Employee = new Employee(0,'','','','','',[])

  constructor(private employeeService:EmployeeService,private router:Router) { }

  ngOnInit(){
    this.isLoggedIn()
  }
  
  isLoggedIn(){
    let employeeString:string|null = sessionStorage.getItem("employee")
    if(employeeString){
      this.employee = JSON.parse(employeeString)
      this.employeeService.getEmployee(this.employee.id).subscribe(data=>{
        this.employee = data
        if(this.employee.role=='Manager')
          this.getEmployees();
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

  getEmployees(){
    this.employeeService.getEmployees().subscribe(data => {
      this.employees = data;
    });
  }

  deleteEmployees(eid:number){
    this.employeeService.deleteEmployee(eid).subscribe(data=>{
      alert(data);
      this.getEmployees();
    },error=>{
      alert(error)
    })
  }

  addEmployee(){
    this.employees = []
    this.newEmployee.role = 'Employee'
    this.employeeService.createEmployee(this.newEmployee).subscribe(data=>{
      alert(data)
      this.getEmployees()
    },error=>{
      alert(error)
    })
  }

}
