package search;

import java.util.Scanner;

public class BinarySearchShift {
    //Pred: (∀i: arr[i] - int) && (∀i, k - 2 >= i >= 0: arr[i] > arr[i + 1])
    //      && (∀i, arr.length >= i >= k: arr[i] > arr[i + 1])
    // 5 3 1 4 2
    // 4 3 1 6 5
    //Post: k
    public static int Shift (int[] arr){
        int l = 0;
        int r = arr.length - 1;
        int mid;
        while (r - 1 > l){
            // r' > l'
            mid = l + (r - l) / 2;
            //r' > mid > l'
            if (arr[r] > arr[mid]){
                //∀i, mid' - 1 >= i >= 0: arr[i] > arr[i + 1])
                //k >= mid'
                //l' < mid' <= k <= r'
                l = mid;
                //l < l' <= k <= r'
                //r' - l' < r - l
            } else {
                //∀i, r' - 1 >= i >= mid': arr[i] > arr[i + 1]
                //k <= mid'
                //l' <= k <= mid' < r'
                r = mid;
                // l' <= k <= r' < r
                // r' - l' < r - l
            }
            // l' <= k <= r'
            // r' - l' < r - l
        }
        //l < r
        // r' - l' <= 1
        //(∀i, arr.length - 1 >= i >= r': arr[i] >= arr[i + 1]) && (l' - 1 >= i >= 0: arr[i] >= arr[i + 1]))
        // arr[r] >= arr[r - 1] && arr[l - 1] >= arr[l]
        if (arr[r] > arr[l]){
            // arr[r] >= arr[r - 1] && arr[l - 1] >= arr[l] && arr[r] > arr[l]
            // -> k = r
            return r;
        } else {
            // arr[l - 1] >= arr[l] >= arr[r] >= arr[r - 1]
            // -> k = 0
            return 0;
        }
    }
    public static void main(String[] args) {
        // Pre: args.length > 0 && (∀i, k - 2 >= i >= 0: args[i] >= args[i + 1])
        //      && (∀i, args.length >= i >= k: args[i] >= args[i + 1])
        // Post: k
        int[] arr = new int[args.length];
        int i = 0;
        //i = 0
        while (i < arr.length){
            arr[i] = Integer.parseInt(args[i]);
            i++;
            //i' = i + 1
        }
        //i = arr.length && (∀i, args.length > i >= 0: arr[i] = args[i])
        System.out.println(Shift(arr));
    }
}
