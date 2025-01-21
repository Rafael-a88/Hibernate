package com.refugio.main;

import com.refugio.model.Animal;
import com.refugio.service.AnimalService;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AnimalService animalService = new AnimalService();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ REFUGIO DE ANIMALES ===");
            System.out.println("1. Registrar nuevo animal");
            System.out.println("2. Buscar animales por especie");
            System.out.println("3. Actualizar estado de un animal");
            System.out.println("4. Actualizar familia de un animal");
            System.out.println("5. Ver todos los animales");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    registrarNuevoAnimal(scanner, animalService);
                    break;
                case 2:
                    buscarPorEspecie(scanner, animalService);
                    break;
                case 3:
                    actualizarEstadoAnimal(scanner, animalService);
                    break;
                case 4:
                    actualizarFamiliaAnimal(scanner, animalService);
                    break;
                case 5:
                    mostrarTodosLosAnimales(animalService);
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void registrarNuevoAnimal(Scanner scanner, AnimalService animalService) {
        System.out.println("\n=== REGISTRO DE NUEVO ANIMAL ===");

        // Datos del animal
        System.out.print("Ingrese el nombre del animal: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la especie: ");
        String especie = scanner.nextLine();
        System.out.print("Ingrese la edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();

        // Datos de la familia
        System.out.print("Ingrese el nombre de la familia: ");
        String nombreFamilia = scanner.nextLine();
        System.out.print("Ingrese la edad del responsable de la familia: ");
        int edadFamilia = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la ciudad de la familia: ");
        String ciudadFamilia = scanner.nextLine();

        Animal animal = new Animal();
        animal.setNombre(nombre);
        animal.setEspecie(especie);
        animal.setEdad(edad);
        animal.setDescripcion(descripcion);
        animal.setEstado("recién abandonado");

        animalService.registrarAnimal(animal, nombreFamilia, edadFamilia, ciudadFamilia);
        System.out.println("Animal registrado correctamente.");
    }

    private static void buscarPorEspecie(Scanner scanner, AnimalService animalService) {
        System.out.print("\nIngrese la especie a buscar: ");
        String especieBuscar = scanner.nextLine();
        List<Animal> animales = animalService.buscarPorEspecie(especieBuscar);

        if (animales.isEmpty()) {
            System.out.println("No se encontraron animales de esta especie.");
        } else {
            System.out.println("\nAnimales encontrados:");
            for (Animal animal : animales) {
                System.out.println("ID: " + animal.getId() +
                        ", Nombre: " + animal.getNombre() +
                        ", Edad: " + animal.getEdad() +
                        ", Estado: " + animal.getEstado());
            }
        }
    }

    private static void actualizarEstadoAnimal(Scanner scanner, AnimalService animalService) {
        System.out.print("\nIngrese el ID del animal: ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.print("Ingrese el nuevo estado: ");
        String nuevoEstado = scanner.nextLine();

        animalService.actualizarEstado(id, nuevoEstado);
        System.out.println("Estado actualizado correctamente.");
    }

    private static void actualizarFamiliaAnimal(Scanner scanner, AnimalService animalService) {
        System.out.print("\nIngrese el ID del animal: ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.print("Ingrese el nombre de la nueva familia: ");
        String nombreFamilia = scanner.nextLine();
        System.out.print("Ingrese la edad del responsable de la familia: ");
        int edadFamilia = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la ciudad de la familia: ");
        String ciudadFamilia = scanner.nextLine();

        animalService.actualizarFamilia(id, nombreFamilia, edadFamilia, ciudadFamilia);
        System.out.println("Familia actualizada correctamente.");
    }

    private static void mostrarTodosLosAnimales(AnimalService animalService) {
        List<Animal> animales = animalService.obtenerTodosLosAnimales();

        if (animales.isEmpty()) {
            System.out.println("\nNo hay animales registrados.");
        } else {
            System.out.println("\n=== LISTA DE TODOS LOS ANIMALES ===");
            for (Animal animal : animales) {
                System.out.println("ID: " + animal.getId() +
                        ", Nombre: " + animal.getNombre() +
                        ", Especie: " + animal.getEspecie() +
                        ", Edad: " + animal.getEdad() +
                        ", Estado: " + animal.getEstado());
            }
        }
    }
}
