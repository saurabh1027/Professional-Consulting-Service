package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {
	
//	Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String email;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String role;
	@Column
	private String address;
	@Column
	private String dob;
	@Column
	private String bio;
	@OneToMany(mappedBy = "employee")
	private Set<Skill> skills = new HashSet<Skill>();
	@OneToMany(mappedBy = "employee")
	private Set<Skill> certificates = new HashSet<Skill>();
	
//	Constructors
	public Employee(long id, String email, String password, String name, String role, String address, String dob, String bio) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.address = address;
		this.dob = dob;
		this.bio = bio;
	}
	public Employee(String email, String password, String name, String role, String address, String dob, String bio) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.address = address;
		this.dob = dob;
		this.bio = bio;
	}
	public Employee() {
		super();
	}
	
//	Getters & Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public Set<Skill> getSkills() {
		return skills;
	}
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	public Set<Skill> getCertificates() {
		return certificates;
	}
	public void setCertificates(Set<Skill> certificates) {
		this.certificates = certificates;
	}
	
	//	toString()
	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", role="
				+ role + ", address=" + address + ", dob=" + dob + ", bio=" + bio + ", skills=" + skills
				+ ", certificates=" + certificates + "]";
	}
}
