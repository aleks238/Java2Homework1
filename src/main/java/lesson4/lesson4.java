package lesson4;

import java.util.Arrays;

public class lesson4 {
    private static final int size = 10000000;
    private static long starting;
    private static long ending;

    public static void main(String[] args) throws InterruptedException {
        createArray1();
        createArray2();
    }
    static void createArray1() {
        float[] array1 = new float[size];
        Arrays.fill(array1, 1);
        starting= System.currentTimeMillis();
        for (int i = 0; i < array1.length; i++) {
            array1[i] = (float) (array1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        ending=System.currentTimeMillis();
        System.out.println("Время одного потока: "+ (ending-starting) + " ms");
    }

    static void createArray2() throws InterruptedException {
        float[] array2 = new float[size];
        Arrays.fill(array2, 1);
        starting = System.currentTimeMillis();
        float[] array2Left = new float[size/2];
        System.arraycopy(array2, 0, array2Left,0, size/2);
        float[] array2Right = new float[size/2];
        System.arraycopy(array2, size/2, array2Right,0, size/2);
        ending =System.currentTimeMillis();
        System.out.println("Время разделить массив на 2 части: " + (ending - starting)+ " ms");

        Thread thread1 = new Thread(()->{
            starting = System.currentTimeMillis();
                halfArrayInputValue(array2Left);
            ending =System.currentTimeMillis();
            System.out.println("Время первого потока: " + (ending - starting)+ " ms");

        });
        Thread thread2 = new Thread(()->{
            starting = System.currentTimeMillis();
                halfArrayInputValue(array2Right);
            ending =System.currentTimeMillis();
            System.out.println("Время второго потока: " + (ending - starting)+ " ms");
        });

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();

        arrayCombine(array2,array2Left,array2Right);
    }

    static void halfArrayInputValue(float[] halfArray){
        for (int i = 0; i < halfArray.length; i++) {
            halfArray[i] = (float) (halfArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
    static void arrayCombine( float[] array2, float[] array2Half1,float[] array2Half2){
        starting = System.currentTimeMillis();
        System.arraycopy(array2Half1, 0, array2,0, array2Half1.length);
        System.arraycopy(array2Half2, 0, array2,size/2, array2Half2.length);
        ending =System.currentTimeMillis();
        System.out.println("Время на соединение : " + (ending - starting)+ " ms");
    }
}
