#include <stdbool.h> 
#include <stdio.h> 
#include <string.h> 
#include <stdlib.h> 
#define MAX_LINE_LENGTH 1000 
// Returns 'true' if the character is a DELIMITER. 
//bool isDelimiter(char ch) 
//{ 
// if (ch == ' ' || ch == '+' || ch == '-' || ch == '*' || 
// ch == '/' || ch == ',' || ch == ';' || ch == '>' || 
// ch == '<' || ch == '=' ) 
// return (true); 
// return (false); 
//} 
bool isDelimiter(char ch) 
{ 
 if (ch == ' ' || ch == ';' ) 
 return (true); 
 return (false); 
} 
// Returns 'true' if the character is an OPERATOR. 
bool isOperator(char ch) 
{ 
 if (ch == '+' || ch == '-' || ch == '*' || 
 ch == '/' || ch == '>' || ch == '<' || 
 ch == '=') 
 return (true); 
 return (false); 
} 
// Returns 'true' if the string is a VALID IDENTIFIER. 
bool validIdentifier(char* str) 
{ 
 
 
 if (str[0] == '0' || str[0] == '1' || str[0] == '2' || 
 str[0] == '3' || str[0] == '4' || str[0] == '5' || 
 str[0] == '6' || str[0] == '7' || str[0] == '8' || 
 str[0] == '9' || isDelimiter(str[0]) == true) 
 return (false); 
 return (true); 
} 
//Return true if the string is keyword 
bool isValidKeyword(char *str) 
{ 
 char kw[32][11] = {"if", "else", "for", "then", "while", 
"do","auto","const","double","short","struct","unsigned","break","continue","long","signed","swict
h","void","case", 
 "default","enum","goto","register","sizeof","typedef","volatile","extern","return","static","u
nion","int"}; 
 
 for(int i=0;i<32;i++) 
 { 
 int result = strcmp(str, kw[i]); 
 { 
 if(result==0) 
 { 
 return true; 
 } 
 } 
 } 
 return false; 
} 
// Returns 'true' if the string is an INTEGER. 
bool isInteger(char* str) 
{ 
 int i, len = strlen(str); 
 if (len == 0) 
 return (false); 
 for (i = 0; i < len; i++) { 
 if (str[i] != '0' && str[i] != '1' && str[i] != '2' 
 && str[i] != '3' && str[i] != '4' && str[i] != '5' 
 && str[i] != '6' && str[i] != '7' && str[i] != '8' 
 && str[i] != '9' || (str[i] == '-' && i > 0)) 
 return (false); 
 } 
 return (true); 
} 
// Extracts the SUBSTRING. 
char* subString(char* str, int left, int right) 
{ 
 int i; 
 char* subStr = (char*)malloc( 
 sizeof(char) * (right - left + 2)); 
 for (i = left; i <= right; i++) 
 subStr[i - left] = str[i]; 
 subStr[right - left + 1] = '\0'; 
 return (subStr); 
} 
// Parsing the input STRING. 
void parse(char* str) 
{ 
 int left = 0, right = 0; 
 int len = strlen(str); 
 while (right <= len && left <= right) { 
 if (isDelimiter(str[right]) == false) 
 right++; 
 if (isDelimiter(str[right]) == true && left == right) { 
 if (isDelimiter(str[right]) == true) 
 printf("%c\t\t\tDELIMITER\n", str[right]); 
 if (isOperator(str[right]) == true) 
 printf("%c\t\t\tOPERATOR\n", str[right]); 
 right++; 
 left = right; 
 } else if (isDelimiter(str[right]) == true && left != right 
 || (right == len && left != right)) { 
 char* subStr = subString(str, left, right - 1); 
 
 if (isInteger(subStr) == true) 
 printf("%s\t\t\tINTEGER\n", subStr); 
 
 else if (validIdentifier(subStr) == true 
 && isDelimiter(str[right - 1]) == false && 
isValidKeyword(subStr) ==true) 
 printf("%s\t\t\tVALID KEYWORD\n", subStr); 
 
 else if (validIdentifier(subStr) == true 
 && isDelimiter(str[right - 1]) == false) 
 printf("%s\t\t\tVALID IDENTIFIER\n", subStr); 
 else if (validIdentifier(subStr) == false 
 && isDelimiter(str[right - 1]) == false) 
 printf("%s IS NOT A VALID IDENTIFIER\n", subStr); 
 left = right; 
 } 
 } 
 return; 
} 
// DRIVER FUNCTION 
int main() 
{ 
 printf("LEXEME\t\t\tTOKENTYPE\n"); 
 FILE *textfile; 
 char line[MAX_LINE_LENGTH]; 
 
 textfile = fopen("input.c", "r"); 
 if(textfile == NULL) 
 return 1; 
 
 while(fgets(line, MAX_LINE_LENGTH, textfile)){ 
 // printf(line); 
 
 parse(line); // calling the parse function 
 } 
 
 fclose(textfile); 
 return 0; 
 
// char name[100]; 
// printf("Enter exp: "); 
// gets(name); 
// printf("LEXEME\t\tTOKENTYPE\n"); 
// parse(name); // calling the parse function 
 return (0); 
} 
Code for input.c file 
#include <stdio.h> 
void main() 
{ 
int a,b; 
int c = 5; 
scanf("%d %d",&a,&b); 
if(a>b) 
printf("A is greater"); 
else 
printf("B is greater"); 
}