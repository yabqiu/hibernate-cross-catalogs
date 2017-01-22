package entity;

import annotation.Segregated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Segregated(tableName = "account")
public abstract class Account {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name = "hk";

    public Integer getId() {
        return id;
    }

    public Account setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }
}
