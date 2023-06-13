/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ista.examen.repository;

import com.ista.examen.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author LaptopSA
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
