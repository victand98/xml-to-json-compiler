package src;
import src.models.Token;

%%
%class Lexer
%type Token
D= 0 | [1-9][0-9]*
space=[ ,\t,\r,\n]+
Identifier= [a-zA-Z\_\-0-9]*

%{
    public String lexeme;
%}
%%
{space} {/*Ignore*/}
"//".* {/*Ignore*/}

 /* Numbers */
{D}+ {lexeme=yytext(); return Token.ENTERO;}

 /* Identifiers */
{Identifier} {lexeme=yytext(); return Token.CADENA;}

 /* Arithmetic operators */
">" {lexeme=yytext(); return Token.SIGNO_MAYOR;}
"<" {lexeme=yytext(); return Token.SIGNO_MENOR;}
"/" {lexeme=yytext(); return Token.SLASH;}

 /* Default Error */
 . {lexeme=yytext(); return Token.ERROR;}
