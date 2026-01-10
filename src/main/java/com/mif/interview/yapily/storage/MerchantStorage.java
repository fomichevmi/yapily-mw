package com.mif.interview.yapily.storage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.mif.interview.yapily.exception.UnknownMerchantException;
import com.mif.interview.yapily.model.Merchant;

import jakarta.validation.constraints.NotNull;

/**
 * in real life there should be a database behind it, using map for demo
 * purposes only
 */
@Repository
public class MerchantStorage {

  final private ConcurrentMap<Integer, String> merchants;

  public MerchantStorage() {
    this.merchants = new ConcurrentHashMap<>();
    fillMerchants();
  }

  private void fillMerchants() {
    merchants.put(3005, "British Airways");
    merchants.put(3006, "Japan Air Lines");
    merchants.put(3007, "Air France");
    merchants.put(3008, "Lufthansa");
    merchants.put(3082, "Korean Airline");
    merchants.put(3102, "Iberia");
  }

  public Merchant getMerchantByCode(@NotNull Integer merchantId) {
    var merchantName = merchants.get(merchantId);
    if (merchantName == null) {
      throw new UnknownMerchantException(merchantId);
    }
    return new Merchant(merchantId, merchantName);
  }
}
