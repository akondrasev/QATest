package com.bytelife.qatest.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Dmitri Maksimov
 */
@Entity
@NamedQueries(
    @NamedQuery(name = "VirtualMachine.findAll", query = "SELECT vm FROM VirtualMachine vm WHERE vm.deleted = false")
)
public class VirtualMachine implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  private String name;

  @NotNull
  private PowerState powerState;

  @Temporal(TemporalType.TIMESTAMP)
  protected Date created;

  @Temporal(TemporalType.TIMESTAMP)
  protected Date modified;

  private boolean deleted;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PowerState getPowerState() {
    return powerState;
  }

  public void setPowerState(PowerState powerState) {
    this.powerState = powerState;
  }

  public Date getCreated() {
    return created;
  }

  public Date getModified() {
    return modified;
  }

  @PrePersist
  protected void setCreated() {
    created = new Date();
    modified = created;
  }

  @PreUpdate
  protected void setModified() {
    modified = new Date();
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    VirtualMachine that = (VirtualMachine) o;

    return getId() == that.getId();

  }

  @Override
  public int hashCode() {
    return id;
  }

}
