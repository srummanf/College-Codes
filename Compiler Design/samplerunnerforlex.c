#include <stdio.h>
#include <math.h>
#define PI 3.14
void main()
{
    int radius_cir;
    float perimeter, area_1;
    int i, code;
    for (int i = 0; i <= 10; i++)
    {
        scanf("%d", &code);
        scanf("%d", &radius_cir);
        if (code == 1)
        {
            area_1 = PI * pow(radius_cir, 2);
            printf("area of the circle is %f", area_1);
        }
        else
        {
            perimeter = 2 * PI * radius_cir;
            printf("perimeter of the circle is %f", perimeter);
        }
    }
}
