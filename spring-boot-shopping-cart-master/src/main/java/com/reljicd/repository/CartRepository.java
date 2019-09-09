package com.reljicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reljicd.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	

}
