package org.photothinik.finance.expense.service;

import org.photothinik.finance.expense.model.Category;
import org.photothinik.finance.expense.model.CategoryPattern;
import org.photothinik.finance.expense.repository.CategoryPatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryPatternService {

    @Autowired
    private CategoryPatternRepository categoryPatternRepository;

    public CategoryPattern findById(Long id) {return this.categoryPatternRepository.findById(id).get();}

    public List<CategoryPattern> getAllPatternsByCategory(Category c) {
        List<CategoryPattern> result = new ArrayList<>();
        List<CategoryPattern> allPatterns = this.categoryPatternRepository.findAll();
        for(CategoryPattern p : allPatterns) {
            if( p.getCategoryId().longValue() == c.getId().longValue())
                result.add(p);
        }


        return result;
    }

    public CategoryPattern save(CategoryPattern p) {return this.categoryPatternRepository.save(p);}

    public CategoryPattern delete(CategoryPattern p) {
        this.categoryPatternRepository.delete(p);

        return p;
    }

}
