package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

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
			System.out.println(skillRepository.findByTitle(skill.getTitle()));
			skillRepository.save(skill);
			return ResponseEntity.ok("Skill added Successfully");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Exists!");
		}
	}

	@Override
	public ResponseEntity<?> updateSkill(Skill skill) {
		return null;
	}

	@Override
	public ResponseEntity<?> deleteSkill(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Skill>> getSkills() {
		return ResponseEntity.ok(skillRepository.findAll());
	}

}
