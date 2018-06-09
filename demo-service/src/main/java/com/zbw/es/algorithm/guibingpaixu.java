package com.zbw.es.algorithm;

/**
 * 归并排序
 */
public class guibingpaixu {

    //临时数组
    private static int temp[];

    private static void sort(int a[],int low,int high){
        if(low >= high) return;

        int mid = low + (high - low)/2;
        //左边
        sort(a,low,mid);
        //右边
        sort(a,mid+1,high);
        // 改进
        if(a[mid]<a[mid+1]) return;
        merge(a,low,mid,high);
    }


    /**
     * 单趟
     * a[low...high] 是待排序序列,其中a[low...mid]和 a[mid+1...high]已有序
     */
    private static void merge(int a[],int low,int mid,int high){
        int i = low;
        int j = mid+1;
        for(int k = low ; k<=high;k++){
            temp[k] = a[k];
        }
        //将两部分数组排序并合并
        for(int k = low;k<=high;k++){
            if(i>mid){
                //左边已用完
                a[k] = temp[j++];
            }else if(j>high){
                //右边已用完
                a[k] = temp[i++];
            }else if(temp[i]>temp[j]){
                // 右半边当前元素小于左半边当前元素， 取右半边元素
                a[k] = temp[j++];
            }else{
                // 右半边当前元素大于等于左半边当前元素，取左半边元素
                a[k] = temp[i++];
            }
        }
    }

    public static void main(String[] args) {
        int [] a = {1,6,3,2,9,7,8,1,5,0};
        temp = new int[a.length];
        sort(a,0,a.length-1);
        for(int i=0;i<a.length;i++){ System.out.println(a[i]); }
    }

}
