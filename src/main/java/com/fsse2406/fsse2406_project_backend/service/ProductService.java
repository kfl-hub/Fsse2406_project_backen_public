package com.fsse2406.fsse2406_project_backend.service;


import com.fsse2406.fsse2406_project_backend.objects.product.data.response.ProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductResponseData> getAllProduct();

    ProductResponseData getProductById(Integer pid);

    ProductEntity getProductEntityById(Integer pid);

    boolean adjustProductQuantity(Integer pid, Integer quantity);
}
