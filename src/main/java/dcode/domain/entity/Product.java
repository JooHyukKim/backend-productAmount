package dcode.domain.entity;

import dcode.config.PromotionProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static dcode.config.PromotionProperties.MIN_FINAL_DISCOUNTED_PRICE;
import static dcode.config.PromotionProperties.MIN_PRICE_UNIT;

@Data
public class Product {
  private int id;
  private String name;
  private int price;
  private final int originalPrice;
  private int discountedAmount;

  private Product() {
    throw new RuntimeException("");
  }

  @Builder
  public Product(int id, String name, int price, int discountedAmount) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.originalPrice = price;
    this.discountedAmount = discountedAmount;
  }

  public void applyPromotions(List<Promotion> promotions) {
    int totalDiscountAmount = 0;
    for (Promotion promotion : promotions) {
      totalDiscountAmount += promotion.getDiscountedAmount(price);
    }
    price = Math.max(MIN_FINAL_DISCOUNTED_PRICE, price - totalDiscountAmount);
    price = (price / MIN_PRICE_UNIT) * MIN_PRICE_UNIT;
  }

  public int getDiscountedAmountInWon() {
    return originalPrice - price;
  }

  public int getDiscountedPrice() {
    return price;
  }

  public int getOriginalPrice() {
    return originalPrice;
  }
}
