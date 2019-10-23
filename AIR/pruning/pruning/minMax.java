package pruning;
import pruning.display;

public class minMax {

    display d;
    int k1,k2;

    minMax()
    {
        k1=k2=0;
    }

    public int minMax(int depth,int nodeNo,boolean isMax,int scores[],int h) {

        if(depth == h) 
	{
            d.setColor(nodeNo);

            return scores[nodeNo];
        }

        else if(isMax) {
            int ans = Math.max(minMax(depth + 1, nodeNo * 2, false, scores, h), minMax(depth + 1, nodeNo * 2 + 1, false, scores, h));
            d.setText(ans,depth,nodeNo*2,nodeNo*2+1);
            return ans;
        }
        else {
            int ans = Math.min(minMax(depth + 1, nodeNo * 2, true, scores, h), minMax(depth + 1, nodeNo * 2 + 1, true, scores, h));
            d.setText(ans,depth,nodeNo*2,nodeNo*2+1);
            return ans;
        }

    }



    int return_height(int n)
    {
        if(n==1)
            return 0;


        else
            return 1+return_height(n/2);
    }

    public static void main(String[] args){

        System.out.println("For min max");
        int scores[]={3, 5, 6,9,1,2,0,-1};

        minMax m = new minMax();
        int height = m.return_height(scores.length);

        //System.out.println("height = "+height);

         m.d = new display();

        m.d.add_elements(scores,height);

        int result = m.minMax(0,0,true,scores,height);

        m.d.setRoot(result);

       System.out.println("optimal value is = "+result);


    }
}
