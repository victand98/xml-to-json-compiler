package src;

import java.io.File;
import java.io.FileReader;

import jflex.Main;
import src.models.Token;

public class App {
    public static String path = "data" + File.separatorChar;

    public static void main(String[] args) throws Exception {
        // move the result to src path
        // only execute this once
        // generateFile();

        try {
            Lexer lexer = new Lexer(new FileReader(path + "code.jcr"));
            String result = "";

            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    result += "END";
                    break;
                }

                switch (token) {
                    case ERROR:
                        result += lexer.lexeme + "\t NO RECOGNIZED SYMBOL";
                        break;
                    case CADENA:
                        result += lexer.lexeme + "\t CADENA";
                        break;
                    case ENTERO:
                        result += lexer.lexeme + "\t ENTERO";
                        break;
                    case SIGNO_MAYOR:
                        result += lexer.lexeme + "\t SIGNO_MAYOR";
                        break;
                    case SIGNO_MENOR:
                        result += lexer.lexeme + "\t SIGNO_MENOR";
                        break;
                    case SLASH:
                        result += lexer.lexeme + "\t SLASH";
                        break;

                    default:
                        break;
                }

                System.out.println(result);
                result = "";
            }

        } catch (Exception e) {
            System.out.println("ERROR\n" + e.toString());
        }
    }

    public static void generateFile() {
        try {
            String[] files = { (path + "Lexer.flex") };
            Main.generate(files);
        } catch (Exception e) {
            System.out.println("Error generating file.");
        }
    }
}
