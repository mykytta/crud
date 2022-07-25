package com.mykyta.crud.controller;

import com.mykyta.crud.model.Specialty;
import com.mykyta.crud.repository.SpecialtyRepository;
import com.mykyta.crud.repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyController {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyController(){
        specialtyRepository = new GsonSpecialtyRepositoryImpl();
    }

    public List<Specialty> getAllSpecialties(){
        return specialtyRepository.getAll();
    }

    public Specialty getSpecialtyById(Integer id){
        return specialtyRepository.getById(id);
    }

    public Specialty createSpecialty(String specialtyName){
        Specialty specialty = new Specialty(null, specialtyName);
        return specialtyRepository.create(specialty);
    }

    public Specialty updateSpecialty(Integer id, String specialtyName) {
        return specialtyRepository.update(new Specialty(id, specialtyName));
    }

    public void deleteSpecialtyById(Integer id){
        specialtyRepository.deleteById(id);
    }
}
