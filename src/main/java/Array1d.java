/*
 * Copyright (c) 2020.    @author:KamalitaBiswas
 */

import java.io.*;

public class Array1d {
    public static void main(String ar[]) throws Exception

    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        String NQ[] = br.readLine().split(" ");

        int N = Integer.parseInt(NQ[0]);

        int Q = Integer.parseInt(NQ[1]);



        String inputArr[]=br.readLine().split(" ");

        int arr[]=new int[N];

        for(int i=0;i<N;i++)

        {

            arr[i]=Integer.parseInt(inputArr[i]);

        }



        for(int i =0;i<Q;i++)

        {

            String queries[]=br.readLine().split(" ");

            int que = Integer.parseInt(queries[0]);

            if(que == 0)

            {

                int L = Integer.parseInt(queries[1]);

                int R = Integer.parseInt(queries[2]);

                if(arr[R-1]==0)System.out.println("EVEN");

                else System.out.println("ODD");

            }

            else if(que == 1)

            {

                int index=Integer.parseInt(queries[1]);

                if(arr[index-1] == 0)arr[index-1]=1;

                else arr[index-1]=0;





            }

        }

    }





}
