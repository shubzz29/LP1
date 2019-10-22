#include<iostream>
//#include<chrono>
#include<time.h>
#include "omp.h"
#include <stdio.h>

using namespace std;
//using namespace std::chrono;

void generate(int a[],int b[],int n)
{
	for(int i=0;i<n;i++)
	{
		a[i]=b[i]=n-i;
	}
}

void serial(int a[],int n)
{
    	time_t t;
    	t = clock();
	for(int i=0;i<n-1;i++)
	{
		for(int j=0;j<n-1;j++)
		{
			if(a[j]>a[j+1])
			{
                		int temp=a[j];
				a[j]=a[j+1];
				a[j+1]=temp;
			}
		}
	}
	t = clock()-t;
	double t2 = ((double)t)/CLOCKS_PER_SEC;

	cout<<"The serial time is "<<t2<<endl<<endl;
}



void parallel (int b[],int n)
{
	time_t t;
    	t = clock();
    	
	omp_set_num_threads(2);

	int first=0;
	for(int i=0;i<n;i++)
	{
		first=i%2;
		//all variables are shared except those declared within the parallel block
		#pragma omp parallel for shared(b,n,first)
		for(int j=first;j<n-1;j=j+2)
		{
			if(b[j]>b[j+1])
			{
				int temp=b[j];
				b[j]=b[j+1];
				b[j+1]=temp;
			}
		}
	}
	t = clock()-t;
	double t2 = ((double)t)/CLOCKS_PER_SEC;
	cout<<"The parallel time is "<<t2<<endl;
}

int main()
{

	cout<<"Enter the size"<<endl;
	int n;
	cin>>n;

	int a[n];
	int b[n];

	generate(a,b,n);
	
	serial(a,n);

	parallel(b,n);

    cout <<endl<<endl<<endl<<endl<<"SORTED ARRAY : ";
    /*for (int i = 0; i < n; i++)
        cout << b[i] << " ";
    cout <<endl<<endl<<endl<<endl;
*/
}
