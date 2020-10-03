package org.photothinik.finance.expense.repository;

import org.photothinik.finance.expense.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

}
