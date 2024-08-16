package com.example.java_syntax.dataTypes;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

@Slf4j
public class MapImpl implements DataTypeHandler{
  Map<Integer, Integer> hashMap = new HashMap<>();
  Map<Integer, Integer> hashTable = new Hashtable<>();
  Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
  Map<Integer, Integer> treeMap = new TreeMap<>();

  //concurrency handler
  Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
  Map<Integer, Integer> concurrentSkipListMap = new ConcurrentSkipListMap<>();

  private void checkKeyNullable() {
    hashMap.put(1, 2);
    hashMap.put(null, 1);
    hashMap.put(null, 3);
    log.info("Value at null in hashMap {}", hashMap.get(null));
    //same as linkedHashMap

    hashTable.put(1, 2);
    try {
      hashTable.put(null, 1);
    } catch (NullPointerException e) {
      log.error("Can not put null as key in hashTable");
    }

    concurrentSkipListMap.put(1, 2);
    try {
      concurrentSkipListMap.put(null, 1);
    } catch (NullPointerException e) {
      log.error("Can not put null as key in concurrentSkipListMap");
    }
    //same as treeMap, concurrentHashMap
  }

  private void checkValueNullable() {
    linkedHashMap.put(null, null);
    log.info("Value at null in linkedHashMap {}", linkedHashMap.get(null));

    treeMap.put(1, null);
    log.info("Value at int key in treeMap {}", treeMap.get(1));

    try {
      concurrentHashMap.put(null, 1);
    } catch (NullPointerException e) {
      log.error("Can not put null as key in concurrentHashMap");
    }

    try {
      concurrentHashMap.put(1, null);
    } catch (NullPointerException e) {
      log.error("Can not put null as value in concurrentHashMap");
    }
  }

  @Override
  public void handleImplementations() {
    checkKeyNullable();
    checkValueNullable();
  }
}
