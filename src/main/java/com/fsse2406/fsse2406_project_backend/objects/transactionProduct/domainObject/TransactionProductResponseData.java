package com.fsse2406.fsse2406_project_backend.objects.transactionProduct.domainObject;

import com.fsse2406.fsse2406_project_backend.objects.product.data.response.ProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity.TransactionProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
public class TransactionProductResponseData {

    private Integer tpid;

    private Integer tid;

    private ProductResponseData product;

    private Integer quantity;

    private BigDecimal subtotal;

    private String size;

    public TransactionProductResponseData(TransactionProductEntity entity) {
        this.tpid = entity.getTpid();
        this.tid=entity.getTransaction().getTid();
        this.product = new ProductResponseData(
                entity.getPid(), entity.getName(), entity.getDescription(),
                entity.getImageUrl(), entity.getPrice(), entity.getStock());
        this.quantity = entity.getQuantity();
        this.subtotal = this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
        this.size = entity.getSize();
    }
}
