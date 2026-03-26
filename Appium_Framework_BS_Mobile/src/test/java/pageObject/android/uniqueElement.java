package pageObject.android;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class uniqueElement {
    public static void sort(int[] a){

        Arrays.sort(a);
        int val = a[0];
        System.out.print(val + " ");

        int valmax =a[a.length-1];

        for(int i=1; i<a.length-1; i++){
            if(a[i] !=a[i-1] && a[i+1] != a[i]){
                System.out.print(a[i]+ " ");
            }
        }
        System.out.print(valmax);




//        HashSet<Integer> hs = new HashSet<>();
//
//        for(int i=0; i<a.length; i++){
//
//            hs.add(a[i]);
//
//
//        }
//        int hsize = hs.size();
//        int[] ans= new int[hs];
//        for(int i=0; i<hsize; i++){
//        }


    }
    public static void main(String[] args) {

        int[] lsit = new int[4];
        lsit= new int[]{1, 1, 2, 3};
        sort(lsit);


    }
}
