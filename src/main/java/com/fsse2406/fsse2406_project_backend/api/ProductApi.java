package com.fsse2406.fsse2406_project_backend.api;

import com.fsse2406.fsse2406_project_backend.config.EnvConfig;
import com.fsse2406.fsse2406_project_backend.objects.product.data.response.ProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.product.dto.response.GetAllProductResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.product.dto.response.ProductResponseDto;
import com.fsse2406.fsse2406_project_backend.service.ProductService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin({EnvConfig.DEV_BSE_URL, EnvConfig.PROD_BSE_URL})
@RequestMapping("/public/product")
public class ProductApi {
ProductService productService;
public ProductApi(ProductService productService){
    this.productService=productService;
}

@GetMapping
    public List<GetAllProductResponseDto> getAllProduct (){
    List<GetAllProductResponseDto> getAllProductResponseDtoList=new ArrayList<>();
    for(ProductResponseData data: productService.getAllProduct()){
        getAllProductResponseDtoList.add(new GetAllProductResponseDto(data));
    }
    return getAllProductResponseDtoList;
}

@GetMapping("/{pid}")
    public ProductResponseDto getProductById(@PathVariable Integer pid){
    return new ProductResponseDto(productService.getProductById(pid));
}
}

