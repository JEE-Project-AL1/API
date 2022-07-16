package com.esgi.jee.apijee.apiComment.apiJee.Item.repository;

import com.esgi.jee.apijee.apiComment.apiJee.Item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
