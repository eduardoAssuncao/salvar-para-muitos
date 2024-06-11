package com.devsuperior.aula.services;

import com.devsuperior.aula.dtos.CategoryDTO;
import com.devsuperior.aula.dtos.ProductDTO;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        for(CategoryDTO categoryDTO : dto.getCategories()) {
            Category categoryEntity = categoryRepository.getReferenceById(categoryDTO.getId());

            //Category categoryEntity = new Category();
            categoryEntity.setId(categoryDTO.getId());
            entity.getCategories().add(categoryEntity);
        }

        entity = productRepository.save(entity);;

        return new ProductDTO(entity);
    }
}
