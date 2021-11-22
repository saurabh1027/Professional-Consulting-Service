import { Employee } from "./Employee";

export class Skill{
    id!:number;
    title!:string;
    category!:string;
    description!:string;
    employees!:Employee[];
    constructor(id:number,title:string,category:string,description:string,employees:Employee[]){
        this.id=id;
        this.title=title;
        this.category=category;
        this.description=description;
        this.employees=employees;
    }
}