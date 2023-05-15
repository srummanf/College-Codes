#include <iostream>

using namespace std;

int main()
{
    int i, j, k, l;

    // Dividend
    int divd_size;
    cout << "\n Enter number of bits in data input: ";
    cin >> divd_size;

    int divd[20];
    cout << "\n Enter the data input bits with spaces:";
    for (i = 0; i < divd_size; i++)
    {
        cin >> divd[i];
    }

    // Divisor
    int poly_size;
    cout << "\n Enter size of polynomial: ";
    cin >> poly_size;
    poly_size = poly_size + 1;
    int gs = poly_size;

    int poly[poly_size];
    cout << "\n Enter the coefficient of polynomial:";
    for (i = 0; i < poly_size; i++)
    {
        cout << "\n Enter the coefficient of degree " << i << ":";
        cin >> poly[i];
    }

    int g[poly_size];
    for (i = 0; i <= poly_size; i++)
    {
        g[i] = poly[i];
    }

    int size_checkbit = poly_size - 1;

    // Apppend zero;
    int x = divd_size;
    for (int i = 1; i <= size_checkbit; i++)
    {
        divd[x] = 0;
        x++;
    }

    int totalsize_divd = divd_size + size_checkbit;

    //
    int temp[20];
    for (i = 0; i < 20; i++)
    {
        temp[i] = divd[i];
    }
    cout << "\n Message after appending 0's :";
    for (i = 0; i < totalsize_divd; i++)
    {
        cout << temp[i];
    }

    // Division
    for (i = 0; i < divd_size; i++)
    {
        j = 0;
        k = i;
        // check whether it is divisible or not
        if (temp[k] >= g[j])
        {
            for (j = 0, k = i; j < gs; j++, k++)
            {
                if ((temp[k] == 1 && g[j] == 1) || (temp[k] == 0 && g[j] == 0))
                {
                    temp[k] = 0;
                }
                else
                {
                    temp[k] = 1;
                }
            }
        }
    }

    // CRC
    int crc[15];
    for (i = 0, j = divd_size; i < size_checkbit; i++, j++)
    {
        crc[i] = temp[j];
    }
    cout << "\n CRC bits: ";
    for (i = 0; i < size_checkbit; i++)
    {
        cout << crc[i];
    }

    cout << "\n Transmitted Frame: ";
    int tf[30];
    for (i = 0; i < divd_size; i++)
    {
        tf[i] = divd[i];
    }
    for (i = divd_size, j = 0; i < divd_size + size_checkbit; i++, j++)
    {
        tf[i] = crc[j];
    }
    for (i = 0; i < divd_size + size_checkbit; i++)
    {
        cout << tf[i];
    }

    for (i = 0; i < divd_size + size_checkbit; i++)
    {
        temp[i] = tf[i];
    }

    int flag = 1;
    // Adding error , flag = 0 no error , flag = 1 error anually added
    // for no error change j = 0, for error change j = 1

    temp[0] = 0;
    flag = 1;

    cout << "\n\n Receiver side : \n";
    cout << "\n The received data is: ";
    for (i = 0; i < divd_size + size_checkbit; i++)
    {
        cout << temp[i];
    }

    // Division
    for (i = 0; i < divd_size + size_checkbit; i++)
    {
        j = 1;
        k = i;
        if (temp[k] >= g[j])
        {
            for (j = 0, k = i; j < gs; j++, k++)
            {
                if ((temp[k] == 1 && g[j] == 1) || (temp[k] == 0 && g[j] == 0))
                {
                    temp[k] = 0;
                }
                else
                {
                    temp[k] = 1;
                }
            }
        }
    }

    cout << "\n The remainder is: ";
    int rrem[15];
    for (i = divd_size, j = 0; i < divd_size + size_checkbit; i++, j++)
    {
        rrem[j] = temp[i];
    }
    for (i = 0; i < size_checkbit; i++)
    {
        cout << rrem[i];
    }

    int flagg = 0;
    for (i = 0; i < size_checkbit; i++)
    {
        if (rrem[i] != 0)
        {
            flagg = 1;
        }
    }

    if (flagg == 0)
    {
        cout << "\n The data is accepted\nSince Remainder Is 0 Hence Message Transmitted From Sender To Receiver Is Correct";
    }
    else
    {
        cout << "\n The data is rejected\nSince Remainder Is Not 0 Hence Message Transmitted From Sender To Receiver Contains Error";
    }
}
