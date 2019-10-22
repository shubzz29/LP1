import java.util.*;
public class pruning
{
    int MAX=1000;
    int MIN=-1000;
    int func(int depth, int nodeno, boolean isMax, int score[], int h,int alpha, int beta)
    {
        int bestVal=0, value;
        if(depth == h)
        {
            return score[nodeno];
        }
        else if(isMax)
        {
            bestVal = MIN;
            for(int i=0;i<2;i++)
            {
                value = func(depth+1, nodeno*2+i, false, score, h, alpha, beta);
                bestVal= Math.max(bestVal, value);
                alpha = Math.max(alpha, bestVal);
                if(beta<=alpha)
                {
                    break;
                }
            }
            System.out.print(bestVal+ "->");
            return bestVal;
        }
        else
        {
            bestVal = MAX;
            for(int i=0;i<2;i++)
            {
                value = func(depth+1, nodeno*2+i, true, score, h, alpha, beta);
                bestVal= Math.min(bestVal, value);
                beta = Math.min(beta, bestVal);
                if(beta<=alpha)
                {
                    break;
                }
            }
            System.out.print(bestVal+ "->");
            return bestVal;
        }
    }
    
    
    int get_height(int n)
    {
        if(n==1)
        {
            return 0;
        }
        else
        {
            return 1+get_height(n/2);
        }
    }
    
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter total elements: ");
        int n=sc.nextInt();
        System.out.println("Enter elements: ");
        int[] score = new int[n];
        for(int i=0;i<n;i++){
            score[i]=sc.nextInt();
        }
        pruning p = new pruning();
        int h = p.get_height(score.length);
        System.out.println("Height is = "+h);
        System.out.println("Nodes covered in sequence-: ");
        int res= p.func(0,0,true, score,h, p.MIN, p.MAX);
        System.out.println("\nResult-:"+ res);
    }
}
/*
Enter total elements: 
16
Enter elements: 
3
4
2
1
7
8
9
10
2
11
1
12
14
9
13
16
Nodes covered in sequence-: 
3->2->3->7->7->3->2->1->2->2->3->
Result-:3
*/