export class Employee{
    id!:number;
    email!:string;
    password!:string;
    name!:string;
    role!:string;
    bio!:string;
    constructor(id:number,email:string,password:string,name:string,role:string,bio:string){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.bio = bio;
    }
}