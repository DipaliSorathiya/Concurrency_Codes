package org.example.ThreadCreation.Runnable_Interface_02;

public class MyRunnable implements   Runnable{
    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println("Runnable " +Thread.currentThread().getId() + " is running: "+i);
            try {
                // pause execution for 500 milliseconds.
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                System.out.println("Thread interruped!!");
            }
        }
    }
}
