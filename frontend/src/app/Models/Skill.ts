export class Skill{
    id!:number;
    title!:string;
    category!:string;
    description!:string;
    constructor(id:number,title:string,category:string,description:string){
        this.id=id;
        this.title=title;
        this.category=category;
        this.description=description;
    }
}