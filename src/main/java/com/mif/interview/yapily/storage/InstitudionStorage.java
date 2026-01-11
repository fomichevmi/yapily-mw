package com.mif.interview.yapily.storage;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.mif.interview.yapily.model.Transaction;

import jakarta.annotation.PostConstruct;

@Repository
public class InstitudionStorage {

  private final Set<String> institutions = ConcurrentHashMap.newKeySet();

  @PostConstruct
  void init() {
    institutions.add("unicaja-sandbox");
    institutions.add("santander-sandbox");
    institutions.add("bbva-sandbox");
    institutions.add("modelo-sandbox");
  }

  public String getInstitutionForTransaction(Transaction transaction) {
    int size = institutions.size();
    int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
    int i = 0;
    for (var obj : institutions) {
      if (i == item)
        return obj;
      i++;
    }
    return null;
  }
}
