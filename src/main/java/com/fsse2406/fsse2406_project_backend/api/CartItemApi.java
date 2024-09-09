package com.fsse2406.fsse2406_project_backend.api;

import com.fsse2406.fsse2406_project_backend.config.EnvConfig;
import com.fsse2406.fsse2406_project_backend.objects.cartItem.domainObject.CartItemResponseData;
import com.fsse2406.fsse2406_project_backend.objects.cartItem.dto.CartItemResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.cartItem.dto.SuccessCartItemResponseDto;
import com.fsse2406.fsse2406_project_backend.objects.user.domainObject.FirebaseUserData;
import com.fsse2406.fsse2406_project_backend.service.CartItemService;
import com.fsse2406.fsse2406_project_backend.util.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin({EnvConfig.DEV_BSE_URL, EnvConfig.PROD_BSE_URL})
@RequestMapping("/cart")
public class CartItemApi {
private final CartItemService cartItemService;

    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}/{size}")
    public SuccessCartItemResponseDto putCartItem(
            JwtAuthenticationToken jwtToken,
            @PathVariable Integer pid,
            @PathVariable Integer quantity,
            @PathVariable(required = false) String size
    ) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        cartItemService.putCartItem(pid, quantity, size, firebaseUserData);
        return new SuccessCartItemResponseDto();
    }

    @GetMapping
    public List<CartItemResponseDto> getCartItem (JwtAuthenticationToken jwtToken){
        FirebaseUserData firebaseUserData= JwtUtil.getFirebaseUserData(jwtToken);
        List<CartItemResponseDto> cartItemRespoenseDtoList=new ArrayList<>();
        for (CartItemResponseData data:cartItemService.getUserCart(firebaseUserData)){
            cartItemRespoenseDtoList.add(new CartItemResponseDto(data));
        }
        return cartItemRespoenseDtoList;
    }

    @GetMapping("/count")
    public Integer getCartItemCount(JwtAuthenticationToken jwtToken){
        FirebaseUserData firebaseUserData= JwtUtil.getFirebaseUserData(jwtToken);
        return cartItemService.getUserCartQuantity(firebaseUserData);
    }

    @PatchMapping("/{cid}/{quantity}/{size}")
    public CartItemResponseDto updateCartItemQty(
            JwtAuthenticationToken jwtToken,
            @PathVariable Integer cid,
            @PathVariable Integer quantity,
            @PathVariable(required = false) String size // Optional
    ) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new CartItemResponseDto(
                cartItemService.updateCartQuantity(cid, quantity, size, firebaseUserData)
        );
    }

    @DeleteMapping("/{cid}")
    public SuccessCartItemResponseDto DeleteCartItem    (JwtAuthenticationToken jwtToken,@PathVariable Integer cid){
        FirebaseUserData firebaseUserData= JwtUtil.getFirebaseUserData(jwtToken);
        cartItemService.deleteCartItem(cid,firebaseUserData);
        return new SuccessCartItemResponseDto();
    }

    @GetMapping("/apitest")
    public String aptTest (){
        return ("hi");
    }
}
