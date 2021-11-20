package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany
	@JoinTable(
			name = "manager_skill",
			joinColumns = @JoinColumn(name="skill_id"),
			inverseJoinColumns = @JoinColumn(name="manager_id"))
	Set<Employee> managers = new HashSet<>();
	
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
	public Set<Employee> getManagers() {
		return managers;
	}
	public void setManagers(Set<Employee> managers) {
		this.managers = managers;
	}
	@Override
	public String toString() {
		return "Skill [id=" + id + ", title=" + title + ", category=" + category + ", description=" + description
				+ ", managers=" + managers + "]";
	}
}
