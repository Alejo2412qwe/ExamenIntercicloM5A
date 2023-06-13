/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ista.examen.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LaptopSA
 */
@Data
@Entity
@Table(name = "Producto")
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private int idProducto;

    @Size(max = 100)
    private String descripcion;
    private double precio;
    private int cantidad;
    private double subtotal;
    private double descuento;
    private double iva = 0.12;
    private double pvp;

    public double calcularSubtotal(double cant, double pre) {
        subtotal = cant * pre;
        return subtotal;
    }

}
