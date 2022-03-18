import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import helpers.Utils;
import java_cup.runtime.Symbol;

public class App {
    public static String path = "data" + File.separatorChar;

    public static void main(String[] args) throws Exception {
        String content = Files.readString(Path.of(path + "code.xml"), StandardCharsets.UTF_8);
        System.out.println("ENTRADA:\n" + content + "\n");
        Syntax syntax = new Syntax(new LexerCup(new StringReader(content)));

        try {
            syntax.parse();
            // TablaSimbolos.ver();
            String jsonOutput = Utils.toJson(syntax.action_obj.variables);
            System.out.println("SALIDA:\n" + jsonOutput + "\n");
            System.out.println("The code has been successfully executed");
        } catch (Exception e) {
            e.printStackTrace();
            Symbol sym = syntax.getS();

            if (sym != null)
                System.out.println("Syntax error in the line " + (sym.right + 1) + " column " + (sym.left + 1)
                        + "; Wrong syntax: " + sym.value);
            else
                System.out.println(e);
        }
    }
}
