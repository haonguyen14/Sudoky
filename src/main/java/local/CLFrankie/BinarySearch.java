package local.CLFrankie;

import java.util.ArrayList;

public class BinarySearch
{
 public static int search(int[] a, int num)
 {
  int left = 0;
  int right = a.length - 1;

  while(left <= right)
  {
   int middle = (left + right) / 2;
   if(a[middle] == num)
    return middle;
   else
   {
    if(num < a[middle])
    {
     right = middle - 1;
    }
    else  // num > a[middle]
    {
     left = middle + 1;
    }
   }
  }

  return -1;
 }


 public static int search(ArrayList<Integer> a, int num)
        {
                int left = 0;
                int right = a.size() - 1;

                while(left <= right)
                {
                        int middle = (left + right) / 2;
                        if(a.get(middle) == num)
                                return middle;
                        else
                        {
                                if(num < a.get(middle))
                                {
                                        right = middle - 1;
                                }
                                else            // num > a[middle]
                                {
                                        left = middle + 1;
                                }
                        }
                }

                return -1;
        }
  
}
