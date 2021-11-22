import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Certificate } from 'src/app/Models/Certificate';
import { Employee } from 'src/app/Models/Employee';
import { CertificateService } from 'src/app/Services/certificate.service';
import { EmployeeService } from 'src/app/Services/employee.service';

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.css']
})
export class CertificateComponent implements OnInit {
  employee:Employee = new Employee(0,'','','','','',[]);
  certificates:Certificate[] = [];
  certificate:Certificate = new Certificate(0,'','')

  constructor(
    private router:Router,
    private certificateService:CertificateService,
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
        this.getCertificates()
      },error=>{
        if(error.status==400){
          alert(error)
        }
      })
    }else{
      this.router.navigate(['','login'])
    }
  }

  getCertificates(){
    console.log(this.certificates)
    this.certificates = []
    this.certificateService.getCertificates(this.employee.id).subscribe(data=>{
      this.certificates = data
    })
  }

  addCertificate(){
    this.certificateService.addCertificate(this.certificate,this.employee.id).subscribe(data=>{
      alert(data)
      this.getCertificates()
    },error=>{
      alert(error)
    })
  }

  deleteCertificate(id:number){
    this.certificateService.deleteCertificate(id,this.employee.id).subscribe(data=>{
      alert(data)
      this.getCertificates()
    },error=>{
      alert(error)
    })
  }

}
