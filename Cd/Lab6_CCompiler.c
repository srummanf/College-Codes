Lex code
%{ 
 #include "y.tab.h" 
 int countn=0; 
%} 
%option yylineno 
alpha [a-zA-Z] 
digit [0-9] 
unary "++"|"--" 
%% 
"printf" { return PRINTF; } 
"scanf" { return SCANF; } 
"int" { return INT; } 
"float" { return FLOAT; } 
"char" { return CHAR; } 
"void" { return VOID; } 
"return" { return RETURN; } 
"for" { return FOR; } 
"if" { return IF; } 
"else" { return ELSE; } 
"#include"[ ]*<.+\.h> { return INCLUDE; } 
"true" { return TRUE; } 
"false" { return FALSE; } 
[-]?{digit}+ { return NUMBER; } 
[-]?{digit}+\.{digit}{1,6} { return FLOAT_NUM; } 
{alpha}({alpha}|{digit})* { return ID; } 
{unary} { return UNARY; } 
"<=" { return LE; } 
">=" { return GE; } 
"==" { return EQ; } 
"!=" { return NE; } 
">" { return GT; } 
"<" { return LT; } 
"&&" { return AND; } 
"||" { return OR; } 
"+" { return ADD; } 
"-" { return SUBTRACT; } 
"/" { return DIVIDE; } 
"*" { return MULTIPLY; } 
\/\/.* { ; } 
\/\*(.*\n)*.*\*\/ { ; } 
[ \t]* { ; } 
[\n] { countn++; } 
. { return *yytext; } 
["].*["] { return STR; } 
['].['] { return CHARACTER; } 
%% 
int yywrap() { 
 return 1; } 
Y code 
%{ 
 #include<stdio.h> 
 #include<string.h> 
 #include<stdlib.h> 
 #include<ctype.h> 
 #include"lex.yy.c" 
 void yyerror(const char *s); 
 int yylex(); 
 int yywrap(); 
%} 
%token VOID CHARACTER PRINTF SCANF INT FLOAT CHAR FOR 
IF ELSE TRUE FALSE NUMBER FLOAT_NUM ID LE GE EQ NE GT 
LT AND OR STR ADD MULTIPLY DIVIDE SUBTRACT UNARY 
INCLUDE RETURN 
%% 
program : headers main '(' ')' '{' body return '}' { 
printf("program compiled successfully_no_of_lines %d\n",countn);} 
; 
headers : headers headers 
| INCLUDE 
; 
main : datatype ID 
; 
datatype : INT 
| FLOAT 
| CHAR 
| VOID 
; 
body : FOR '(' statement ';' condition ';' statement ')' '{' 
body '}' 
| IF '(' condition ')' '{' body '}' else 
| statement ';' 
| body body 
| PRINTF '(' STR ',' ID ')' ';' 
 | PRINTF '(' STR ')' ';' 
| SCANF '(' STR ',' '&' ID ')' ';' 
; 
else : ELSE '{' body '}' 
| 
; 
condition : value relop value 
| TRUE 
| FALSE 
; 
statement : datatype ID init 
| ID '=' expression 
| ID relop expression 
| ID UNARY 
| UNARY ID 
; 
init : '=' value 
| 
; 
expression : expression arithmetic expression 
| value 
; 
arithmetic : ADD 
| SUBTRACT 
| MULTIPLY 
| DIVIDE 
; 
relop : LT 
| GT 
| LE 
| GE 
| EQ 
| NE 
; 
value : NUMBER 
| FLOAT_NUM 
| CHARACTER 
| ID 
; 
return : RETURN value ';' 
| 
; 
%% 
int main() { 
 yyparse(); 
} 
void yyerror(const char* msg) { 
 printf(" error - %s\n", msg); 
} 