package com.bytelife.qatest.service;

import javax.ejb.Stateless;
import com.bytelife.qatest.model.VirtualMachine;
import com.bytelife.qatest.model.VirtualMachineState;

/**
 * @author Dmitri Maksimov
 */
@Stateless
public class VCenterService {

  public VirtualMachineState retrieveVirtualMachineState(VirtualMachine vm) {
    // in proper implementation it should request state from vCenter
    switch (vm.getId() % 3) {
      case 0:
        return VirtualMachineState.GREEN;
      case 1:
        return VirtualMachineState.YELLOW;
      default:
      case 2:
        return VirtualMachineState.RED;
    }
  }

}
