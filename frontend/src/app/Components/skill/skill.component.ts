import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/Models/Employee';
import { Skill } from 'src/app/Models/Skill';
import { EmployeeService } from 'src/app/Services/employee.service';
import { SkillService } from 'src/app/Services/skill.service';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent implements OnInit {
  employeeSkills:Skill[] = [];
  skill:Skill = new Skill(0,'','','',[]);
  skills:Skill[] = [];
  employee:Employee = new Employee(0,'','','','','',[]);

  constructor(
    private skillService:SkillService,
    private employeeService:EmployeeService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.isLoggedIn();
    this.getSkills()
  // this.getSkillsOfEmployee()
  }
  
  isLoggedIn(){
    let employeeString:string|null = sessionStorage.getItem("employee")
    if(employeeString){
      this.employee = JSON.parse(employeeString)
      this.employeeService.getEmployee(this.employee.id).subscribe(data=>{
        this.employee = data
        console.log(this.employee)
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

  getSkills(){
 
    this.skills=[]
    this.skillService.getSkills().subscribe(data=>{
      this.skills = data
     console.log(this.skills)
     this.getSkillsOfEmployee()
     
    })
  }

  getSkillsOfEmployee(){
    this.employeeSkills = [];
    if(this.skills){
      for(let i=0;i<this.skills.length;i++){
        if(this.skills[i].employees && this.skills[i].employees.length>0){
          for(let j=0;j<this.skills[i].employees.length;j++){
            if(this.skills[i].employees[j].id == this.employee.id){
              this.employeeSkills.push(this.skills[i])
            }
          }
        }
      }
    }

  }

  addSkill(){
    this.skillService.createSkill(this.skill).subscribe(data=>{
      alert(data)
      this.skill = new Skill(0,'','','',[])
    },error=>{
      
      
      if(error.status == 400){
        alert("Already Exists!")
      }
    })
  }

  toggleSkill(id:number){
    let spans = document.getElementsByClassName('skill');
    let skill:Skill = new Skill(0,'','','',[])
    for(let i=0;i<this.skills.length;i++){
      if(this.skills[i].id==id){
        skill = this.skills[i];
      }
    }
    for(let i=0;i<spans.length;i++){
      if(spans[i].textContent==skill.title){
        if(spans[i].classList.contains('selected')){
          spans[i].classList.remove('selected')
          spans[i].classList.add('deselected')
        }else{
          spans[i].classList.remove('deselected')
          spans[i].classList.add('selected')
        }
      }
    }

  }

  // selectSkills(){
  //   let selectedSkillsIds:number[] = []
  //   let spans = document.getElementsByClassName('selected');
  //   for(let i=0;i<spans.length;i++){
  //     for(let j=0;j<this.skills.length;j++){
  //       if(spans[i].textContent == this.skills[j].title){
  //         selectedSkillsIds.push(this.skills[j].id)
  //       }
  //     }
  //   }
  //   this.skillService.selectSkills(selectedSkillsIds,this.employee.id).subscribe(data=>{
  //     alert(data)
  //   },error=>{
  //     alert(error)
  //   })
  // }
  selectSkills(){
    let spans = document.getElementsByClassName('selected');
    let spans1 = document.getElementsByClassName('selected');
    for(let i=0;i<spans.length;i++){
      for(let j=0;j<this.skills.length;j++){
        if(spans[i].textContent == this.skills[j].title){
          this.selectSkill(this.skills[j].id)
        }
      }
    }
    for(let i=0;i<spans1.length;i++){
      for(let j=0;j<this.skills.length;j++){
        if(spans1[i].textContent == this.skills[j].title){
          this.deselectSkill(this.skills[j].id)
        }
      }
    }
  }

  isSkillSelected(id:number){
    for(let i=0;i<this.employeeSkills.length;i++){
      if(this.employeeSkills[i].id == id){
        return true;
      }
    }
    return false;
  }
  selectSkill(id:number){
    this.skillService.selectSkill(id,this.employee.id).subscribe(data=>{
      
    },error=>{
      alert(" Unable to select skill")
    })
  }
  deselectSkill(id:number){
    this.skillService.deselectSkill(id,this.employee.id).subscribe(data=>{
    },error=>{
      alert("Unable to deselect skill")
    })
  }

}
