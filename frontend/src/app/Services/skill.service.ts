import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Skill } from '../Models/Skill';

@Injectable({
  providedIn: 'root'
})
export class SkillService {
  baseUrl:string = "http://localhost:8080/";

  constructor(
    private http:HttpClient
  ) { }

  public getSkills():Observable<Skill[]>{
    return this.http.get<Skill[]>(this.baseUrl+'skills')
  }

  public createSkill(skill:Skill){
    return this.http.post(this.baseUrl+"skills",skill,{responseType:'text'})
  }

  public selectSkills(selectedSkillsIds:number[],eid:number){
    return this.http.put(this.baseUrl+'employees/'+eid+"/skills",selectedSkillsIds,{responseType:'text'})
  }

}
