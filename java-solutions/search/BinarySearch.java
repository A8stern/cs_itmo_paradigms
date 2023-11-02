package search;

import java.util.Scanner;

public class BinarySearch {

    // Pred: (∀i'', arr.length - 2 >= i'' >= 0: arr[i''] >= arr[i'' + 1]) && arr[i] - int
    // Post: i':
    //      (arr[i'] <= x && i' > 0 && arr[i' - 1] > x) ||
    //      (i' = len(arr) - 1 && arr[i'] > x) ||
    //      (i' = 0 &&  arr[i'] <= x)
    public static int notRecursive (int[] arr, int x){
        int l = -1;
        //arr[l] > x
        int r = arr.length;
        while (r - 1 > l){
            int mid = l + ((r - l) / 2);
            // r >= mid' >= l
            if (arr[mid] > x){
                //arr[mid'] > x
                //∀i, mid' >= i >= 0: arr[i] > x
                //mid' > l' -> arr[l'] > x
                //l' <= mid <= i' <= r'
                l = mid;
                //l < l' <= i' <= r'
                //r' - l' < r' - l
            }
            else{
                //arr[mid'] <= x
                //∀i, mid' <= i <= arr.length - 1: arr[i] <= x
                //mid' < r' -> arr[r'] <= x
                //l' <= i' <= mid <= r'
                r = mid;
                //l' <= i' <= r' < r
                //r' - l' < r - l'
            }
            //l' <= i' <= r'
            //r' - l' < r - l
        }
        // r - l <= 1 && (arr[r'] <= x || (arr[r'] > x && r' = len(arr) - 1)) && (arr[l'] > x)
        return r;
    }

    // Pred: (Vi'', arr.length - 2 >= i'' >= 0: arr[i''] >= arr[i'' + 1])
    //      && arr[i] - int && l <= i' <= r && arr[l] > x
    // Post: i':
    //      (arr[i'] <= x && i' > 0 && arr[i' - 1] > x) ||
    //      (i' = len(arr) - 1 && arr[i'] > x) ||
    //      (i' = 0 &&  arr[i'] <= x)
    public static int Recursive (int[] arr, int x, int l, int r){
        if (r - 1 <= l){
            // r - l <= 1 && (arr[r'] <= x || (arr[r'] > x && r' = len(arr) - 1)) && (arr[l'] > x)
            return r;
        } else {
            int mid = l + ((r - l) / 2);
            // mid' >= l && mid' <= r
            if (arr[mid] > x){
                //arr[mid'] > x
                //∀i, mid' >= i >= 0: arr[i] > x
                //mid' > l' -> arr[l'] > x
                //l' <= mid <= i' <= r'
                return Recursive(arr, x, mid, r);
            }
            else{
                //arr[mid'] <= x
                //∀i, mid' <= i <= arr.length - 1: arr[i] <= x
                //mid' < r' -> arr[r'] <= x
                //l' <= i' <= mid <= r'
                return Recursive(arr, x, l, mid);
            }
        }
    }

    // Pre: args.length > 0 && (∀i, args.length - 2 >= i >= 1: args[i] >= args[i + 1])
    // Post: arr[i'] <= x
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] arr = new int[args.length - 1];
        int i = 0;
        int sum = 0;
        // i = 0
        while (i < arr.length){
            arr[i] = Integer.parseInt(args[i + 1]);
            sum += arr[i];
            i++;
            //i' = i + 1
            //sum' = sum + arr[i]
        }
        // i' = arr.length && (∀i'', arr.length - 2 >= i'' >= 0: arr[i''] >= arr[i'' + 1])
        //      && (∀i''', arr.length > i''' >= 0: arr[i] = int(args[i + 1])
        //(sum % 2 == 0 -> BinSearch - recursive) || (sum % 2 == 1 -> BinSearch - iterative)
        if (sum % 2 == 0){
            System.out.println(Recursive(arr, x, -1, arr.length));
        } else{
            System.out.println(notRecursive(arr, x));
        }
    }
}
