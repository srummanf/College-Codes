#include <iostream>
#include <string>
using namespace std;
// ParityBit
int main()
{
    int user;
    cout << "User Choice - 1. Sender's Generating Parity 2. Recevier Checking 
        Parity\n "; 
        cout
         << "Choice : ";
    cin >> user;
    switch (user)
    {
    case 1:
    {
        string dat;
        int ch;
        char p;
        cout << "\nEnter data : ";
        cin >> dat;
        cout << "\nSchema Choice - 1. Odd 2. Even 3. Invalid ";
        cout << "\nChoice : ";
        cin >> ch;
        if (ch == 1)
        {
            int cnt = 0;
            for (int i = 0; i < dat.length(); i++)
            {
                if (dat[i] == '1')
                {
                    cnt++;
                }
            }
            if (cnt % 2 == 1)
            {
                p = '0';
            }
            else
            {
                p = '1';
            }
        }
        else if (ch == 2)
        {
            int cnt = 0;
            for (int i = 0; i < dat.length(); i++)
            {
                if (dat[i] == '1')
                {
                    cnt++;
                }
            }
            if (cnt % 2 == 1)
            {
                p = '1';
            }
            else
            {
                p = '0';
            }
        }
        else
        {
            cout << "Invalid";
            return 0;
        }
        dat.push_back(p);
        cout << "With parity bit " << dat << endl;
        break;
    }
    case 2:
    {
        string dat;
        int ch;
        char p;
        cout << "\nEnter data : ";
        cin >> dat;
        cout << "\nSchema Choice - 1. Odd 2. Even 3. Invalid ";
        cout << "\nChoice : ";
        cin >> ch;
        if (ch == 1)
        {
            int cnt = 0;
            for (int i = 0; i < dat.length(); i++)
            {
                if (dat[i] == '1')
                {
                    cnt++;
                }
            }
            if (cnt % 2 == 1)
            {
                cout << "Correct - No Error\n";
            }
            else
            {
                cout << "Error in data\n";
            }
        }
        else if (ch == 2)
        {
            int cnt = 0;
            for (int i = 0; i < dat.length(); i++)
            {
                if (dat[i] == '1')
                {
                    cnt++;
                }
            }
            if (cnt % 2 == 1)
            {
                cout << "Error in data\n";
            }
            else
            {
                cout << "Correct - No error\n";
            }
        }
        else
        {
            cout << "Invalid\n";
        }
        break;
    }
    default:
        cout << "Invalid Choice\n";
    }
}