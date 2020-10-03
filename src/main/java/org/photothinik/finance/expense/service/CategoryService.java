package org.photothinik.finance.expense.service;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {return this.categoryRepository.findAll();}
}
