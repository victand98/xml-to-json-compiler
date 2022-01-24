package src;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
Letters=[a-zA-Z\_\-0-9]*
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
("<") {return new Symbol(sym.SIGNO_MENOR, yychar, yyline, yytext());}
(">") {return new Symbol(sym.SIGNO_MAYOR, yychar, yyline, yytext());}
("/") {return new Symbol(sym.SIGNO_SLASH, yychar, yyline, yytext());}

 /* Numbers */
("(-"{Digits}+")")|{Digits}+ {return new Symbol(sym.NUMERO, yychar, yyline, yytext());}

 /* Identifiers */
{Letters}({Letters}|{Digits})* {return new Symbol(sym.IDENTIFICADOR, yychar, yyline, yytext());}

/* String */
{Letters} {return new Symbol(sym.CADENA, yychar, yyline, yytext());}

 /* Default Error */
. {return new Symbol(sym.ERROR, yychar, yyline, yytext());}