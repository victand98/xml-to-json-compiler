package helpers;

import java.io.Console;
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
        variable.setValor(transformar(valor != null ? valor.trim() : valor));
        variables.add(variable);
        return variables;
    }

    public static String toJson(ArrayList<Variable> variables) {
        String json = "{\n";
        Integer initialIndex = variables.size() - 1;
        Integer subs = 0;

        for (int i = initialIndex; i >= 0; i--) {
            Variable entry = variables.get(i);
            Boolean isLastElement = isLastElement(i, 0);
            Boolean nextIsNull = subs > 1 && isLastElement == false && variables.get(i - 1).getValor() == null;
            subs = entry.getValor() == null ? subs + 1 : subs;

            if (i == initialIndex) {
                json += "\"" + entry.getIdentificador() + "\": ";
                json += getJSONValue(entry.getValor(), isLastElement || nextIsNull);
                continue;
            }

            json += "\t\"" + entry.getIdentificador() + "\": ";
            json += getJSONValue(entry.getValor(), isLastElement || nextIsNull);

            if (nextIsNull) {
                json += "\t},\n";
                subs--;
            }
        }

        for (int i = 0; i < subs; i++) {
            if (i == subs - 1) {
                json += "}\n";
                continue;
            }
            json += "\t}\n";
        }

        json += "}";

        return json;
    }

    static String getJSONValue(Object value, Boolean isLastElement) {
        if (value == null) {
            return "{\n";
        }

        if (value.getClass().equals(Integer.class)) {
            return value + (isLastElement ? "\n" : ",\n");
        } else
            return "\"" + value + "\"" + (isLastElement ? "\n" : ",\n");
    }

    static Boolean isLastElement(Integer currentIndex, Integer lastIndex) {
        if (currentIndex == lastIndex)
            return true;

        return false;
    }
}
