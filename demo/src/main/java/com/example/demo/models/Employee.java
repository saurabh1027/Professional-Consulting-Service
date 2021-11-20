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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String bio;
	
//	@ManyToMany(mappedBy = "employees")
//	Set<Skill> skills = new HashSet<>();
	@OneToMany(mappedBy = "employee")
	private Set<Certificate> certificates = new HashSet<Certificate>();
	
	
	@ManyToMany(mappedBy = "employees",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	Set<Skill> skills = new HashSet<>();

	public Employee(long id, String email, String password, String name, String role, String bio,
			Set<Certificate> certificates, Set<Skill> skills) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.bio = bio;
		this.certificates = certificates;
		this.skills = skills;
	}

	public Employee() {
		super();
	}

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

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Set<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", role="
				+ role + ", bio=" + bio + ", certificates=" + certificates + ", skills=" + skills + "]";
	}
	


	
}
