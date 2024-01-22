import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProcesoElectoral elecciones = new ProcesoElectoral();

        int opcion;
        do {
            System.out.println("=== Menú ===");
            System.out.println("1) Registro de votantes");
            System.out.println("2) Emitir voto");
            System.out.println("3) Registrar su candidatura");
            System.out.println("4) Información del proceso electoral");
            System.out.println("5) Avanzar hora al reloj");
            System.out.println("6) Conocer los resultados oficiales de las elecciones");
            System.out.println("7) Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    elecciones.registroVotantes(scanner);
                    break;
                case 2:
                    elecciones.emitirVoto(scanner);
                    break;
                case 3:
                    elecciones.registrarCandidatura(scanner);
                    break;
                case 4:
                    elecciones.mostrarInformacionProcesoElectoral();
                    break;
                case 5:
                    elecciones.avanzarHora();
                    break;
                case 6:
                    elecciones.mostrarResultadosEleccion();
                    break;
                case 7:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }

        } while (opcion != 7);
    }
}
