package com.zbw.es.algorithm;

import java.util.Arrays;

/**
 * Created by zbww on 2018/6/7.
 *
 *  快速排序
 *
 1.选定一个合适的值（理想情况中值最好，但实现中一般使用数组第一个值）,称为“枢轴”(pivot)。

 2.基于这个值，将数组分为两部分，较小的分在左边，较大的分在右边。

 3.可以肯定，如此一轮下来，这个枢轴的位置一定在最终位置上。

 4.对两个子数组分别重复上述过程，直到每个数组只有一个元素。

 5.排序完成。
 *
 */
public class quickSort {

    public static int partition(int[] arr,int low,int high){
        int pivot = arr[low];     //枢轴记录
        while (low<high){
            while (low<high && arr[high]>=pivot) --high;
            arr[low]=arr[high];             //交换比枢轴小的记录到左端
            while (low<high && arr[low]<=pivot) ++low;
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }


    public static void quickSort(int[] arr){
        qsort(arr, 0, arr.length-1);
    }
    private static void qsort(int[] arr, int low, int high){
        if (low < high){
            int pivot=partition(arr, low, high);        //将数组分为两部分
            qsort(arr, low, pivot-1);                   //递归排序左子数组
            qsort(arr, pivot+1, high);                  //递归排序右子数组
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,6,3,12};

        quickSort(arr);
        for(int i : arr){
            System.out.println(i);
        }

    }



}
