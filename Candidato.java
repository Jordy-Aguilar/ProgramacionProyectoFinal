class Candidato extends Persona {
    private String fechaNacimiento;
    private String genero;
    private String telefono;
    private String correo;
    private String partidoPolitico;
    private int votos;

    public Candidato(String nombre, String apellido, String fechaNacimiento, String id, String genero,
                     String telefono, String correo, String partidoPolitico) {
        super(nombre, apellido, id);
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.telefono = telefono;
        this.correo = correo;
        this.partidoPolitico = partidoPolitico;
        this.votos = 0;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPartidoPolitico(String partidoPolitico) {
        this.partidoPolitico = partidoPolitico;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public String getPartidoPolitico() {
        return partidoPolitico;
    }

    public void incrementarVotos() {
        this.votos++;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + partidoPolitico;
    }
}