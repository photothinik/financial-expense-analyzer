package org.photothinik.finance.expense.service;

import org.photothinik.finance.expense.model.CategoryPattern;
import org.photothinik.finance.expense.repository.CategoryPatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryPatternService {

    @Autowired
    private CategoryPatternRepository categoryPatternRepository;

    public CategoryPattern findById(Long id) {return this.categoryPatternRepository.findById(id).get();}

    public CategoryPattern save(CategoryPattern p) {return this.categoryPatternRepository.save(p);}

    public CategoryPattern delete(CategoryPattern p) {
        this.categoryPatternRepository.delete(p);

        return p;
    }

}
