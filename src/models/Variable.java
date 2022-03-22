package models;

public class Variable {
    private String identificador;
    private Object valor;

    public String getIdentificador() {
        return identificador;
    }

    public Object getValor() {
        return valor;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Identificador: " + identificador + "\t|\tValor: " + valor;
    }
}
