package com.fsse2406.fsse2406_project_backend.api;
import com.fsse2406.fsse2406_project_backend.config.EnvConfig;
import com.fsse2406.fsse2406_project_backend.objects.checkout.TransactionForStripeBody;
import com.fsse2406.fsse2406_project_backend.objects.transaction.domainObject.TransactionResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transaction.dto.SuccessTransactionResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2406.fsse2406_project_backend.objects.transactionProduct.dto.TransactionProductRequestDto;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import com.fsse2406.fsse2406_project_backend.service.TransactionService;
import com.fsse2406.fsse2406_project_backend.util.JwtUtil;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin({EnvConfig.DEV_BSE_URL, EnvConfig.PROD_BSE_URL})
@RequestMapping("/checkout")
public class CheckoutApi {
    private final TransactionService transactionService;

    public CheckoutApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    @PostMapping("/create-checkout-session/{tid}")
    public Map<String, String> createCheckoutSession(
            JwtAuthenticationToken jwtToken
            ,@PathVariable Integer tid) throws Exception {
        String YOUR_DOMAIN = "http://www.grabnrun.shop"; // Replace with your frontend domain
        FirebaseUserData firebaseUserData= JwtUtil.getFirebaseUserData(jwtToken);


        TransactionResponseData body=transactionService.getTransactionByTid(firebaseUserData,tid);

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        // Iterate over each CartItemDto to create a line item
        for (TransactionProductResponseData item : body.getItems()) {
            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(Long.valueOf(item.getQuantity()))
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("hkd")  // Set the currency, adjust as needed
                                    .setUnitAmount(item.getProduct().getPrice().multiply(BigDecimal.valueOf(100)).longValue()) // Stripe expects price in cents
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(item.getProduct().getName())
                                                    .setDescription("Size: " + item.getSize())
                                                    .addImage(item.getProduct().getImageUrl())
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            lineItems.add(lineItem);
        }

        // Start building session parameters
        SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/thankyou/"+body.getTid())
                .setCancelUrl(YOUR_DOMAIN + "/transaction/"+body.getTid());

        // Add each line item to the builder
        for (SessionCreateParams.LineItem lineItem : lineItems) {
            paramsBuilder.addLineItem(lineItem);
        }

        // Build the final session params
        SessionCreateParams params = paramsBuilder.build();

        // Create the session
        Session session = Session.create(params);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("url", session.getUrl());

        return responseData;
    }

}
