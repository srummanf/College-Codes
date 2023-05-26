// 2. Construct a Recursive Decent Parser for the following Grammar
// E → E # T | T
// T → T & F | F
// F → ! F | ( E ) | [a-z]
//  Parse the following string
// 1) a#b&!c
// 2) a&#b
// 3) a#b&!c)
// 4) (a#b)&c)

#include <stdio.h>
int i = 0;
char input[100];
int main()
{

    scanf("%s", input);

    if (E())
    {
        if (input[i] == '$')
            printf("\n the string is accepted\n");
        else
            printf("\n the string is not accepted\n");
    }
    else
        printf("\n the string is not accepted \n");
}

int E()
{
    if (T())
    {
        if (EP())
        {
            printf("E-->TE`\n");
            return (1);
        }
        else
            return (0);
    }
    else
        return (0);
}

int EP()
{
    if (input[i] == '#')
    {
        i++;
        if (T())
        {
            if (EP())
            {
                printf("E`-->#TE`\n");
                return (1);
            }
            else
                return (0);
        }
        else
            return (0);
    }
    else
    {
        printf("E`--> epsilon\n");
        return (1);
    }
}

int T()
{
    if (F())
    {
        if (TP())
        {
            printf("T-->FT`\n");
            return (1);
        }
        else
            return (0);
    }
    else
        return (0);
}

int TP()
{
    if (input[i] == '&')
    {
        i++;
        if (F())
        {
            if (TP())
            {
                printf("T`-->&FT`\n");
                return (1);
            }
            else
                return (0);
        }
        else
            return (0);
    }
    else
    {
        printf("T`-->epsilon\n");
        return (1);
    }
}

int F()
{
    if (input[i] == '(')
    {
        i++;
        if (E())
        {
            if (input[i] == ')')
            {
                i++;
                printf("F-->(E)\n");
                return (1);
            }
            else
                return (0);
        }
        else
            return (0);
    }
    else if (input[i] >= 'a' && input[i] <= 'z' || input[i] >= 'a' && input[i] <= 'z')
    {
        i++;
        printf("F-->[a-z]\n");
        return (1);
    }
    else if(input[i] == '!')
    {
        i++;
        if(F())
        {
            printf("F-->!F\n");
        }
        printf("F-->epsilon\n");
        return (1);
    }
    
    else
        return (0);
}
