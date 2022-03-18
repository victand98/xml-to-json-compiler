package helpers;

import java.util.ArrayList;
import models.Variable;

public class Utils {

    public static Object transformar(String valor) {
        Object aux = valor;

        try {
            if (valor != null) {
                aux = Integer.parseInt(aux.toString());
            }
        } catch (Exception e) {
        }

        return aux;
    }

    public static ArrayList<Variable> addVariables(ArrayList<Variable> variables, String valor,
            String identificador) {
        Variable variable = new Variable();
        variable.setIdentificador(identificador);
        variable.setValor(transformar(valor));
        variables.add(variable);
        return variables;
    }

    public static String toJson(ArrayList<Variable> variables) {
        String json = "{\n";
        Integer subs = 0;

        for (int i = variables.size() - 1; i >= 0; i--) {
            Variable entry = variables.get(i);
            if (i == variables.size() - 1) {
                json += "\"" + entry.getIdentificador() + "\"" + ": {\n";
                continue;
            }

            if (entry.getValor() == null && subs > 0)
                json += "\t},\n";

            json += "\t\"" + entry.getIdentificador() + "\"" + ":";
            if (entry.getValor() == null) {
                subs++;
                json += "{\n";
            } else {
                if (entry.getValor().getClass().equals(Integer.class)) {
                    json += entry.getValor() + ",\n";
                } else
                    json += "\"" + entry.getValor() + "\"," + "\n";
            }
        }

        if (subs == 1)
            json += "\t}\n";
        json += "}\n}";

        return json;
    }
}
