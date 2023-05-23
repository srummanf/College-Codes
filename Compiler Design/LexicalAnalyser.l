%{
	int COMMENT = 0;
%}
delim [\t\n]
ws  {delim}+
digit [0-9]
number  {digit}+(\.{digit}+)?
identifier  [a-zA-Z][a-zA-Z0-9]*
%%
{ws}
#.*  {printf("\n %s is a Preprocessor",yytext);}
int |
float |
main |
if |
else |
printf  |
scanf  |
for |
char  |
getch |
while {printf("\n %s is a key word ", yytext);}
\<.*\.h>  {printf("\n %s is a header file ",yytext);}
"/*" {COMMENT=1;}
"*/" {COMMENT=0;}
{identifier}(\[[0-9]*\])?  {if(!COMMENT) printf("\n %s  is an identifier", yytext);}
\{ {if(!COMMENT) printf("\n  Block Begins");}
\} {if(!COMMENT) printf("\n  Block End");}
\".*\"  {if(!COMMENT) printf("\n %s is a String", yytext);}
{number} {if(!COMMENT) printf("\n %s is a Number", yytext);}
= {if(!COMMENT)  printf("\n %s is an assignment operator", yytext);}
\+ |
\- |
\* |
\/ |
\% {if(!COMMENT) printf("\n %s is  an Arithmetic operator", yytext);}
\<= |
\>= |
\< |
\> |
\!= |
== {if(!COMMENT) printf("\n %s is a Relative Operator", yytext);}
%%
int main(int argc, char **argv)
{
if(argc>1)
{
FILE *f1;
f1=fopen(argv[1],"r");
if(f1)
{
yyin=f1;
}
}
yylex();
return 0;
}
int yywrap()
{
return 0;
}
