package com.mykyta.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mykyta.crud.model.Developer;
import com.mykyta.crud.model.Specialty;
import com.mykyta.crud.model.Status;
import com.mykyta.crud.repository.DeveloperRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    private final Path DEVELOPERS_FILE_PATH = Path.of("src/main/resources/developers.json");

    private List<Developer> getAllDevelopers(){
        String json;
        try{
            json = Files.readString(DEVELOPERS_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Type targetClassType = new TypeToken<ArrayList<Developer>>() {
        }.getType();
        return new Gson().fromJson(json, targetClassType);
    }
    @Override
    public List<Developer> getAll() {
        return getAllDevelopers();
    }

    @Override
    public Developer getById(Integer id) {
        return getAllDevelopers().stream()
                .filter(specialty -> specialty.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Developer create(Developer developer) {
        List<Developer> developerList = getAllDevelopers();
        developer.setId(generateNewId(developerList));
        developerList.add(developer);
        createNewDevelopersJson(developerList);
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> developerList = getAllDevelopers();
        developerList.forEach(s -> {
            if(s.getId().equals(developer.getId())) {
                s.setFirstName(developer.getFirstName());
                s.setLastName(developer.getLastName());
                s.setSkills(developer.getSkills());
                s.setSpecialty(developer.getSpecialty());
                s.setStatus(Status.ACTIVE);
            }
        });
        createNewDevelopersJson(developerList);
        return developer;
    }

    @Override
    public void deleteById(Integer id) {
        List<Developer> developerList = getAllDevelopers();
        developerList.stream()
                .filter(developer -> developer.getId().equals(id))
                .forEach(developer -> developer.setStatus(Status.DELETED));
        createNewDevelopersJson(developerList);
    }

    private void createNewDevelopersJson(List<Developer> developerList){
        try {
            Files.writeString(DEVELOPERS_FILE_PATH, new Gson().toJson(developerList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer generateNewId(List<Developer> developerList) {
        Developer developerWithMaxId = developerList.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(developerWithMaxId) ? developerWithMaxId.getId() + 1 : 1;
    }
}

