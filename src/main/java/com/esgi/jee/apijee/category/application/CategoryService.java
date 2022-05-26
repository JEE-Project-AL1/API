package com.esgi.jee.apijee.category.application;
import com.esgi.jee.apijee.category.domain.Category;
import com.esgi.jee.apijee.category.infrastructure.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category saveCategory(Category category) {
        Optional<Category> findCategory = categoryRepository.findByName(category.getName());
        if(findCategory.isPresent())
            throw new IllegalStateException("Category exist already");
       log.info("Saving new category {} to the database",category.getName());
       return categoryRepository.save(category);
    }


    public List<Category> getCategorys() {
        log.info("Fetching all Categorys ");
        return categoryRepository.findAll();
    }


    public void deleteById(Long id) {
       Optional<Category> category = categoryRepository.findById(id);
       log.info("Delete category {} to the database",category.get().getName());
       categoryRepository.delete(category.orElseThrow(() -> new IllegalStateException("Category not found")));


    }
}
