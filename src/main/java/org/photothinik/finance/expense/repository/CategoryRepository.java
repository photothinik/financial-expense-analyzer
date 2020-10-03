package org.photothinik.finance.expense.repository;

import org.photothinik.finance.expense.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

}
