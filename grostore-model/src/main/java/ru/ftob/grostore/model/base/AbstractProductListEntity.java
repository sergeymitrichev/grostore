package ru.ftob.grostore.model.base;

import ru.ftob.grostore.model.security.PermissionType;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractProductListEntity extends AbstractNamedEntity {

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

    public AbstractProductListEntity() {
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    @Override
    public String toString() {
        return "AbstractProductListEntity{" +
                "permissionType=" + permissionType +
                "} " + super.toString();
    }
}
