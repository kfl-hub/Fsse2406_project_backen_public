package com.fsse2406.fsse2406_project_backend.api;

import com.fsse2406.fsse2406_project_backend.config.EnvConfig;
import com.fsse2406.fsse2406_project_backend.objects.transaction.dto.SuccessTransactionResponseDto;
import com.fsse2406.fsse2406_project_backend.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin({EnvConfig.DEV_BSE_URL, EnvConfig.PROD_BSE_URL})
@RequestMapping("/public")
public class TesttApi {
ProductService productService;
public TesttApi(ProductService productService){
    this.productService=productService;
}

@GetMapping("/apitest")
    public SuccessTransactionResponseDto aptTest (){
    return new SuccessTransactionResponseDto();
}}

