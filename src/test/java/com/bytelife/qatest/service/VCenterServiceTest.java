package com.bytelife.qatest.service;

import com.bytelife.qatest.model.VirtualMachine;
import com.bytelife.qatest.model.VirtualMachineState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author akondrasev
 */
public class VCenterServiceTest {
    private VCenterService vCenterService;
    @Before
    public void setUp() throws Exception {
        vCenterService = new VCenterService();
    }

    @Test
    public void retrieveVirtualMachineState() throws Exception {
        VirtualMachine machine = new VirtualMachine();

        //0 case
        machine.setId(0);
        VirtualMachineState expectedState = VirtualMachineState.GREEN;
        VirtualMachineState actualState = vCenterService.retrieveVirtualMachineState(machine);
        assertEquals(expectedState, actualState);

        //1 case
        expectedState = VirtualMachineState.YELLOW;
        machine.setId(1);
        actualState = vCenterService.retrieveVirtualMachineState(machine);
        assertEquals(expectedState, actualState);

        //default case
        expectedState = VirtualMachineState.RED;
        machine.setId(5);
        actualState = vCenterService.retrieveVirtualMachineState(machine);
        assertEquals(expectedState, actualState);
    }

}