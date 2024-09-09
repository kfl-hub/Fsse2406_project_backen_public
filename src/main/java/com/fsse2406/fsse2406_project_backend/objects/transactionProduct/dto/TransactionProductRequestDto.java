package com.fsse2406.fsse2406_project_backend.objects.transactionProduct.dto;

import com.fsse2406.fsse2406_project_backend.objects.product.dto.response.ProductResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.domainObject.TransactionProductResponseData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
public class TransactionProductRequestDto {


    private Integer tpid;

    private ProductResponseDto product;

    private Integer quantity;

    private BigDecimal subtotal;

    private String size;

    public TransactionProductRequestDto(TransactionProductResponseData data) {
        this.tpid = data.getTpid();
        this.product = new ProductResponseDto(data.getProduct());
        this.quantity = data.getQuantity();
        this.subtotal = this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
        this.size = data.getSize();
    }

}
