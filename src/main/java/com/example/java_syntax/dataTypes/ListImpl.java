package com.example.java_syntax.dataTypes;

import com.example.java_syntax.CollectionDataTypes;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class ListImpl implements DataTypeHandler{
  List<Integer> arrayList = new ArrayList<>();
  List<Integer> vectorList = new Vector<>();
  List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

  //LinkedList and CopyOnWriteArrayList also use list

  private void threadSafe() {
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

    //Thread safe with copyOnWriteArrayList
    Runnable copyOnWriteArrayListTask = () -> {
      for (int i = 0; i < 1000; i++) {
        copyOnWriteArrayList.add(i);
      }
    };

    for(int i=0;i<10;i++) {
      executeThreads(arrayTask, arrayList, CollectionDataTypes.List);
      executeThreads(vectorTask, vectorList, CollectionDataTypes.Vector);
      executeThreads(copyOnWriteArrayListTask, copyOnWriteArrayList, CollectionDataTypes.CopyOnWriteArrayList);
      log.info("");
      arrayList.clear();
      vectorList.clear();
      copyOnWriteArrayList.clear();
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
    log.info("Final size of {}: {} and address {}", dataType.name(), list.size(), System.identityHashCode(list));
  }

  private void addressModificationForCopyWriteOnArrayList() {
    copyOnWriteArrayList.add(1);
    copyOnWriteArrayList.add(2);
    Object[] arrayBefore = copyOnWriteArrayList.toArray();
    log.info("Address of internal array and list object before adding: {}, {}",
            System.identityHashCode(arrayBefore), System.identityHashCode(copyOnWriteArrayList));
    copyOnWriteArrayList.add(3);
    copyOnWriteArrayList.add(4);
    Object[] arrayAfter = copyOnWriteArrayList.toArray();
    log.info("Address of internal array and list object after adding: {}, {}",
            System.identityHashCode(arrayAfter), System.identityHashCode(copyOnWriteArrayList));
    //The identity of the list doesn't change as it refer to the same address as before
    //Address of the internal array changes because with every write a new internal array is created
  }

  @Override
  public void handleImplementations() {
    threadSafe();
    addressModificationForCopyWriteOnArrayList();
  }
}
