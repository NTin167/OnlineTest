package com.ptithcm.onlinetest.controller;

import com.ptithcm.onlinetest.model.Category;
import com.ptithcm.onlinetest.payload.request.CategoryRequest;
import com.ptithcm.onlinetest.payload.response.ApiResponse;
import com.ptithcm.onlinetest.payload.response.CategoryResponse;
import com.ptithcm.onlinetest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getAll")
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/getQuizzes/{categoryId}")
    public Iterable<?> getQuizzesByCategory(@PathVariable(value = "categoryId") Long id) {

        return categoryService.getQuizzByCategory(id);
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        Category category = categoryService.createCategory(categoryRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{categoryId}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location)
                .body(ApiResponse.builder().status(200).data(category).message("Created category successfully").build());
    }

    @PutMapping(value = "/edit/{categoryId}")
    public CategoryResponse updateCategory(@RequestBody @Valid CategoryRequest categoryRequest, @PathVariable(value = "categoryId") Long categoryId) {
        return categoryService.updateCategory(categoryId ,categoryRequest);
    }

    @DeleteMapping(value = "/delete")
    public CategoryResponse deleteCategory(@RequestParam Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }




//    API TEST
//    ---------------------------------------------------------------------------------------------------------
//    @DeleteMapping(value = "/deleteCourseType")
//    void deleteCourseType(@RequestParam Long courseTypeId) {
//        courseTypeRepository.deleteById(courseTypeId);
//    }
//    @GetMapping(value = "/get")
//    public Iterable<CourseType> getA() {
//        return courseTypeRepository.findAll();
//    }
//    @GetMapping(value = "/get1")
//    public Iterable<Course> getA1() {
//        return courseRepository.findAll();
//    }
}
