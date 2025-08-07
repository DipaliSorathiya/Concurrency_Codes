package org.example.ThreadCreation.Runnable_Interface_02;

public class RunnableExample  {

    public static  void main(String[] args){
        MyRunnable runnable = new MyRunnable(); // create runnable instacne.
        Thread thread1 = new Thread(runnable); // create thread with runnable
        Thread thread2 = new Thread(runnable); // create another thread with same runnable.

        thread1.start();
        thread2.start();

    }
}
