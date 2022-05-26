package com.esgi.jee.apijee.item.infrastructure;
import com.esgi.jee.apijee.category.domain.Category;
import com.esgi.jee.apijee.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByCategory(Category category);
}