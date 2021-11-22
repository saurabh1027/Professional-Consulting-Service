import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Certificate } from '../Models/Certificate';

@Injectable({
  providedIn: 'root'
})
export class CertificateService {
  baseUrl:string = "http://localhost:8080/"

  constructor(
    private http:HttpClient
  ) { }

  public addCertificate(certificate:Certificate,eid:number){
    return this.http.post(this.baseUrl+'employees/'+eid+'/certificates',certificate,{responseType:'text'})
  }

}
