package org.photothinik.finance.expense.model;

import javax.persistence.*;

@Entity
@Table(name = "category_pattern")
public class CategoryPattern {

    @Id
    @GeneratedValue(generator = "category_pattern_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "category_pattern_seq",
            sequenceName = "category_pattern_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(nullable = false, name = "category_id")
    private Long categoryId;

    @Column(nullable = false)
    private String pattern;

    public CategoryPattern() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "CategoryPattern{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
