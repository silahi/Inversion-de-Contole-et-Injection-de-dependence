package com.alhous.di.annota.dao;

import com.alhous.di.dao.INombre;
import org.springframework.stereotype.Component;

/**
 *
 * @author silah
 */
@Component("nombreImplV2")
public class NombreImpl implements INombre {

    @Override
    public double getNombre1() {
        /* Code pour la récupération du premier nombre */
        return 15;
    }

    @Override
    public double getNombre2() {
        /* Code pour la récupération du deuxième nombre */
        return 15;
    }

}
