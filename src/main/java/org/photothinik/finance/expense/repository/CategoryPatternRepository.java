package org.photothinik.finance.expense.repository;

import org.photothinik.finance.expense.model.CategoryPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryPatternRepository extends JpaRepository<CategoryPattern, Long> {
}
