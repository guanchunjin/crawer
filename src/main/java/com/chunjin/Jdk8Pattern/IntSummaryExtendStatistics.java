package com.chunjin.Jdk8Pattern;

import java.util.IntSummaryStatistics;
import java.util.function.IntConsumer;

public class IntSummaryExtendStatistics extends IntSummaryStatistics {

  private long variance;

  public IntSummaryExtendStatistics() {

  }




  public final double getVariance() {

    double  avg = getAverage();

    
    return getCount() > 0 ? (double) getSum() / getCount() : 0.0d;
  }

}
