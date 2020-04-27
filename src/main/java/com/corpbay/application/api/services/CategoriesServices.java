package com.corpbay.application.api.services;

import com.corpbay.application.api.entity.Categories;
import com.corpbay.application.api.repositories.CategoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServices {

    private final CategoriesDao categoriesDao;

    @Autowired
    public CategoriesServices(CategoriesDao categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    public Categories addCategory(Categories categories) {
        categories.setCreatedDate(new Date());
        categories.setUpdateDate(new Date());
        categoriesDao.save(categories);
        return categories;
    }

    public Categories getCategory(Long id) throws Exception {
        Optional<Categories> categories = categoriesDao.findById(id);
        if(!categories.isPresent())
            throw new Exception("Category doesn't exist");
        return categories.get();
    }

    public List<Categories> getAllCategories() throws Exception {
        List<Categories> categories = categoriesDao.findAll();
        if(categories.isEmpty())
            throw new Exception("No Categories exist");
        return categories;
    }

    public Categories deleteCategory(Long id) throws Exception {
        Categories categories = getCategory(id);
        categoriesDao.delete(categories);
        return categories;
    }

}
