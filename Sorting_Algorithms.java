
import java.util.Arrays;

// สอบ group Sorting Algorithms:
// Selection Sort
// Bubble Sort
// Insertion Sort
// Merge Sort
// Quick Sort
// Heap Sort
// Radix Sort
// ShellSort
// ใช้วิธีจับสลาก สอบ 1 หัวข้อ คะแนน 10 คะแนน
// สอบ วันที่ 17 ตค 67 เวลา 13:00-17:00  น
// เงื่อนไข สมาชิกในกลุ่มขาด 1 ตัดทิ้งนศที่ขาด  2. ได้คะแนน 0 ทั้งกลุ่ม
// คำถามที่จะถาม
// 1. program
// 2. Process +algorithm ของ sorting
// 3. Big O
public class Sorting_Algorithms {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // สลับค่าถ้าค่าในตำแหน่งปัจจุบันมากกว่าค่าถัดไป
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // หาตำแหน่งของค่าน้อยที่สุดในอาเรย์ที่ยังไม่ได้เรียงลำดับ
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // สลับค่าระหว่างตำแหน่งเริ่มต้นกับตำแหน่งของค่าน้อยที่สุด
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i - 1;

            // เลื่อนตำแหน่งของตัวเลขที่มากกว่า temp ไปทางขวา
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            // แทรก temp ในตำแหน่งที่เหมาะสม
            arr[j + 1] = temp;
        }
    }

    //QuickSort
    // ฟังก์ชันสำหรับจัดเรียงข้อมูลในแต่ละส่วน
    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];  // เลือกค่าสุดท้ายเป็น pivot
        int i = (start - 1);  // ดัชนีของค่าน้อยกว่า pivot

        for (int j = start; j < end; j++) {
            // ถ้าค่าปัจจุบันน้อยกว่าหรือเท่ากับ pivot
            if (arr[j] <= pivot) {
                i++;
                // สลับตำแหน่ง arr[i] และ arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // สลับตำแหน่ง pivot กับ arr[i+1]
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;  // คืนค่าตำแหน่งของ pivot
    }

    // ฟังก์ชัน Quick Sort หลัก
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pi = partition(arr, start, end);  // หาตำแหน่ง pivot

            // เรียก Quick Sort กับส่วนซ้ายและขวาของ pivot
            quickSort(arr, start, pi - 1);
            quickSort(arr, pi + 1, end);
        }
    }

    // ######################
    // MergeSort
    public static void mergeSort(int[] array) {

        int length = array.length;
        if (length <= 1) {
            return; //base case
        }
        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0; //left array
        int j = 0; //right array

        for (; i < length; i++) {
            if (i < middle) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    private static void merge(int[] leftArray, int[] rightArray, int[] array) {

        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0; //indices

        //check the conditions for merging
        while (l < leftSize && r < rightSize) {
            if (leftArray[l] < rightArray[r]) {
                array[i] = leftArray[l];
                i++;
                l++;
            } else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while (l < leftSize) {
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while (r < rightSize) {
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }
    // ###################

    // Heap Sort
    // ฟังก์ชันสำหรับจัดการ heap 
    public static void heapify(int[] arr, int n, int i) {
        int largest = i; // กำหนดโหนด i เป็นโหนดที่ใหญ่ที่สุด
        int left = 2 * i + 1; // โหนดซ้าย
        int right = 2 * i + 2; // โหนดขวา

        // ตรวจสอบว่าลูกซ้ายมีค่ามากกว่าโหนดหลักหรือไม่
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // ตรวจสอบว่าลูกขวามีค่ามากกว่าโหนดหลักหรือไม่
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // ถ้าโหนดที่ใหญ่ที่สุดไม่ใช่โหนดหลัก ให้สลับค่าและเรียก heapify ซ้ำ
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // เรียก heapify ซ้ำกับโหนดที่มีการเปลี่ยนแปลง
            heapify(arr, n, largest);
        }
    }

    // ฟังก์ชันหลักของ Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // สร้าง max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // ดึงค่ามากที่สุดออกจาก heap ทีละตัว และปรับ heap ใหม่
        for (int i = n - 1; i > 0; i--) {
            // สลับโหนดรากกับโหนดสุดท้าย
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // เรียก heapify กับ heap ที่เหลือ
            heapify(arr, i, 0);
        }
    }
    // #####################

    // Radix Sort
    // ฟังก์ชันสำหรับการนับและจัดเรียงข้อมูลในแต่ละหลัก
    public static void countingSort(int[] arr, int place) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // นับจำนวนครั้งของแต่ละตัวเลขในหลักที่กำหนด
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / place) % 10;
            count[digit]++;
        }

        // เปลี่ยนแปลง count เพื่อสะสมค่าดัชนี
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // สร้างอาเรย์ที่เรียงลำดับตามหลักที่กำหนด
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / place) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // คัดลอกข้อมูลจากอาเรย์ output กลับไปยังอาเรย์ต้นฉบับ
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // ฟังก์ชันหลักของ Radix Sort
    public static void radixSort(int[] arr) {
        // หาค่ามากที่สุดในอาเรย์เพื่อทราบจำนวนหลักสูงสุด
        int max = Arrays.stream(arr).max().getAsInt();

        // ทำการเรียงลำดับตามแต่ละหลักโดยใช้ Counting Sort
        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(arr, place);
        }
    }

    // ##################
    // ฟังก์ชันหลักของ Shell Sort
    public static void shellSort(int[] arr) {
        int n = arr.length;

        // เริ่มต้นด้วยระยะห่าง (gap) ที่ใหญ่และลดลงครึ่งหนึ่งในแต่ละรอบ
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // ใช้ Insertion Sort กับแต่ละกลุ่มที่ถูกแบ่งตามระยะห่าง
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;

                // สลับตำแหน่งของตัวเลขที่อยู่ห่างกันด้วย gap
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 6, 2, 4};
        System.out.println("ก่อนการเรียงลำดับ:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // quickSort(arr, 0, arr.length - 1);
        // mergeSort(arr);
        // heapSort(arr);
        // radixSort(arr);
        shellSort(arr);
        System.out.println("\nหลังการเรียงลำดับ:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
