package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Skill;
import com.example.demo.services.SkillService;

@RestController
@RequestMapping("skills")
public class SkillController {
	@Autowired
	SkillService skillService;
	//Only managers can create skills
	@PostMapping("")
	public ResponseEntity<?> createSkill(@RequestBody Skill skill) {
		return skillService.createSkill(skill);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Skill>> getSkills(){
		return skillService.getSkills();
	}
	
}