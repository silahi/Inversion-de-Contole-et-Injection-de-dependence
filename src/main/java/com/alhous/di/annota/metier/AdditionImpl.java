package com.alhous.di.annota.metier;

import com.alhous.di.dao.INombre;
import com.alhous.di.metier.IAddition;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author silah
 */
@Component("additionImplV2")
public class AdditionImpl implements IAddition {

    @Autowired
    @Resource(name = "nombreImplV2") // optionnel
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
