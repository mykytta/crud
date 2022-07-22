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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final Path SKILLS_FILE_PATH = Path.of("src/main/resources/skills.json");

    private List<Skill> getAllSkills() {
        String json;
        try {
            json = Files.readString(SKILLS_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Type targetClassType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        return new Gson().fromJson(json, targetClassType);
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkills();
    }

    @Override
    public Skill getById(Integer id) {
        return getAllSkills().stream()
                .filter(skill -> skill.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Skill create(Skill skill) {
        List<Skill> skillList = getAllSkills();
        skill.setId(generateNewId(skillList));
        skillList.add(skill);
        createNewSkillsJson(skillList);
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> skillList = getAllSkills();
        skillList.forEach(s -> {
            if(s.getId().equals(skill.getId())) {
                s.setSkillName(skill.getSkillName());
            }
        });
        createNewSkillsJson(skillList);
        return skill;
    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> skillList = getAllSkills();
        skillList.removeIf(s -> s.getId().equals(id));
        createNewSkillsJson(skillList);
    }


    private void createNewSkillsJson(List<Skill> skillList) {
        try {
            Files.writeString(SKILLS_FILE_PATH, new Gson().toJson(skillList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer generateNewId(List<Skill> skills) {
        Skill skillWithMaxId = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(skillWithMaxId) ? skillWithMaxId.getId() + 1 : 1;
    }
}
