package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Skill;

@Service
public interface SkillService {
	public ResponseEntity<?> createSkill(Skill skill);
	
	public ResponseEntity<?> updateSkill(Skill skill);
	
	public ResponseEntity<?> deleteSkill(long id);
	
	public ResponseEntity<List<Skill>> getSkills();

}
