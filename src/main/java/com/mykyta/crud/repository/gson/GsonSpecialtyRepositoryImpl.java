package com.mykyta.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mykyta.crud.model.Specialty;
import com.mykyta.crud.repository.SpecialtyRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {
    private final Path SPECIALTIES_FILE_PATH = Path.of("src/main/resources/specialties.json");

    private List<Specialty> getAllSpecialties() {
        String json;
        try {
            json = Files.readString(SPECIALTIES_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Type targetClassType = new TypeToken<ArrayList<Specialty>>() {
        }.getType();
        return new Gson().fromJson(json, targetClassType);
    }

    @Override
    public List<Specialty> getAll() {
        return getAllSpecialties();
    }

    @Override
    public Specialty getById(Integer id) {
        return getAllSpecialties().stream()
                .filter(specialty -> specialty.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Specialty create(Specialty specialty) {
        List<Specialty> specialtyList = getAllSpecialties();
        specialty.setId(generateNewId(specialtyList));
        specialtyList.add(specialty);
        createNewSpecialtyJson(specialtyList);
        return specialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        List<Specialty> specialtyList = getAllSpecialties();
        specialtyList.forEach(s -> {
            if(s.getId().equals(specialty.getId())) {
                s.setSpecialtyName(specialty.getSpecialtyName());
            }
        });
        createNewSpecialtyJson(specialtyList);
        return specialty;
    }

    @Override
    public void deleteById(Integer id) {
        List<Specialty> specialtyList = getAllSpecialties();
        specialtyList.removeIf(s -> s.getId().equals(id));
        createNewSpecialtyJson(specialtyList);
    }


    private void createNewSpecialtyJson(List<Specialty> specialtyList) {
        try {
            Files.writeString(SPECIALTIES_FILE_PATH, new Gson().toJson(specialtyList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer generateNewId(List<Specialty> specialtyList) {
        Specialty specialtyWithMaxId = specialtyList.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        return Objects.nonNull(specialtyWithMaxId) ? specialtyWithMaxId.getId() + 1 : 1;
    }
}
