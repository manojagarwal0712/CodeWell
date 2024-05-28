package com.interview.misc;

public class SortingsQuestions {

    /**
     * In Bubble sort, Each element of the array is compared with its adjacent element. The algorithm processes the list in passes. A list with n elements requires n-1 passes for sorting. Consider an array A of n elements whose elements are to be sorted by using Bubble sort. The algorithm processes like following.
     *
     * In Pass 1, A[0] is compared with A[1], A[1] is compared with A[2], A[2] is compared with A[3] and so on. At the end of pass 1, the largest element of the list is placed at the highest index of the list.
     * In Pass 2, A[0] is compared with A[1], A[1] is compared with A[2] and so on. At the end of Pass 2 the second largest element of the list is placed at the second highest index of the list.
     * In pass n-1, A[0] is compared with A[1], A[1] is compared with A[2] and so on. At the end of this pass. The smallest element of the list is placed at the first index of the list.
     *
     * O(n^2)
     */
    public static void bubbleSort(int []arr){
        for (int i =0; i<arr.length;i++){
            for (int j =i+1; j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int a: arr) {
            System.out.println(a);
        }
    }


    /**
     * https://www.geeksforgeeks.org/heap-sort/
     *
     * @param arr
     */
    public static void heapSort(int arr[]){
        int length = arr.length;
        for (int i = length/2-1;i>=0;i--){
            heapify(arr, length,i);
        }

        for (int i = length-1;i>=0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int arr[], int arrSize, int root){
        int largest = root; // parent node index position
        int left = 2*root+1; // left child index pos
        int right = 2*root+2; // right child index pos

        if(left < arrSize && arr[left]>arr[largest]){
            largest = left;
        }

        if(right < arrSize && arr[right]>arr[largest]){
            largest = right;
        }
        if(largest !=root){
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;
            heapify(arr, arrSize , largest);
        }

    }


    /**
     *  arr [] = 4, 10, 3, 5, 1
     *
     * @param arr
     */

    public static void heapSort1(int arr[]){

        int n = arr.length;
        for (int i=n/2-1; i>=0;i--){
            heapfyTree(arr, n, i);
        }
        for (int i=n-1;i>=0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public static void heapfyTree(int arr[], int n, int i){
        int largest = i; // lets consider this is root;
        int left = 2*1+1;
        int right = 2*i +2;

        if(left < n && arr[left] > arr[largest])
            largest = left;

        if(right < n && arr[right]> arr[largest])
            largest = right;

        if(largest!=i){
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            heapfyTree(arr, n-1, largest);
        }


    }

    /**
     *
     * @param arr
     */
    public static void selectionSort(int []arr){

        for (int i =0; i<arr.length-1;i++){
            int min =i; // consider intially the first element is min and in the next loop
            //find if there is any other element with min , if yes min will have that index
            // and after the loop we will swap it with i which would be 0 in first loop and then 1 and so on.
            // Its basically select the min and place it in the initial on array
            for (int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min]= temp;
        }

        for (int i =0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

    }

    public static void insertionSort(int []arr){
        for (int i =0;i<arr.length;i++){
            int val = arr[i];
            int pos = i;
            while (pos>0 && arr[pos-1]>val){
                arr[pos] = arr[pos-1];
                pos =pos-1;
            }
            arr[pos] =val;
        }

        for (int i =0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    /**
     * 1)select a piovt (any element in array)
     * 2) arrange the array in such a way that elment left to pivot are lesser and element
     * right to it are greater. (it called partitioning)
     * 3)
     */
    public static void quickSort(int arr[], int start, int end){
        if(start<end){
            int partitionIndex = partition(arr,start,end);
            quickSort(arr,start,partitionIndex);
            quickSort(arr,partitionIndex+1,end);
        }
    }

    public static int partition(int arr[], int start, int end){
        int pivot = arr[end];
        int pindx = start;

        for (int i =start; i<end-1;i++){
            if(arr[i]<=pivot){
                int temp = arr[i];
                arr[i] = arr[pindx];
                arr[pindx] = temp;
                pindx++;
            }
        }
        int temp = arr[pindx];
        arr[pindx] = arr[end];
        arr[end] = temp;

        return pindx;
    }

    public static void heapify(int arr[],int i){
        int left = 2*i;
        int right = 2*i+1;
        int largest = 0;

        if(left<=arr.length-1 && arr[i]<arr[left]){
            largest = left;
        }else
            largest =right;

        if(right<=arr.length-1 && arr[right]>arr[largest]){
            largest=right;
        }

        if(largest!=i){
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i]=temp;
        }
        heapify(arr,largest);
    }

  public static void merge(int arr[], int p, int q, int r){
        int n1 = q-p+1;
        int n2 = r-q;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i =0;i<n1;i++){
            L[i] = arr[p+i];
        }

      for (int i =0;i<n2;i++){
          R[i] = arr[q+1+i];
      }

      int i =0,j=0;
      int k =0;
      while (i<n1 && j<n2){
          if (L[i]<=R[j]){
              arr[k] = L[i];
              k++;
              i++;
          }else {
              arr[k] = R[j];
              j++;
              k++;
          }
      }

      while (i<n1){
          arr[k]=arr[i];
          i++;
          k++;
      }
      while (j<n2){
          arr[k]=arr[j];
          j++;
          k++;
      }
  }

  public static void mergeSOrt(int arr[], int p, int r){
        int q = (p+r)/2;
      mergeSOrt(arr,p,q);
      mergeSOrt(arr,q+1,r);
      merge(arr,p,q,r);
  }



    public static void main(String[] args) {
        int arr[] = {1,14,10,8,7,9,3,2,4,6};
        //bubbleSort(arr);
        insertionSort(arr);
        //heapify(arr,0);
        /*for (Integer a:
             arr) {
            System.out.println(a);
        }*/

        mergeSOrt(arr,0,arr.length-1);
        for (Integer a:
             arr) {
            System.out.println(a);
        }
    }
}
