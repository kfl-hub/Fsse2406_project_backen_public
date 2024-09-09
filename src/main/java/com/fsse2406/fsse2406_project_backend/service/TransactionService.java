package com.fsse2406.fsse2406_project_backend.service;


import com.fsse2406.fsse2406_project_backend.objects.transaction.domainObject.TransactionResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.dto.TransactionResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import jakarta.transaction.Transactional;

import java.util.List;

public interface TransactionService {


    TransactionResponseData CreateTransaction(FirebaseUserData firebaseUserData);

    List<TransactionResponseData> getALlTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData,Integer tid);

    boolean payForTransaction(FirebaseUserData firebaseUserData, Integer tid);

    TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid);
}
