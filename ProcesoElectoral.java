import java.util.*;

class ProcesoElectoral {

    private static final int HORA_CIERRE = 17;

    private Map<String, Votante> votantes;
    private Map<String, Candidato> candidatos;
    private List<String> partidosPoliticos;
    private boolean procesoElectoralCerrado;
    private int horaActual;

    public ProcesoElectoral() {
        votantes = new HashMap<>();
        candidatos = new HashMap<>();
        partidosPoliticos = new ArrayList<>();
        procesoElectoralCerrado = false;
        horaActual = 5; // Inicializando la hora en 05:00 Am
    }


    void registroVotantes(Scanner scanner) {
        if (procesoElectoralCerrado) {
            System.out.println("El proceso electoral está cerrado. No se pueden registrar nuevos votantes.");
            return;
        }

        System.out.println("=== Registro de Votantes ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Número de identificación (cédula o pasaporte): ");
        String id = scanner.nextLine();

        // Verificar si la cédula es válida antes de agregar al votante
        if (!votantes.containsKey(id) && new Persona("", "", id).getId().equals(id)) {
            Votante votante = new Votante(nombre, apellido, id);
            votantes.put(id, votante);
            System.out.println("Votante registrado exitosamente.");
        } else {
            System.out.println("Este votante ya está registrado o la cédula es incorrecta. Registro fallido.");
        }
    }

