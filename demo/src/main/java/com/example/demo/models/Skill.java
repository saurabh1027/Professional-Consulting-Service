package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Skill")
public class Skill {

//	Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String title;
	@Column
	private String category; 
	@Column
	private String description;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id",referencedColumnName = "id")
	private Employee employee;
	
//	Constructors
	public Skill(long id, String title, String category, String desc) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.description = desc;
	}
	public Skill(String title, String category, String desc) {
		super();
		this.title = title;
		this.category = category;
		this.description = desc;
	}
	public Skill() {
		super();
	}
	
//	Getters & Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	//	toString()
	@Override
	public String toString() {
		return "Skill [id=" + id + ", title=" + title + ", category=" + category + ", description=" + description
				+ ", employee=" + employee + "]";
	}
}
