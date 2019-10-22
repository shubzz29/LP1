#include <iostream>
#include<mpi/mpi.h>
#include<cstdlib>
using namespace std;

void binarySearch(int *arr,int start, int end,int key,int rank)
{
	while(start<=end)
	{
		int mid=(start+end)/2;
		if(arr[mid]==key)
		{
			cout<<"Element is  Found by processor "<<rank<<" .\n";
			return;
		}
		else if(arr[mid]<key)
		{
			start=mid+1;
		}
		else
		{
			end=mid-1;
		}
	}
}


int main(int argc, char **argv) 
{
	int n=6000;
	int *arr=new int[n];

	for(int i=0;i<n;i++)
	{
		arr[i]=i+1;
	}
	
	int key=4500;
	
	int rank,size;
	
	MPI_Init(&argc,&argv);
	
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	cout<<"Processor rank: "<<rank<<"\nSize : "<<size<<endl;
	
	int blockSize=n/size;

	int i;
	for(i=0;i<size;i++)
	{
		if(rank==i)
		{
			double start=MPI_Wtime();
			binarySearch(arr, rank*blockSize, (rank+1)*blockSize-1, key, rank);
			double end=MPI_Wtime();
			cout<<"Execution time of Processor "<<rank<<" is "<<(end-start)*1000<<endl;
		}
	}
	MPI_Finalize();

	return 0;
}
