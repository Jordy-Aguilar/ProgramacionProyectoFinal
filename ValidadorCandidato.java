import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidadorCandidato {

    public static boolean validarCandidato(Candidato candidato, Map<String, Candidato> candidatosRegistrados) {
        if (!esMayorDe17Anios(candidato.getFechaNacimiento())) {
            System.out.println("Lamentablemente, no cumple con los requisitos necesarios (menor de 17 años).");
            return false;
        }

        if (!tieneCedulaValida(candidato.getId())) {
            System.out.println("La cédula debe tener 10 dígitos. Registro de candidatura fallido.");
            return false;
        }

        if (!tieneCorreoValido(candidato.getCorreo())) {
            System.out.println("El correo electrónico debe contener '@' y terminar con '.com'. Registro de candidatura fallido.");
            return false;
        }

        if (!tieneTelefonoValido(candidato.getTelefono())) {
            System.out.println("El número de teléfono debe empezar con '09' y tener 10 dígitos. Registro de candidatura fallido.");
            return false;
        }

        if (!esGeneroValido(candidato.getGenero())) {
            System.out.println("El género debe ser 'Masculino' o 'Femenino'. Registro de candidatura fallido.");
            return false;
        }

        if (yaRegistradoEnOtroPartido(candidato, candidatosRegistrados)) {
            System.out.println("El candidato ya está registrado para otro partido político. Registro de candidatura fallido.");
            return false;
        }

        return true;
    }

    private static boolean esMayorDe17Anios(String fechaNacimiento) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);
            LocalDate fechaActual = LocalDate.now();

            long edad = ChronoUnit.YEARS.between(fechaNac, fechaActual);
            return edad > 17;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean tieneCedulaValida(String cedula) {
        return cedula.length() == 10;
    }

    private static boolean tieneCorreoValido(String correo) {
        String regex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(correo).matches();
    }

    private static boolean tieneTelefonoValido(String telefono) {
        return telefono.startsWith("09") && telefono.length() == 10;
    }

    private static boolean esGeneroValido(String genero) {
        return genero.equalsIgnoreCase("Masculino") || genero.equalsIgnoreCase("Femenino");
    }

    private static boolean yaRegistradoEnOtroPartido(Candidato candidato, Map<String, Candidato> candidatosRegistrados) {
        return candidatosRegistrados.values().stream()
                .anyMatch(c -> c.getPartidoPolitico().equals(candidato.getPartidoPolitico()));
    }
}
