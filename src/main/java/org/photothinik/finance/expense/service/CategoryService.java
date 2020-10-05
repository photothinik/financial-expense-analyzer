package org.photothinik.finance.expense.service;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {return this.categoryRepository.findAll();}

    public Category getById(long id) {

        Optional<Category> result = this.categoryRepository.findById(id);

        if( result.isPresent())
            return result.get();
        else
            throw new IllegalArgumentException("No category found with ID " + id);
    }

    public Category save(Category c) {return this.categoryRepository.save(c);}

    public Category delete(Category c) {
        this.categoryRepository.delete(c);

        return c;
    }
}
