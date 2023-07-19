yacc 
%{ 
#include<ctype.h> 
#include<stdio.h> 
#include<stdlib.h> 
int yylex(); 
int yywrap(); 
%} 
%token alpha 
%% 
/* Rule Section */ 
/*After Evaluating the expression E,S prints correct*/ 
S : E {printf("syntax is Correct\n\n");} 
; 
/*The expression parser uses three different symbols T,F and P, to set the 
precedence and associativity of operators*/ 
E : E '+' E 
| E '-' E 
| E '*' E 
| E '/' E 
| E '%' E 
| E '^' E 
| E'+''+' 
|'+''+'E 
| E'-''-' 
|'-''-'E 
|'('E')' 
| F 
; 
F : alpha ; 
%% 
//driver code 
#include"lex.yy.c" 
int main() 
{ 
printf("Enter infix expression: "); 
yyparse(); 
} 
yyerror() 
{ 
printf(" Error\n"); 
}


lexcode 
%{ 
#include "y.tab.h" 
extern int yylval; 
%} 
%% 
[a-z]+ {yylval=yytext; return alpha;} 
[\t] ; 
[\n] return 0; 
. return yytext[0]; 
%% 
int yywrap() 
{ 
return 0; 
} 