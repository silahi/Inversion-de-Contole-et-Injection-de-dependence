package com.alhous.di.metier;

import com.alhous.di.dao.INombre;

/**
 *
 * @author silah
 */
public class AdditionImpl implements IAddition {

    private INombre nombre;

    @Override
    public double somme() {
        return nombre.getNombre1() + nombre.getNombre2();
    }

    public INombre getNombre() {
        return nombre;
    }

    public void setNombre(INombre nombre) {
        this.nombre = nombre;
    }   

}
