package com.fsse2406.fsse2406_project_backend.objects.checkout;

import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.dto.TransactionProductRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionForStripeBody {
    private List<TransactionProductRequestDto> transactionProducts;
}
