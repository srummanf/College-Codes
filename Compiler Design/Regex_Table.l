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
}