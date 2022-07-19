package com.mykyta.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mykyta.crud.model.Skill;
import com.mykyta.crud.repository.SkillRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final Path skillsRep = Path.of("src/main/resources/skills.json");

    @Override
    public List<Skill> getAll() {
        String json;
        try {
            json = Files.readString(skillsRep);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Type targetClassType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        return new Gson().fromJson(json, targetClassType);
    }

    @Override
    public Skill getById(Integer id) {
        return getAll().stream()
                .filter(skill -> skill.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Skill create(Skill skill) {
        List<Skill> skillList = getAll();
        if (skillList != null) {
            skillList.add(skill);
        } else {
            skillList = new ArrayList<>();
            skillList.add(skill);
        }

        createNewSkillsJson(skillList);
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> skillList = getAll();
        if(skillList.stream().noneMatch(skill1 -> skill1.getId().equals(skill.getId())))
            return null;
        skillList.stream()
                .filter(skill1 -> skill1.getId().equals(skill.getId()))
                .forEach(skill1 -> skill1.setSkillName(skill.getSkillName()));
        createNewSkillsJson(skillList);
        return skill;
    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> skillList = getAll().stream()
                .filter(skill -> !skill.getId().equals(id))
                .collect(Collectors.toList());
        createNewSkillsJson(skillList);
    }

    public void createNewSkillsJson(List<Skill> skillList) {
        try {
            Files.writeString(skillsRep, new Gson().toJson(skillList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
