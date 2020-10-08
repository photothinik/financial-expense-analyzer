package org.photothinik.finance.expense.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(generator = "category_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "category_seq",
            sequenceName = "category_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    private Set<CategoryPattern> patterns;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CategoryPattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(Set<CategoryPattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patterns=" + patterns +
                '}';
    }
}
