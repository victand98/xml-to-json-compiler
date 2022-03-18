import java_cup.runtime.Symbol;
%%
%public
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
Letters=[a-zA-Z\_\-0-9]*
LettersString=[a-zA-Z\_\-0-9\s]*
Digits=([0-9]+\.?[0-9]*|\.[0-9]+)
Space=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%
/* Spaces */
{Space} {/*ignore*/}

/* Comments */
("//"(.)*) {/*ignore*/}

/* Symbols */
("<") {
    TablaSimbolos.addArrayList("SIGNO_MENOR");
    TablaSimbolos.addArrayList2(yytext());
    TablaSimbolos.addArrayList3(yyline + 1);
    TablaSimbolos.addArrayList4(yycolumn + 1);
    return new Symbol(sym.SIGNO_MENOR, yychar, yyline, yytext());
}
(">") {
    TablaSimbolos.addArrayList("SIGNO_MAYOR");
    TablaSimbolos.addArrayList2(yytext());
    TablaSimbolos.addArrayList3(yyline + 1);
    TablaSimbolos.addArrayList4(yycolumn + 1);
    return new Symbol(sym.SIGNO_MAYOR, yychar, yyline, yytext());
}
("/") {
    TablaSimbolos.addArrayList("SIGNO_SLASH");
    TablaSimbolos.addArrayList2(yytext());
    TablaSimbolos.addArrayList3(yyline + 1);
    TablaSimbolos.addArrayList4(yycolumn + 1);
    return new Symbol(sym.SIGNO_SLASH, yychar, yyline, yytext());
}

 /* Numbers */
("(-"{Digits}+")")|{Digits}+ {
    TablaSimbolos.addArrayList("NUMERO");
    TablaSimbolos.addArrayList2(yytext());
    TablaSimbolos.addArrayList3(yyline + 1);
    TablaSimbolos.addArrayList4(yycolumn + 1);
    return new Symbol(sym.NUMERO, yychar, yyline, yytext());
}

 /* Identifiers */
{Letters}({Letters}|{Digits})* {
    TablaSimbolos.addArrayList("IDENTIFICADOR");
    TablaSimbolos.addArrayList2(yytext());
    TablaSimbolos.addArrayList3(yyline + 1);
    TablaSimbolos.addArrayList4(yycolumn + 1);
    return new Symbol(sym.IDENTIFICADOR, yychar, yyline, yytext());
}

/* String */
{LettersString} {
    TablaSimbolos.addArrayList("CADENA");
    TablaSimbolos.addArrayList2(yytext());
    TablaSimbolos.addArrayList3(yyline + 1);
    TablaSimbolos.addArrayList4(yycolumn + 1);
    return new Symbol(sym.CADENA, yychar, yyline, yytext());
}

 /* Default Error */
. {
    TablaSimbolos.addArrayList("ERROR");
    TablaSimbolos.addArrayList2(yytext());
    TablaSimbolos.addArrayList3(yyline + 1);
    TablaSimbolos.addArrayList4(yycolumn + 1);
    return new Symbol(sym.ERROR, yychar, yyline, yytext());
}