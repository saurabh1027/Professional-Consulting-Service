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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Certificate")
public class Certificate {
	
//	Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private String description;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "employee_id",referencedColumnName = "id")
	private Employee employee;
	
//	Constructors
	public Certificate(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Certificate(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Certificate() {
		super();
	}
	
//	Getters & Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return "Certificate [id=" + id + ", name=" + name + ", description=" + description + ", employee=" + employee
				+ "]";
	}
}
