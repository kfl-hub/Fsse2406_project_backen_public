package com.fsse2406.fsse2406_project_backend.objects.transaction.domainObject;

import com.fsse2406.fsse2406_project_backend.objects.transaction.Status;
import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity.TransactionProductEntity;
import com.fsse2406.fsse2406_project_backend.objects.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseData {
    private Integer tid;

    private Integer uid;

    private LocalDateTime datetime;

    private Status status;

    private BigDecimal total;

    private List<TransactionProductResponseData> items=new ArrayList<>();

    public TransactionResponseData() {
    }

    public TransactionResponseData(TransactionEntity entity) {
        this.tid=entity.getTid();
        this.uid=entity.getUser().getUid();
        this.datetime=entity.getDatetime();
        this.status=entity.getStatus();
        this.total=entity.getTotal();
        for (TransactionProductEntity tpEntity:entity.getItems()){
            this.items.add(new TransactionProductResponseData(tpEntity));
        }
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public List<TransactionProductResponseData> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductResponseData> items) {
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
