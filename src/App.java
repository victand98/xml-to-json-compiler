package src;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java_cup.runtime.Symbol;

public class App {
    public static String path = "data" + File.separatorChar;

    public static void main(String[] args) throws Exception {
        String content = Files.readString(Path.of(path + "code.jcr"), StandardCharsets.UTF_8);

        System.out.println("ENTRADA:\n " + content + "\n");

        String code = "<persona>" +
                "\n <nombre>" +
                "\n Luis" +
                "\n </nombre>" +
                "\n <apellido>" +
                "\n Rojas" +
                "\n </apellido>" +
                "\n <edad>" +
                "\n 18" +
                "\n </edad>" +
                "\n </persona>";

        Syntax syntax = new Syntax(new LexerCup(new StringReader(content)));
        Symbol aux = null;
        try {
            aux = syntax.parse();
            System.out.println("The code has been successfully executed");
        } catch (Exception e) {
            System.out.println("ERROR:\n" + e.toString());
            Symbol sym = syntax.getS();
            System.out.println("Syntax error in the line " + (sym.right + 1) + " column " + (sym.left + 1)
                    + "; Wrong syntax: " + sym.value);

        }
    }
}
