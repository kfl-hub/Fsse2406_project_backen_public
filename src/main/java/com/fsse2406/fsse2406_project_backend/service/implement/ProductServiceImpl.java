package com.fsse2406.fsse2406_project_backend.service.implement;

import com.fsse2406.fsse2406_project_backend.exception.ProductServiceException;


import com.fsse2406.fsse2406_project_backend.objects.product.data.response.ProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.product.entity.ProductEntity;
import com.fsse2406.fsse2406_project_backend.service.ProductService;
import com.fsse2406.fsse2406_project_backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private  final  ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }



    @Override
    public List<ProductResponseData> getAllProduct(){
        List<ProductEntity> productEntityList =(List)productRepository.findAll();
        List<ProductResponseData> getAllProductResponseDataList=new ArrayList<>();
        try {
            if (productEntityList.isEmpty()){
                throw new ProductServiceException("Product not found");
            }
            for (ProductEntity product:productEntityList){
                getAllProductResponseDataList.add(new ProductResponseData(product));
            }
            return getAllProductResponseDataList;
        }catch (ProductServiceException exception){
            logger.warn(exception.getMessage());
            throw exception;
        }

    }
    @Override
    public ProductResponseData getProductById(Integer pid){
        try {
            return new ProductResponseData(productRepository.findById(pid).orElseThrow(()->new ProductServiceException("Product not found-pid: "+pid)));
        }catch (ProductServiceException exception){
            logger.warn(exception.getMessage());
            throw exception;
        }
    }
@Override
    public ProductEntity getProductEntityById(Integer pid){
        try {
            return productRepository.findById(pid).orElseThrow(()->new ProductServiceException("Product not found-pid: "+pid));
        }catch (ProductServiceException exception){
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

    @Override
    @Transactional
    public boolean adjustProductQuantity(Integer pid, Integer quantity){
        try {
            ProductEntity product=productRepository.findById(pid).
                    orElseThrow(()->new ProductServiceException("Product service- stock out- not found-pid: "+pid));
        if(product.stockOut(quantity)){
return  true;
        }
            throw new ProductServiceException(
                    String.format("Product service- stock not enough for out pid: %d,qty: %d,stock: %d" ,pid,quantity,product.getStock()));
        }catch (ProductServiceException exception){
            logger.warn(exception.getMessage());
            throw exception;
        }
    }

}
