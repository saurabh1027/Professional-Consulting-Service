import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  skill:Skill = new Skill(0,'','','');
  skills:Skill[] = [];
  index:number = 0;
  certificate:Certificate = new Certificate(0,'','')

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

  addSkill(){
    this.skillService.createSkill(this.skill).subscribe(data=>{
      alert(data)
      this.skill = new Skill(0,'','','')
    },error=>{
      if(error.status == 400){
        alert(error)
      }
    })
  }

  getSkills(){
    this.skillService.getSkills().subscribe(data=>{
      this.skills = data
    })
  }

  toggleSkill(id:number){
    let spans = document.getElementsByClassName('skill');
    let skill:Skill = new Skill(0,'','','')
    for(let i=0;i<this.skills.length;i++){
      if(this.skills[i].id==id){
        skill = this.skills[i];
      }
    }
    for(let i=0;i<spans.length;i++){
      if(spans[i].textContent==skill.title){
        if(spans[i].classList.contains('selected')){
          spans[i].classList.remove('selected')
          spans[i].classList.add('bg-secondary')
        }else{
          spans[i].classList.remove('bg-secondary')
          spans[i].classList.add('selected')
        }
      }
    }

  }

  selectSkills(){
    let selectedSkillsIds:number[] = []
    let spans = document.getElementsByClassName('selected');
    for(let i=0;i<spans.length;i++){
      for(let j=0;j<this.skills.length;j++){
        if(spans[i].textContent == this.skills[j].title){
          selectedSkillsIds.push(this.skills[j].id)
        }
      }
    }
    this.skillService.selectSkills(selectedSkillsIds,this.employee.id).subscribe(data=>{
      alert(data)
    },error=>{
      alert(error)
    })
  }

  addCertificate(){
    this.certificateService.addCertificate(this.certificate,this.employee.id).subscribe(data=>{
      alert(data)
    },error=>{
      alert(error)
    })
  }

}
