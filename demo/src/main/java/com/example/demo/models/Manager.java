package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Manager")
public class Manager {
	
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
	private String address;
	@Column
	private String dob;
	@JsonIgnore
	@ManyToMany(mappedBy = "managers")
	Set<Skill> skills = new HashSet<>();
	
	public Manager(long id, String email, String password, String name, String address, String dob, Set<Skill> skills) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.dob = dob;
		this.skills = skills;
	}
	public Manager() {
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
	public Set<Skill> getSkills() {
		return skills;
	}
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	@Override
	public String toString() {
		return "Manager [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", address="
				+ address + ", dob=" + dob + ", skills=" + skills + "]";
	}
}