    void emitirVoto(Scanner scanner) {
        if (procesoElectoralCerrado) {
            System.out.println("El proceso electoral está cerrado. No se pueden emitir votos.");
            return;
        }

        System.out.println("=== Emitir Voto ===");
        System.out.print("Número de identificación (cédula o pasaporte): ");
        String id = scanner.nextLine();

        if (!votantes.containsKey(id)) {
            System.out.println("El votante no está registrado.");
            return;
        }

        Votante votante = votantes.get(id);

        if (votante.getVotoEmitido()) {
            System.out.println("Este votante ya ha ejercido su voto.");
            return;
        }

        // Mostrar candidatos y partidos políticos con números
        System.out.println("Candidatos disponibles:");
        int i = 1;
        for (Candidato candidato : candidatos.values()) {
            System.out.println(i + ") " + candidato.getNombre() + " " + candidato.getApellido() + " - " + candidato.getPartidoPolitico());
            i++;
        }

        // Opciones adicionales
        System.out.println(i + ") Voto en blanco");
        i++;
        System.out.println(i + ") Voto nulo");

        System.out.print("Seleccione una opción: ");
        String opcionVoto = scanner.nextLine();

        try {
            int numeroOpcion = Integer.parseInt(opcionVoto);

            if (numeroOpcion >= 1 && numeroOpcion <= candidatos.size()) {
                // El usuario ha seleccionado un candidato válido
                Candidato candidatoElegido = candidatos.values().toArray(new Candidato[0])[numeroOpcion - 1];
                candidatoElegido.incrementarVotos();
                votante.setVotoEmitido(true);
                votante.setOpcionVoto(String.valueOf(numeroOpcion));
                System.out.println("Voto registrado exitosamente para " + candidatoElegido.getNombre() + " " + candidatoElegido.getApellido());
            } else if (numeroOpcion == i - 1) {
                // Voto en blanco
                System.out.println("Voto en blanco registrado exitosamente.");
                votante.setVotoEmitido(true);
                votante.setOpcionVoto(String.valueOf(i - 1));
            } else if (numeroOpcion == i) {
                // Voto nulo
                System.out.println("Voto nulo registrado exitosamente.");
                votante.setVotoEmitido(true);
                votante.setOpcionVoto(String.valueOf(i));
            } else {
                System.out.println("Opción no válida. Por favor, ingrese una opción válida y vuelva a intentarlo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida. Por favor, ingrese un número.");
        }
    }


    void registrarCandidatura(Scanner scanner) {
        if (procesoElectoralCerrado) {
            System.out.println("El proceso electoral está cerrado. No se pueden registrar nuevos candidatos.");
            return;
        }

        System.out.println("=== Registrar Candidatura ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Fecha de nacimiento (Dia/Mes/Año): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Número de identificación (cédula o pasaporte): ");
        String id = scanner.nextLine();
        System.out.print("Género (Masculino, Femenino): ");
        String genero = scanner.nextLine();
        System.out.print("Número de teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();

        // Solicitar al candidato que ingrese el nombre del partido político
        System.out.print("Ingrese el nombre del partido político: ");
        String partidoPolitico = scanner.nextLine();

        // Verificar si el partido político ya existe en la lista, si no, agregarlo
        if (!partidosPoliticos.contains(partidoPolitico)) {
            partidosPoliticos.add(partidoPolitico);
            System.out.println("Partido político registrado exitosamente.");
        }

        Candidato candidato = new Candidato(nombre, apellido, fechaNacimiento, id, genero, telefono, correo, partidoPolitico);

        if (ValidadorCandidato.validarCandidato(candidato, candidatos)) {
            candidatos.put(id, candidato);
            System.out.println("Su registro se ha completado con éxito.");
        }
    }


    void mostrarInformacionProcesoElectoral() {
        System.out.println("=== Información del Proceso Electoral ===");

        // Mostrar candidatos y sus partidos políticos
        System.out.println("Candidatos y Partidos Políticos:");
        for (Candidato candidato : candidatos.values()) {
            System.out.println(candidato.getNombre() + " " + candidato.getApellido() + " - " + candidato.getPartidoPolitico());
        }

        // Mostrar el periodo electoral
        System.out.println("Periodo Electoral:");
        System.out.println("Desde las 05:00 AM hasta las " + HORA_CIERRE + ":00 PM");
    }

    void avanzarHora() {
        if (horaActual >= HORA_CIERRE) {
            System.out.println("Ya ha pasado la hora límite para emitir votos. El proceso electoral está cerrado.");
            procesoElectoralCerrado = true;
        } else {
            horaActual++;
            System.out.println("La hora ha avanzado a las " + horaActual + ":00.");
        }
    }

    void mostrarResultadosEleccion() {
        if (!procesoElectoralCerrado) {
            System.out.println("El proceso electoral aún no ha concluido. No hay resultados disponibles.");
            return;
        }

        System.out.println("=== Resultados Oficiales de las Elecciones ===");

        // Contadores
        int votosBlancos = 0;
        int votosNulos = 0;
        int votosValidos = 0;

        // Contadores de votos por candidato
        Map<Candidato, Integer> votosPorCandidato = new HashMap<>();

        // Contadores para encontrar el candidato más y menos votado
        int maxVotos = 0;
        int minVotos = Integer.MAX_VALUE;
        Candidato candidatoMasVotado = null;
        Candidato candidatoMenosVotado = null;

        // Contar votos nulos y en blanco y por cada candidato
        for (Votante votante : votantes.values()) {
            if (votante.getVotoEmitido()) {
                // Voto válido emitido
                if (votante.getOpcionVoto().equals(String.valueOf(candidatos.size() + 1))) {
                    votosBlancos++;
                } else if (votante.getOpcionVoto().equals(String.valueOf(candidatos.size() + 2))) {
                    votosNulos++;
                } else {
                    // Voto válido para un candidato
                    votosValidos++;
                    int numeroOpcion = Integer.parseInt(votante.getOpcionVoto());
                    Candidato candidato = candidatos.values().toArray(new Candidato[0])[numeroOpcion - 1];

                    // Actualizar contadores de votos por candidato
                    votosPorCandidato.put(candidato, votosPorCandidato.getOrDefault(candidato, 0) + 1);

                    // Actualizar candidato más votado
                    if (votosPorCandidato.get(candidato) > maxVotos) {
                        maxVotos = votosPorCandidato.get(candidato);
                        candidatoMasVotado = candidato;
                    }

                    // Actualizar candidato menos votado
                    if (votosPorCandidato.get(candidato) < minVotos) {
                        minVotos = votosPorCandidato.get(candidato);
                        candidatoMenosVotado = candidato;
                    }
                }
            }
        }

        // Calcular porcentajes
        double porcentajeVotosBlancos = (votosBlancos * 100.0) / votosValidos;
        double porcentajeVotosNulos = (votosNulos * 100.0) / votosValidos;
        double porcentajeVotosValidos = (votosValidos * 100.0) / (votosValidos + votosNulos + votosBlancos);

        // Mostrar resultados
        System.out.println("a) Cantidad de votos en blanco: " + votosBlancos);
        System.out.println("b) Cantidad de votos nulos: " + votosNulos);
        System.out.println("c) Cantidad de votos válidos: " + votosValidos);

        // Mostrar votos por cada candidato
        System.out.println("d) Cantidad de votos obtenidos por cada candidato:");
        for (Candidato candidato : votosPorCandidato.keySet()) {
            System.out.println("   " + candidato.getNombre() + " " + candidato.getApellido() + ": " + votosPorCandidato.get(candidato) + " votos");
        }

        // Mostrar candidato más votado
        System.out.println("e) Candidato más votado: " + candidatoMasVotado);

        // Mostrar candidato menos votado
        System.out.println("f) Candidato menos votado: " + candidatoMenosVotado);

        // Mostrar porcentajes
        System.out.println("g) Porcentaje de todos los candidatos:");
        for (Candidato candidato : votosPorCandidato.keySet()) {
            double porcentajeCandidato = (votosPorCandidato.get(candidato) * 100.0) / votosValidos;
            System.out.println("   " + candidato.getNombre() + " " + candidato.getApellido() + ": " + porcentajeCandidato + "%");
        }

        System.out.println("h) Porcentaje de votos en blanco: " + porcentajeVotosBlancos + "%");
        System.out.println("i) Porcentaje de votos nulos: " + porcentajeVotosNulos + "%");
        System.out.println("j) Porcentaje de votos válidos: " + porcentajeVotosValidos + "%");
    }

}
