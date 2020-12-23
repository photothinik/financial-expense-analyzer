package org.photothinik.finance.expense.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ExpenseRecord {

    @Id
    @GeneratedValue(generator = "exp_record_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "exp_record_seq",
            sequenceName = "exp_record_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "transaction_dt")
    private Date transactionDate;

    private String description;

    @Column(name = "check_number")
    private Integer checkNumber;

    private String amount;

//    @ManyToOne
//    @JoinColumn(name = "category_override")
//    private Category categoryOverride;

    @Column(name = "category_override")
    private Long categoryIdOverride;

    @Column
    private String label;

    public ExpenseRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getCategoryIdOverride() {
        return categoryIdOverride;
    }

    public void setCategoryIdOverride(Long categoryIdOverride) {
        this.categoryIdOverride = categoryIdOverride;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ExpenseRecord{" +
                "id=" + id +
                ", transactionDate=" + transactionDate +
                ", description='" + description + '\'' +
                ", checkNumber=" + checkNumber +
                ", amount='" + amount + '\'' +
                ", categoryIdOverride=" + categoryIdOverride +
                '}';
    }
}
