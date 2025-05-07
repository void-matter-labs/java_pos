package crud.app.product_form;

import com.google.inject.AbstractModule;

import crud.app.product_form.controllers.ProductFormMainController;
import crud.shared.persistence.dao.ProductsCsvDao;

public class ProductFormModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProductFormMainController.class);
        bind(ProductsCsvDao.class);
    }
}