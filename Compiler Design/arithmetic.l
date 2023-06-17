%{
#include "y.tab.h"
%}

%%
"+"         { return PLUS; }
"-"         { return MINUS; }
"/"         { return DIVIDE; }
"*"         { return MULTIPLY; }
"%"         { return MODULO; }
"^"         { return POWER; }
"("         { return LPAREN; }
")"         { return RPAREN; }
"++"        { return INCREMENT; }
"--"        { return DECREMENT; }
[a-zA-Z]+   { yylval = strdup(yytext); return IDENTIFIER; }
[0-9]+      { yylval = atoi(yytext); return NUMBER; }
[\t\n ]+    ;  // Ignore whitespace and newlines
.           { return yytext[0]; }
%%

int yywrap() {
    return 1;
}
