import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CertificateComponent } from './Components/certificate/certificate.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { ListEmployeeComponent } from './Components/list-employee/list-employee.component';
import { LoginComponent } from './Components/login/login.component';
import { SkillComponent } from './Components/skill/skill.component';

const routes: Routes = [
  {path:"login",component:LoginComponent},
  {path:"",component:DashboardComponent,
  children:[
    {path:"employees",component:ListEmployeeComponent},
    {path:"",component:CertificateComponent},
    {path:"skills",component:SkillComponent}
  ]}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
