package dcode.service;

import dcode.domain.entity.Product;
import dcode.domain.entity.Promotion;
import dcode.exception.NoSuchProductException;
import dcode.model.request.ProductInfoRequest;
import dcode.model.response.ProductAmountResponse;
import dcode.repository.ProductRepository;
import dcode.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

  public static final Validator validator =
    Validation.buildDefaultValidatorFactory().getValidator();

  private final ProductRepository productRepository;

  private final PromotionRepository promotionRepository;

  public ProductAmountResponse getProductAmount(ProductInfoRequest request) {
    request.isValidated();
    Product product = findProduct(request);
    List<Promotion> promotions = findPromotionsOf(product, request.getPromotionIds());
    product.applyPromotions(promotions);
    return ProductAmountResponse.from(product);
  }

  private Product findProduct(ProductInfoRequest request) {
    return productRepository.getProduct(request.getProductId()).orElseThrow(NoSuchProductException::new);
  }

  private List<Promotion> findPromotionsOf(Product product, int[] promotionIds) {
    return promotionRepository.getPromotionsByProductIdAndPromotionId(product.getId(), promotionIds);
  }
}
