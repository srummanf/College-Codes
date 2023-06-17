%{
#include <stdio.h>
extern int yylex();
extern int yylineno;
extern char* yytext;
void yyerror(const char* s) {
    fprintf(stderr, "Syntax Error at line %d: %s\n", yylineno, s);
}
%}

%token IDENTIFIER NUMBER PLUS MINUS MULTIPLY DIVIDE MODULO POWER LPAREN RPAREN INCREMENT DECREMENT

%%

expr: expr PLUS expr
    | expr MINUS expr
    | expr MULTIPLY expr
    | expr DIVIDE expr
    | expr MODULO expr
    | expr POWER expr
    | LPAREN expr RPAREN
    | IDENTIFIER
    | NUMBER
    | INCREMENT IDENTIFIER
    | DECREMENT IDENTIFIER
    ;

%%
