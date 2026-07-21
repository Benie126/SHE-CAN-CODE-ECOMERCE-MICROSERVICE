package com.shecancode.product.controller;
import com.shecancode.product.dto.*; import com.shecancode.product.model.*; import com.shecancode.product.repo.*; import org.springframework.http.*; import org.springframework.transaction.annotation.Transactional; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/products") public class ProductController {
 private final ProductRepository products; private final CategoryRepository categories; public ProductController(ProductRepository p,CategoryRepository c){products=p;categories=c;}
 @GetMapping public List<Product> list(@RequestParam(name="search",required=false) String search){return search==null||search.isBlank()?products.findByActiveTrue():products.findByActiveTrueAndNameContainingIgnoreCase(search);}
 @GetMapping("/{id}") public Product one(@PathVariable Long id){return products.findById(id).orElseThrow();}
 @PostMapping public ResponseEntity<Product> create(@RequestBody ProductRequest r){Category c=categories.findById(r.categoryId()).orElseThrow(); Product p=new Product(r.name(),r.description(),r.price(),r.stockQuantity()==null?0:r.stockQuantity(),c); if(r.active()!=null)p.setActive(r.active()); return ResponseEntity.status(201).body(products.save(p));}
 @PutMapping("/{id}") public Product update(@PathVariable Long id,@RequestBody ProductRequest r){Product p=one(id); p.setName(r.name());p.setDescription(r.description());p.setPrice(r.price());p.setStockQuantity(r.stockQuantity());p.setCategory(categories.findById(r.categoryId()).orElseThrow()); if(r.active()!=null)p.setActive(r.active()); return products.save(p);}
 @DeleteMapping("/{id}") public void deactivate(@PathVariable Long id){Product p=one(id);p.setActive(false);products.save(p);}
 @PatchMapping("/{id}/stock") public Product setStock(@PathVariable Long id,@RequestBody StockRequest r){Product p=one(id); if(r.quantity()<0)throw new IllegalArgumentException("Stock cannot be negative");p.setStockQuantity(r.quantity());return products.save(p);}
 @PostMapping("/{id}/reduce") @Transactional public Product reduce(@PathVariable Long id,@RequestBody StockRequest r){Product p=one(id);if(!p.isActive()||p.getStockQuantity()<r.quantity())throw new IllegalArgumentException("Insufficient stock");p.setStockQuantity(p.getStockQuantity()-r.quantity());return products.save(p);}
 @PostMapping("/{id}/restore") @Transactional public Product restore(@PathVariable Long id,@RequestBody StockRequest r){Product p=one(id);p.setStockQuantity(p.getStockQuantity()+r.quantity());return products.save(p);}
}
