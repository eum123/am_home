package com.am.homework.cache.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.am.homework.cache.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
   List<CategoryEntity> findAllByParentNo(int parentNo);
   int countByDepth(int depth);
}