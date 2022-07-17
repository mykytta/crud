package com.mykyta.crud.controller;

import com.mykyta.crud.model.Skill;
import com.mykyta.crud.repository.SkillRepository;
import com.mykyta.crud.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    SkillRepository skillRepository;

    public SkillController(){
        skillRepository = new GsonSkillRepositoryImpl();
    }

    public List<Skill> getAllSkills(){
        return skillRepository.getAll();
    }

    public Skill getSkillById(Integer id){
        return skillRepository.getById(id);
    }

    public Skill createSkill(Integer id, String skillName){
        Skill skill = new Skill(id, skillName);
        return skillRepository.create(skill);
    }

    public Skill updateSkill(Integer id, String skillName) {
        return skillRepository.update(new Skill(id, skillName));
    }

    public void deleteSkillsById(Integer id){
        skillRepository.deleteById(id);
    }
}
