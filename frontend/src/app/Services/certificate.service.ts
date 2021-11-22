import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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

  public deleteCertificate(cid:number,eid:number){
    return this.http.delete(this.baseUrl+'employees/'+eid+'/certificates/'+cid,{responseType:"text"})
  }

  public getCertificates(eid:number):Observable<Certificate[]>{
    return this.http.get<Certificate[]>(this.baseUrl+"employees/"+eid+"/certificates")
  }

}
