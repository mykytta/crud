package com.mykyta.crud.controller;

import com.mykyta.crud.model.Developer;
import com.mykyta.crud.model.Skill;
import com.mykyta.crud.model.Specialty;
import com.mykyta.crud.repository.DeveloperRepository;
import com.mykyta.crud.repository.gson.GsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    DeveloperRepository developerRepository;

    public DeveloperController(){
        developerRepository = new GsonDeveloperRepositoryImpl();
    }

    public List<Developer> getAllDevelopers(){
        return developerRepository.getAll();
    }

    public Developer getDeveloperById(Integer id){
        return developerRepository.getById(id);
    }

    public Developer createDeveloper(String firstName, String lastName, List<Skill> skillList, Specialty specialty){
        Developer developer = new Developer(null, firstName, lastName, skillList, specialty);
        return developerRepository.create(developer);
    }

    public Developer updateDeveloper(Integer id, String firstName, String lastName, List<Skill> skillList, Specialty specialty){
        Developer developer = new Developer(id, firstName, lastName, skillList, specialty);
        return  developerRepository.update(developer);
    }

    public void deleteDeveloperById(Integer id){
        developerRepository.deleteById(id);
    }
}
