package com.bytelife.qatest.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bytelife.qatest.model.VirtualMachine;

/**
 * @author Dmitri Maksimov
 */
@Dependent
public class VirtualMachineDAO implements Serializable{

  @PersistenceContext
  private EntityManager em;

  public Optional<VirtualMachine> findById(int id) {
    return Optional.ofNullable(em.find(VirtualMachine.class, id));
  }

  public List<VirtualMachine> findAll() {
    return em.createNamedQuery("VirtualMachine.findAll", VirtualMachine.class).getResultList();
  }

  public void delete(VirtualMachine vm) {
    vm.setDeleted(true);
    em.merge(vm);
  }

  public VirtualMachine create(VirtualMachine vm) {
    em.persist(vm);
    return vm;
  }

  public VirtualMachine update(VirtualMachine vm) {
    return em.merge(vm);
  }

}
