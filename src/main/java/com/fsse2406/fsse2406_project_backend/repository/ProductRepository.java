package com.fsse2406.fsse2406_project_backend.repository;

import com.fsse2406.fsse2406_project_backend.objects.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {
}
