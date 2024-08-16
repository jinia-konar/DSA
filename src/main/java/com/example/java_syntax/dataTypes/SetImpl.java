package com.example.java_syntax.dataTypes;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
public class SetImpl implements DataTypeHandler{
  Set<Integer> hashSet = new HashSet<>();
  Set<Integer> linkedHashSet = new LinkedHashSet<>();
  Set<Integer> treeSet = new TreeSet<>();
  Set<Integer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
  Set<Integer> concurrentSkipListSet = new ConcurrentSkipListSet<>();
  //ConcurrentHashSet does not belong Java -> it is the combination of HashSet and ConcurrentHashMap

  private void checkNullable() {
    addInitialAndNullValues(hashSet);
    log.info("hashSet size {}", hashSet.size());

    try {
      addInitialAndNullValues(treeSet);
      log.info("treeSet size {}", treeSet.size());
    } catch (NullPointerException e) {
      log.error("can not add null to TreeSet");
    }

    try {
      addInitialAndNullValues(concurrentSkipListSet);
      log.info("concurrentSkipListSet size {}", concurrentSkipListSet.size());
    } catch (NullPointerException e) {
      log.error("can not add null to concurrentSkipListSet");
    }
  }

  private void addInitialAndNullValues(Set<Integer> set) {
    set.add(1);
    set.add(null);
    //set.add(null);
  }


  @Override
  public void handleImplementations() {
    checkNullable();
  }
}
