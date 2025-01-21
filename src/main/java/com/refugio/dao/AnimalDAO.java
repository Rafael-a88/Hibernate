package com.refugio.dao;

import com.refugio.model.Animal;
import com.refugio.model.Familia;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AnimalDAO {

    // Método para guardar un nuevo animal
    public void saveAnimal(Animal animal, String nombreFamilia, int edadFamilia, String ciudadFamilia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Buscar la familia existente
            Query<Familia> query = session.createQuery("FROM Familia WHERE nombre = :nombre", Familia.class);
            query.setParameter("nombre", nombreFamilia);
            Familia familia = query.uniqueResult();

            if (familia == null) {
                // Si no existe, crea una nueva familia
                familia = new Familia();
                familia.setNombre(nombreFamilia);
                familia.setEdad(edadFamilia);
                familia.setCiudad(ciudadFamilia);
                session.save(familia); // Guardar la nueva familia
            } else {
                // Si existe, actualiza los detalles si es necesario
                familia.setEdad(edadFamilia);
                familia.setCiudad(ciudadFamilia);
                session.update(familia); // Actualizar la familia existente
            }

            // Asignar la familia al animal
            animal.setFamilia(familia);
            session.save(animal); // Ahora guarda el animal

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



    // Método para buscar animales por especie
    public List<Animal> findByEspecie(String especie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Animal> query = session.createQuery("FROM Animal WHERE especie = :especie", Animal.class);
            query.setParameter("especie", especie);
            return query.list();
        }
    }

    // Método para actualizar el estado de un animal
    public void updateEstado(Long id, String nuevoEstado) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Animal animal = session.get(Animal.class, id);
            if (animal != null) {
                animal.setEstado(nuevoEstado);
                session.update(animal);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Método para actualizar datos de la familia que acoge al animal
    public void updateFamilia(Long id, String nombreFamilia, int edadFamilia, String ciudadFamilia) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Animal animal = session.get(Animal.class, id);
            if (animal != null) {
                // Buscar la familia existente
                Query<Familia> query = session.createQuery("FROM Familia WHERE nombre = :nombre", Familia.class);
                query.setParameter("nombre", nombreFamilia);
                Familia familia = query.uniqueResult();

                if (familia == null) {
                    // Si no existe, crea una nueva familia
                    familia = new Familia();
                    familia.setNombre(nombreFamilia);
                    familia.setEdad(edadFamilia);
                    familia.setCiudad(ciudadFamilia);
                    session.save(familia); // Guardar la nueva familia
                } else {
                    // Si existe, actualiza los detalles si es necesario
                    familia.setEdad(edadFamilia);
                    familia.setCiudad(ciudadFamilia);
                    session.update(familia); // Actualizar la familia existente
                }

                // Asignar la familia al animal
                animal.setFamilia(familia);
                session.update(animal); // Actualizar el animal
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    // Método para obtener todos los animales
    public List<Animal> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Animal> query = session.createQuery("FROM Animal", Animal.class);
            return query.list();
        }
    }
}
