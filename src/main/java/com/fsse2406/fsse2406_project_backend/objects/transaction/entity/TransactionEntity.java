package com.fsse2406.fsse2406_project_backend.objects.transaction.entity;

import com.fsse2406.fsse2406_project_backend.objects.transaction.Status;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity.TransactionProductEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction",indexes = @Index(columnList = "tid"))
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne
    @JoinColumn(name = "buyerUid", referencedColumnName = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private BigDecimal total ;

    @OneToMany//(mappedBy = "transaction")
    private List<TransactionProductEntity> items = new ArrayList<>();

    public TransactionEntity(UserEntity user) {
        this.status = Status.PREPARE;
        this.user = user;
        this.total=BigDecimal.ZERO;
        this.datetime=LocalDateTime.now();

    }

    public TransactionEntity() {
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public List<TransactionProductEntity> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductEntity> items) {
        this.items = items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void addTotal(BigDecimal subtotal) {
        this.total=this.total.add(subtotal);
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
