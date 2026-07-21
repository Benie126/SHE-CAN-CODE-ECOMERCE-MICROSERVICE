package com.shecancode.order.client; import org.springframework.cloud.openfeign.FeignClient; import org.springframework.web.bind.annotation.*; import java.math.BigDecimal;@FeignClient(name="PRODUCT-SERVICE")
public interface ProductClient {
    @GetMapping("/api/products/{id}")
    ProductView get(@PathVariable("id") Long id);
    @PostMapping("/api/products/{id}/reduce")
    ProductView reduce(@PathVariable("id") Long id,@RequestBody StockRequest r);
    @PostMapping("/api/products/{id}/restore")
    ProductView restore(@PathVariable("id") Long id,@RequestBody StockRequest r); record ProductView(Long id,String name,String description,BigDecimal price,int stockQuantity,boolean active,Object category){} record StockRequest(Integer quantity){} }