import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Employee } from 'src/app/Models/Employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  baseUrl:string='http://localhost:8080/';

  constructor(private http:HttpClient) { }

  public login(email:string,password:string):Observable<Employee>{
    let params:HttpParams = new HttpParams();
    params = params.append("email",email)
    params = params.append("password",password)
    return this.http.get<Employee>(this.baseUrl+'authenticate',{params:params})
  }

  public getEmployee(id:number):Observable<Employee>{
    return this.http.get<Employee>(this.baseUrl+"employees/"+id)
  }

  public createEmployee(employee:Employee){
    return this.http.post(this.baseUrl+'employees/',employee)
  }

  public updateEmployee(employee:Employee){
    return this.http.patch(this.baseUrl+'employees/'+employee.id,employee)
  }

  public deleteEmployee(id:number){
    return this.http.delete(this.baseUrl+'employees/'+id)
  }

  public getEmployees():Observable<Employee[]>{
    return this.http.get<Employee[]>(this.baseUrl+'employees')
  }

}