package com.example.java_syntax;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Slf4j
public class ListImpl {
  List<Integer> arrayList = new ArrayList<>();
  List<Integer> vectorList = new Vector<>();

  //LinkedList and CopyOnWriteArrayList also use list

  public void threadSafe() {
    //Thread safe with array list
    Runnable arrayTask = () -> {
      for (int i = 0; i < 1000; i++) {
        arrayList.add(i);
      }
    };

    //Thread safe with vector
    Runnable vectorTask = () -> {
      for (int i = 0; i < 1000; i++) {
        vectorList.add(i);
      }
    };

    for(int i=0;i<10;i++) {
      executeThreads(arrayTask, arrayList, CollectionDataTypes.List);
      executeThreads(vectorTask, vectorList, CollectionDataTypes.Vector);
      arrayList.clear();
      vectorList.clear();
    }
  }

  private void executeThreads(Runnable task,
                              List<Integer> list,
                              CollectionDataTypes dataType) {
    Thread thread1 = new Thread(task);
    Thread thread2 = new Thread(task);

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      log.error(String.format("failed while modifying %s via different threads", dataType.name()), e);
    }

    // Since Vector is thread-safe, we expect the size to be 2000
    log.info("Final size of {}: {}", dataType.name(), list.size());
  }
}
