package com.refugio.service;

import com.refugio.dao.AnimalDAO;
import com.refugio.model.Animal;

import java.util.List;

public class AnimalService {
    private AnimalDAO animalDAO;

    public AnimalService() {
        this.animalDAO = new AnimalDAO();
    }

    public void registrarAnimal(Animal animal, String nombreFamilia, int edadFamilia, String ciudadFamilia) {
        animalDAO.saveAnimal(animal, nombreFamilia, edadFamilia, ciudadFamilia);
    }

    public List<Animal> buscarPorEspecie(String especie) {
        return animalDAO.findByEspecie(especie);
    }

    public void actualizarEstado(Long id, String nuevoEstado) {
        animalDAO.updateEstado(id, nuevoEstado);
    }

    public void actualizarFamilia(Long id, String nombreFamilia, int edadFamilia, String ciudadFamilia) {
        animalDAO.updateFamilia(id, nombreFamilia, edadFamilia, ciudadFamilia);
    }

    public List<Animal> obtenerTodosLosAnimales() {
        return animalDAO.findAll();
    }
}
