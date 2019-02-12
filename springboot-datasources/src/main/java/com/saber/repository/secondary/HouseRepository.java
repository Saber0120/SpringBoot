package com.saber.repository.secondary;

import com.saber.entity.secondary.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer> {
}
