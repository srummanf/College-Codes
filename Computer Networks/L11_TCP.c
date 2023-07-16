#include <iostream>

using namespace std;

void tcp_cong(int max_cwnd, int max)
{
    int maxthres = max_cwnd / 2;

    for (int i = 1; i <= maxthres; i = i * 2)
    {
        cout << "cwnd = " << i << endl;
        cout << "ack = " << i << endl;
    }

    for (int i = maxthres + 1; i <= max; i++)
    {
        cout << "cwnd = " << i << endl;
        cout << "ack = " << i << endl;
    }
}

void tcp_cong2(int max_cwnd, int timeout, int dup)
{
    int maxthres = timeout / 2;
    //

    for (int i = 1; i <= dup; i = i * 2)
    {
        cout << "cwnd = " << i << endl;
        cout << "ack = " << i << endl;
    }

    //

    for (int i = 1; i <= maxthres; i = i * 2)
    {
        cout << "cwnd = " << i << endl;
        cout << "ack = " << i << endl;
    }

    for (int i = maxthres + 1; i <= dup; i++)
    {
        cout << "cwnd = " << i << endl;
        cout << "ack = " << i << endl;
    }

    maxthres = dup / 2;
    int j = 1;

    cout << "cwnd = " << 1 << endl;
    cout << "ack = " << 1 << endl;

    for (int i = 2; i <= maxthres; i = j * 2)
    {
        j++;
        cout << "cwnd = " << i << endl;
        cout << "ack = " << i << endl;
    }

    for (int i = maxthres + 1; i <= max_cwnd; i++)
    {
        cout << "cwnd = " << i << endl;
        cout << "ack = " << i << endl;
    }
}

int main()
{
    int max_cwnd = 32;
    int timeout = 8;
    int dup = 12;

    cout << "CASE 1" << endl;
    cout << "______" << endl;

    tcp_cong(max_cwnd, max_cwnd);

    cout << "-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-." << endl;

    cout << "CASE 2" << endl;
    cout << "______" << endl;

    tcp_cong(timeout, max_cwnd);

    cout << "-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-." << endl;

    cout << "CASE 3" << endl;
    cout << "______" << endl;

    tcp_cong(dup, max_cwnd);

    cout << "-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-." << endl;

    cout << "CASE 4" << endl;
    cout << "______" << endl;

    tcp_cong2(max_cwnd, timeout, dup);

    return 0;
}
