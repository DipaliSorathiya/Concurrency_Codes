package org.example.ConcurrentCollection.ConcurrentHashMap;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapExample {

    public static void main(String[] args) throws InterruptedException {

        // create a non thread safe HashMap wrapped as a synchronized map.
        // even though Collections : synchronizedMap provides basic thread safety for individual
        // operations, you still need to manually synchronize when iterating over it.
        final Map<Integer,String> hashMap = Collections.synchronizedMap(new HashMap<>());

        // create a concurrentHashMap which is thread safe and desined for concurrent access.
        final ConcurrentHashMap<Integer,String> concurrentMap = new ConcurrentHashMap<>();

        // ------- Ex -1 : Using HashMap with manual locking
        Thread hashMapUpdater = new Thread(() ->{
            for(int i=1;i<=5;i++){
                hashMap.put(i,"Value"+i);
                try {
                    Thread.sleep(50);
                }
                catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread hashMapIterator = new Thread(() -> {
            try {
                Thread.sleep(25);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }

            // when iterating over a synchronizedMap, you must lock the map manually.
            synchronized (hashMap){
                for(Map.Entry<Integer,String> entry:hashMap.entrySet()){
                    System.out.println("hashMap Iteration - Key: "+entry.getKey() + ", Value: " + entry.getValue());
                }
            }
        });

        // start both threads and wait for them to finish.
        hashMapUpdater.start();
        hashMapIterator.start();
        hashMapUpdater.join();
        hashMapIterator.join();
        System.out.println("Final hashMap: "+ hashMap);


        // ------Ex :2 Using ConcurrentHashMap ---------------

        Thread concurrentMapUpdater = new Thread(() ->{
           for(int i=1;i<=5;i++){
               concurrentMap.put(i,"Value" +i);
               try{
                   Thread.sleep(50);
               }
               catch (InterruptedException e){
                   Thread.currentThread().interrupt();
               }
           }
        });

        Thread concurrentMapIterator = new Thread(() -> {
            try{
                Thread.sleep(25);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }

            // with concurrentHashMap, iteration is safe without any external synchronization.
            for(Map.Entry<Integer,String> entry:concurrentMap.entrySet()){
                System.out.println("concurrent Iteration- Key: "+ entry.getKey() + ", Value: " +entry.getValue());
            }
        });

        // start both threads and wait for them to finish.
        concurrentMapUpdater.start();
        concurrentMapIterator.start();
        concurrentMapUpdater.join();
        concurrentMapIterator.join();
        System.out.println("Final concurrentMap: "+concurrentMap);


    };
}
