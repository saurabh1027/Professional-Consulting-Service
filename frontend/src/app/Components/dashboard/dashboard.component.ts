import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Certificate } from 'src/app/Models/Certificate';
import { Employee } from 'src/app/Models/Employee';
import { Skill } from 'src/app/Models/Skill';
import { CertificateService } from 'src/app/Services/certificate.service';
import { EmployeeService } from 'src/app/Services/employee.service';
import { SkillService } from 'src/app/Services/skill.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  employee:Employee = new Employee(0,'','','','','',[]);
  skill:Skill = new Skill(0,'','','',[]);
  skills:Skill[] = [];
  index:number = 0;
  isEmployeesPage:boolean = false;
  isSkillsPage:boolean = false;
  isCertificatesPage:boolean = false;

  constructor(
    private router:Router,
    private employeeService:EmployeeService,
    private skillService:SkillService,
    private certificateService:CertificateService
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
