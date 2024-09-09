package com.fsse2406.fsse2406_project_backend.objects.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2406.fsse2406_project_backend.objects.transaction.Status;
import com.fsse2406.fsse2406_project_backend.objects.transaction.domainObject.TransactionResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.entity.TransactionEntity;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.dto.TransactionProductResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.entity.TransactionProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {
    private Integer tid;

    private Integer buyerUid;

    private LocalDateTime datetime;

    private Status status;

    private BigDecimal total;

    private List<TransactionProductResponseDto> items=new ArrayList<>();

    public TransactionResponseDto(TransactionResponseData data) {
        this.tid=data.getTid();
        this.buyerUid=data.getUid();
        this.datetime=data.getDatetime();
        this.status=data.getStatus();
        this.total=data.getTotal();
        for (TransactionProductResponseData tpData:data.getItems()){
            this.items.add(new TransactionProductResponseDto(tpData));
        }
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public List<TransactionProductResponseDto> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductResponseDto> items) {
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

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
    }
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }



}
