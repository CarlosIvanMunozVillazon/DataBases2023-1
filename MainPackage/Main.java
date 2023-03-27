package MainPackage;

import java.util.Scanner;
//Many methods here need exception handling.
public class Main {
    private static final Scanner registerScanner = new Scanner(System.in);
    public static void main(String[] args) {

        DataBaseManager.initializeDataBase();

        int option;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar persona.");
            System.out.println("2. Consultar persona.");
            System.out.println("3. Actualizar persona.");
            System.out.println("4. Eliminar persona.");
            System.out.println("Presione otro número para salir.");

            option = registerScanner.nextInt();
            registerScanner.nextLine();

            if (option == 1){
                register();
            } else if (option == 2){
                search();
            } else if (option == 3){
                update();
            } else if (option == 4){
                delete();
            }

        } while (option == 1 || option == 2 || option == 3 || option == 4);

    }

    public static void register(){ //CREATE

        System.out.println("Inserte cédula: ");
        String cedula = registerScanner.nextLine();
        int cedula2 = Integer.parseInt(cedula);

        System.out.println("Inserte nombre: ");
        String nombre = registerScanner.nextLine();

        System.out.println("Inserte apellido: ");
        String apellido = registerScanner.nextLine();

        System.out.println("Inserte edad: ");
        String edad = registerScanner.nextLine();
        int edad2 = Integer.parseInt(edad);

        DataBaseManager.registerPerson(cedula2,nombre,apellido,edad2);

    }

    public static void search(){ //READ
        System.out.println("Inserte cédula: ");
        String cedula = registerScanner.nextLine();
        int cedula2 = Integer.parseInt(cedula);

        DataBaseManager.peekPerson(cedula2);
    }

    public static void update(){ //UPDATE

        int opcion;

        System.out.println("Inserte cédula: ");
        int cedula = registerScanner.nextInt();
        registerScanner.nextLine();

        do {
            System.out.println("¿Qué desea actualizar? Escoja una opción: ");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Edad");
            System.out.println("4. Salir");

            opcion = registerScanner.nextInt();
            registerScanner.nextLine();

            if (opcion == 1) {
                System.out.println("Por favor ingrese nuevo nombre: ");
                String newName = registerScanner.nextLine();
                DataBaseManager.updatePerson(cedula, newName, "Nombre");
            } else if (opcion == 2) {
                System.out.println("Por favor ingrese nuevo apellido: ");
                String newLastName = registerScanner.nextLine();
                DataBaseManager.updatePerson(cedula, newLastName, "Apellido");
            } else if (opcion == 3) {
                System.out.println("Por favor ingrese nueva edad: ");
                int newAge = registerScanner.nextInt();
                registerScanner.nextLine();
                DataBaseManager.updatePerson(cedula, newAge);
            }

        } while (opcion != 4);
    }

    public static void delete(){ //DELETE
        System.out.println("Inserte cédula: ");
        String cedula = registerScanner.nextLine();
        int cedula2 = Integer.parseInt(cedula);

        DataBaseManager.deletePerson(cedula2);
    }
}
