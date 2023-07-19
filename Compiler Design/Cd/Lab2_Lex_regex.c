lex sample.l
cc lex.yy.c
./a.out a.txt 

LEX CODE 1 
%{ 
 int COMMENT = 0; 
%} 
delim [\t\n] 
ws {delim}+ 
digit [0-9] 
number {digit}+(\.{digit}+)? 
identifier [a-zA-Z][a-zA-Z0-9]* 
%% 
{ws} 
#.* {printf("\n %s is a Preprocessor",yytext);} 
int | 
float | 
main | 
if | 
else | 
printf | 
scanf | 
for | 
char | 
getch | 
while {printf("\n %s is a key word ", yytext);} 
\<.*\.h> {printf("\n %s is a header file ",yytext);} 
"/*" {COMMENT=1;} 
"*/" {COMMENT=0;} 
{identifier}(\[[0-9]*\])? {if(!COMMENT) printf("\n %s is an identifier", yytext);} 
\{ {if(!COMMENT) printf("\n Block Begins");} 
\} {if(!COMMENT) printf("\n Block End");} 
\".*\" {if(!COMMENT) printf("\n %s is a String", yytext);} 
{number} {if(!COMMENT) printf("\n %s is a Number", yytext);} 
= {if(!COMMENT) printf("\n %s is an assignment operator", yytext);} 
\+ | 
\- | 
\* | 
\/ | 
\% {if(!COMMENT) printf("\n %s is an Arithmetic operator", yytext);} 
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
SAMPLE INPUT CODE 
#include <stdio.h> 
#include <math.h> 
#define PI 3.14 
void main() 
{ 
int radius_cir; 
float perimeter,area_1; 
int i, code; 
for(int i=0;i<=10;i++) 
{ 
scanf("%d", &code); 
scanf("%d", &radius_cir); 
if(code==1) 
{ 
area_1=PI*pow(radius_cir,2); 
printf("area of the circle is %f", area_1); 
} 
else 
{ 
perimeter=2*PI*radius_cir; 
printf("perimeter of the circle is %f", perimeter); 
} 
} 
} 






LEX Code 2 
number A_[0-9]{10} 
nanumber A_[0-9]{11,} 
regnumber 21BCE[0-9]{4} 
delim [ ]* 
email [a-z]+[a-z.]+2021@vitstudent.ac.in 
name (^[ a-zA-Z]+|^.) 
phoneno [0-9]{10} 
dob [0-9]+[-][a-zA-z]*[-][0-9]+ 
%% 
{name} {printf(" %s-Name\t",yytext);} 
{delim} 
{email} {printf(" %s-VITEmailId",yytext);} 
{nanumber} {printf(" %s-NotApplication\t",yytext);} 
{number} {printf(" %s-ApplicationNumber\t",yytext);} 
{regnumber} {printf(" %s-RegNumber\t",yytext);} 
{phoneno} {printf(" %s-PhoneNumber\t",yytext);} 
{dob} {printf(" %s-DOB\n",yytext);} 
%% 
int main(int argc,char **argv){ 
if(argc>1){ 
FILE *f1; 
f1=fopen(argv[1],"r"); 
if(f1){ 
yyin=f1; 
} 
} 
yylex(); 
return 0; 
} 
int yywrap() 
{ 
return 0; 
