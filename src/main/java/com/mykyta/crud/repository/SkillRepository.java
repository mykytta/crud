package com.mykyta.crud.repository;

import com.mykyta.crud.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Integer >{
    List<Skill> getAll();
    Skill getById(Integer id);
    Skill create(Skill skill);
    Skill update(Skill skill);
    void deleteById(Integer id);
}
