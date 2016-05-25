package com.bytelife.qatest.service;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.bytelife.qatest.dao.VirtualMachineDAO;
import com.bytelife.qatest.model.VirtualMachine;

/**
 * @author Dmitri Maksimov
 */
@Stateless
public class VirtualMachineService {

  @Inject
  VirtualMachineDAO virtualMachineDAO;

  public List<VirtualMachine> getAll() {
    return virtualMachineDAO.findAll();
  }

  public Optional<VirtualMachine> getById(int id) {
    return virtualMachineDAO.findById(id);
  }

  public boolean deleteById(int id) {
    Optional<VirtualMachine> vm = getById(id);
    if (vm.isPresent()) {
      virtualMachineDAO.delete(vm.get());
      return true;
    }
    return false;
  }

  public VirtualMachine create(VirtualMachine vm) {
    return virtualMachineDAO.create(vm);
  }

  public VirtualMachine update(VirtualMachine vm) {
    return virtualMachineDAO.update(vm);
  }

}
