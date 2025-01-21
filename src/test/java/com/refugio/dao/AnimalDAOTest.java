package com.refugio.dao;

import com.refugio.model.Animal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AnimalDAOTest {

    @Test
    public void testSaveAndFindByEspecie() {
        AnimalDAO animalDAO = new AnimalDAO();

        // Crear animal de prueba con los datos que sabemos que funcionan
        Animal animal = new Animal();
        animal.setNombre("Kino");
        animal.setEspecie("Chucho");
        animal.setEdad(14);
        animal.setDescripcion("Perro negro");
        animal.setEstado("recién abandonado");

        // Datos de la familia que sabemos que funcionan
        String nombreFamilia = "Auxilia";
        int edadFamilia = 36;
        String ciudadFamilia = "Sevilla";

        // Guardar animal con los datos de la familia
        animalDAO.saveAnimal(animal, nombreFamilia, edadFamilia, ciudadFamilia);

        // Verificar que se puede encontrar el animal
        List<Animal> perros = animalDAO.findByEspecie("Chucho");
        assertFalse(perros.isEmpty());
        assertEquals("Kino", perros.get(0).getNombre());
    }

    @Test
    public void testUpdateEstado() {
        AnimalDAO animalDAO = new AnimalDAO();

        // Crear y guardar animal de prueba
        Animal animal = new Animal();
        animal.setNombre("Kino");
        animal.setEspecie("Chucho");
        animal.setEdad(14);
        animal.setDescripcion("Perro negro");
        animal.setEstado("recién abandonado");

        animalDAO.saveAnimal(animal, "Auxilia", 36, "Sevilla");

        // Obtener el ID del animal guardado
        List<Animal> perros = animalDAO.findByEspecie("Chucho");
        Long animalId = perros.get(0).getId();

        // Actualizar estado
        String nuevoEstado = "Acogida";
        animalDAO.updateEstado(animalId, nuevoEstado);

        // Verificar que el estado se actualizó
        List<Animal> perrosActualizados = animalDAO.findByEspecie("Chucho");
        assertEquals(nuevoEstado, perrosActualizados.get(0).getEstado());
    }

    @Test
    public void testUpdateFamilia() {
        AnimalDAO animalDAO = new AnimalDAO();

        // Crear y guardar animal de prueba
        Animal animal = new Animal();
        animal.setNombre("Kino");
        animal.setEspecie("Chucho");
        animal.setEdad(14);
        animal.setDescripcion("Perro negro");
        animal.setEstado("recién abandonado");

        animalDAO.saveAnimal(animal, "Auxilia", 36, "Sevilla");

        // Obtener el ID del animal guardado
        List<Animal> perros = animalDAO.findByEspecie("Chucho");
        Long animalId = perros.get(0).getId();

        // Actualizar familia con nuevos datos
        String nuevaFamilia = "Nueva Familia";
        int nuevaEdad = 40;
        String nuevaCiudad = "Madrid";

        animalDAO.updateFamilia(animalId, nuevaFamilia, nuevaEdad, nuevaCiudad);

        // Verificar que la familia se actualizó
        List<Animal> perrosActualizados = animalDAO.findByEspecie("Chucho");
        assertEquals(nuevaFamilia, perrosActualizados.get(0).getFamilia().getNombre());
        assertEquals(nuevaEdad, perrosActualizados.get(0).getFamilia().getEdad());
        assertEquals(nuevaCiudad, perrosActualizados.get(0).getFamilia().getCiudad());
    }
}
