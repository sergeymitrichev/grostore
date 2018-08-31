package ru.ftob.grostore.model.productlist;

import ru.ftob.grostore.model.base.AbstractProductListEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_product_list")
public class UserProductList extends AbstractProductListEntity {
    public UserProductList() {
    }

}
