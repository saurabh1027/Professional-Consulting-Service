package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@Table(name = "Skill")
//User for many-to-many relationships
@JsonIdentityInfo(
		  generator = PropertyGenerator.class, 
		  property = "id")
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
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(
			name = "employee_skill",
			joinColumns = @JoinColumn(name="skill_id"),
			inverseJoinColumns = @JoinColumn(name="employee_id"))
	Set<Employee> employees = new HashSet<>();
	
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
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	@Override
	public String toString() {
		return "Skill [id=" + id + ", title=" + title + ", category=" + category + ", description=" + description
				+ ", employees=" + employees + "]";
	}
	
	
	
}
