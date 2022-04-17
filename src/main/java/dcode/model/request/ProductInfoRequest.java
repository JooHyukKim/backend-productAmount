package dcode.model.request;

import dcode.exception.InvalidRequestPropertyException;
import dcode.service.ProductService;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class ProductInfoRequest {

    @Positive
    private Integer productId;

    @NotNull
    private int[] promotionIds;

  public void isValidated() {
    var violiations = ProductService.validator.validate(this);
    if (violiations.size() > 0) throw new InvalidRequestPropertyException(violiations.toString());
  }
}
