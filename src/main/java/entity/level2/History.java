package entity.level2;

import annotation.Segregated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Segregated(tableName = "history")
public abstract class History {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "accountId")
    private Integer accountId;

    @Column(name = "lastUpdate")
    private String lastUpdate;

    public Integer getId() {
        return id;
    }

    public History setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public History setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public History setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }
}
