package org.example.ThreadCreation.Thread_Creation_01;

public class MyThread extends  Thread {

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println("Thread " +Thread.currentThread().getId() + " is running " +i);
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                System.out.println("Thread interrupted!!");
            }
        }
    }

}
