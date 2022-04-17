package dcode.model.response;

import dcode.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAmountResponse {
    private String name; //상품명

    private int originPrice; //상품 기존 가격
    private int discountPrice; //총 할인 금액
    private int finalPrice; //확정 상품 가격

  public static ProductAmountResponse from(Product product) {
    return ProductAmountResponse.builder()
      .name(product.getName())
      .originPrice(product.getOriginalPrice())
      .finalPrice(product.getDiscountedPrice())
      .discountPrice(product.getDiscountedAmountInWon())
      .build();
  }
}
