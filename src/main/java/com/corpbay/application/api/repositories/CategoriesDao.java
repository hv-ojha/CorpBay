package com.corpbay.application.api.repositories;

import com.corpbay.application.api.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesDao extends JpaRepository<Categories, Long> {
}
