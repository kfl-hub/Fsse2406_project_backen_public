package com.fsse2406.fsse2406_project_backend.api;

import com.fsse2406.fsse2406_project_backend.config.EnvConfig;
import com.fsse2406.fsse2406_project_backend.objects.transaction.domainObject.TransactionResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.dto.SuccessTransactionResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.transaction.dto.TransactionResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import com.fsse2406.fsse2406_project_backend.service.TransactionService;
import com.fsse2406.fsse2406_project_backend.util.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin({EnvConfig.DEV_BSE_URL, EnvConfig.PROD_BSE_URL})
@RequestMapping("/transaction")
public class TransactionApi {
    private final TransactionService transactionService;

    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto createTransaction(JwtAuthenticationToken jwtToken) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionResponseDto(
                transactionService.CreateTransaction(firebaseUserData));
    }

    @GetMapping("/all")
    public List<TransactionResponseDto> getAllTransaction(JwtAuthenticationToken jwtToken) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        List<TransactionResponseDto> tResDto = new ArrayList<>();
        for (TransactionResponseData data : transactionService.getALlTransaction(firebaseUserData)) {
            tResDto.add(new TransactionResponseDto(data));
        }
        return tResDto;
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionByTid(JwtAuthenticationToken jwtToken, @PathVariable Integer tid) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionResponseDto(
                transactionService.getTransactionByTid(firebaseUserData, tid));
    }

    @PatchMapping("/{tid}/pay")
    public SuccessTransactionResponseDto payForTransaction(JwtAuthenticationToken jwtToken, @PathVariable Integer tid) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        transactionService.payForTransaction(firebaseUserData, tid);
        return new SuccessTransactionResponseDto();
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto finishTransaction(JwtAuthenticationToken jwtToken, @PathVariable Integer tid) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionResponseDto(
                transactionService.finishTransaction(firebaseUserData, tid));
    }


}
