package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.models.Skill;
import com.example.demo.repositories.SkillRepository;

@Component
public class SkillServiceImpl implements SkillService{
	@Autowired
	SkillRepository skillRepository;

	@Override
	public ResponseEntity<?> createSkill(Skill skill) {
		try {
			if(skillRepository.findByTitle(skill.getTitle()) != null) {
				throw new Exception("Skill already exists!");
			}
			skillRepository.save(skill);
			return ResponseEntity.ok("Skill added Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Exists!");
		}
	}

	@Override
	public ResponseEntity<List<Skill>> getSkills() {
		return ResponseEntity.ok(skillRepository.findAll());
	}

}
