package com.corpbay.application.api.controllers;

import com.corpbay.application.api.entity.Categories;
import com.corpbay.application.api.services.CategoriesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesServices categoriesServices;

    @Autowired
    public CategoriesController(CategoriesServices categoriesServices) {
        this.categoriesServices = categoriesServices;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        try {
            return ResponseEntity.ok(categoriesServices.getAllCategories());
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addCategories(@RequestBody Categories categories) {
        try {
            return ResponseEntity.ok(categoriesServices.addCategory(categories));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategories(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(categoriesServices.getCategory(id));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategories(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(categoriesServices.deleteCategory(id));
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
