package org.azure.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by adil on 25/11/15.
 */
@Entity
@Table(name = "avatar")
public class Avatar {
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

}
