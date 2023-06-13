/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ista.examen.controller;

import com.ista.examen.model.entity.Producto;
import com.ista.examen.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LaptopSA
 */
@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listaProductos() {
        return new ResponseEntity<>(productoService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto pr) {
        pr.setSubtotal(pr.calcularSubtotal(pr.getCantidad(), pr.getPrecio()));
        pr.setDescuento(0.10);
        double iba = pr.getPrecio() * pr.getIva();
        if (pr.getSubtotal() > 50) {
            pr.setPvp((pr.getSubtotal() - pr.getDescuento()) + iba);
        } else {
            pr.setPvp(pr.getSubtotal() + iba);
        }
        return new ResponseEntity<>(productoService.save(pr), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProductos(@PathVariable Integer id, @RequestBody Producto pr) {
        Producto productos = productoService.findById(id);
        if (productos != null) {
            try {
                productos.setCantidad(pr.getCantidad());
                productos.setDescripcion(pr.getDescripcion());
                productos.setDescuento(pr.getDescuento());
                productos.setIva(pr.getIva());
                productos.setPrecio(pr.getPrecio());
                productos.setPvp(pr.getPvp());
                productos.setSubtotal(pr.getSubtotal());
                return new ResponseEntity<>(productoService.save(pr), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Producto> elimiarProducto(@PathVariable Integer id) {
        productoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
