package src;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFile {
    public static void main(String[] args) {
        String path = "data" + File.separatorChar;

        String[] paths = { path + "Lexer.flex" };

        String[] pathCup = {
                "-parser", "Syntax", path + "Syntax.cup"
        };

        try {
            jflex.Main.generate(paths);
            java_cup.Main.main(pathCup);

            Path syn = Paths.get(System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar
                    + "sym.java");
            if (Files.exists(syn))
                Files.delete(syn);

            Files.move(Paths.get(System.getProperty("user.dir") + File.separatorChar + "sym.java"), syn);
            syn = Paths.get(System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar
                    + "Syntax.java");
            if (Files.exists(syn))
                Files.delete(syn);

            Files.move(Paths.get(System.getProperty("user.dir") + File.separatorChar + "Syntax.java"), syn);
            syn = Paths.get(System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar
                    + "LexerCup.java");
            if (Files.exists(syn))
                Files.delete(syn);

            Files.move(Paths.get(path + "LexerCup.java"), syn);

        } catch (Exception e) {
            System.out.println("An error occurred while generating the file\n" + e.toString());
        }

    }
}
