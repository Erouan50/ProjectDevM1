package org.youfood.utils;


import com.google.inject.persist.PersistService;

import javax.inject.Inject;


/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class JPAControl {

    @Inject
    private PersistService persistService;

    public void startJpa() {
        persistService.start();
    }

    public void stopJpa() {
        persistService.stop();
    }
}
