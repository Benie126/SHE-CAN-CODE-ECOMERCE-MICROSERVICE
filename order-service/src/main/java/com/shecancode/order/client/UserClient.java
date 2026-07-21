package com.shecancode.order.client; import org.springframework.cloud.openfeign.FeignClient; import org.springframework.web.bind.annotation.*;@FeignClient(name="USER-SERVICE")
public interface UserClient {
    @GetMapping("/api/users/{id}")
    UserView get(@PathVariable("id") Long id);
    @GetMapping("/api/users/{userId}/addresses/{addressId}")
    AddressView address(@PathVariable("userId") Long userId,@PathVariable("addressId") Long addressId); record UserView(Long id,String firstName,String lastName,String email,String role,boolean enabled){} record AddressView(Long id,Long userId,String province,String district,String sector,String street){} }