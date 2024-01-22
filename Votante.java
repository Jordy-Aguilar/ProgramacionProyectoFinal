class Votante extends Persona {
    private boolean votoEmitido;
    private String opcionVoto;

    public Votante(String nombre, String apellido, String id) {
        super(nombre, apellido, id);
        this.votoEmitido = false;
        this.opcionVoto = "";
    }

    public boolean getVotoEmitido() {
        return votoEmitido;
    }

    public void setVotoEmitido(boolean votoEmitido) {
        this.votoEmitido = votoEmitido;
    }

    public String getOpcionVoto() {
        return opcionVoto;
    }

    public void setOpcionVoto(String opcionVoto) {
        this.opcionVoto = opcionVoto;
    }
}
