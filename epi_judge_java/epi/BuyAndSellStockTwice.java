package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    // TODO - you fill in here.
    //10 12 8 16 6 18
    //0 2 2 8 8 12
    //12 12 12 12 12 0
    List<Double> maxFront = new ArrayList<>(prices.size());
    double maxProfit = 0;
    double min = Double.MAX_VALUE;
    for (double price : prices) {
      min = Math.min(price, min);
      maxProfit = Math.max(price - min, maxProfit);
      maxFront.add(maxProfit);
    }
    maxProfit = 0;
    double max = Double.MIN_VALUE;
    List<Double> maxBack = new ArrayList<>(prices.size());
    for (int i = prices.size() - 1; i >= 0; i--) {
      double price = prices.get(i);
      max = Math.max(price, max);
      maxProfit = Math.max(max - price, maxProfit);
      maxBack.add(0, maxProfit);
    }
    maxProfit = 0;
    for (int i = 0; i < prices.size(); i++) {
      if (i == 0) {
        maxProfit = Math.max(maxBack.get(i), maxProfit);
      }
      else
          maxProfit = Math.max(maxFront.get(i-1) + maxBack.get(i), maxProfit);
    }

    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
