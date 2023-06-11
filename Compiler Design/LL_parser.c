#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int i = 0, top = 0;
char stack[20], ip[20];
void push(char c)
{
    if (top >= 20)
        printf("Stack Overflow");
    else
        stack[top++] = c;
}

void pop()
{
    if (top < 0)
        printf("Stack underflow");
    else
        top--;
}
void error()
{
    printf("\n Syntax Error");
    exit(0);
}
int main()
{
    int n;
    printf("Enter the string to be parsed\n");
    scanf("%s", ip);
    n = strlen(ip);
    ip[n] = '$';
    ip[n + 1] = '\0';
    push('$');
    push('E');
    while (ip[i] != '\0')
    {
        if (ip[i] == '$' && stack[top - 1] == '$')
        {
            printf("\n Parsing done successful\n");
            return 0;
        }
        else if (ip[i] == stack[top - 1])
        {
            printf("\n match of %c\n", ip[i]);
            i++;
            pop();
        }
        else
        {
            if (stack[top - 1] == 'E' && (ip[i] == 'i' || ip[i] == '('))
            {
                printf("\nE->TA\n");
                pop();
                push('A');
                push('T');
            }
            else if (stack[top - 1] == 'A' && ip[i] == '+')
            {
                printf("\nA->+TA\n");
                pop();
                push('A');
                push('T');
                push('+');
            }
            else if (stack[top - 1] == 'A' && (ip[i] == ')' || ip[i] == '$'))
            {
                printf("\nA->epsilon");
                pop();
            }
            else if (stack[top - 1] == 'T' && (ip[i] == 'i' || ip[i] == '('))
            {
                printf("\nT->FB\n");
                pop();
                push('B');
                push('F');
            }
            else if (stack[top - 1] == 'B' && (ip[i] == '+' || ip[i] == ')' || ip[i] == '$'))
            {
                printf("\nB->epsilon\n");
                pop();
            }
            else if (stack[top - 1] == 'B' && ip[i] == '*')
            {
                printf("\nB->*FB\n");
                pop();
                push('B');
                push('F');
                push('*');
            }
            else if (stack[top - 1] == 'F' && ip[i] == 'i')
            {
                printf("\nF->i\n");
                pop();
                push('i');
            }
            else if (stack[top - 1] == 'F' && ip[i] == '(')
            {
                printf("\nF->(E)\n");
                pop();
                push(')');
                push('E');
                push('(');
            }
            else
            {
                error();
            }
        }
    }
    return 0;
}
