package com.bytelife.qatest.service;

import com.bytelife.qatest.dao.VirtualMachineDAO;
import com.bytelife.qatest.model.VirtualMachine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.*;

/**
 * @author akondrasev
 */
public class VirtualMachineServiceTest {

    private VirtualMachineService virtualMachineService;
    private VirtualMachineDAO mockedDao = mock(VirtualMachineDAO.class);

    @Before
    public void setUp() throws Exception {
        virtualMachineService = new VirtualMachineService();
        virtualMachineService.virtualMachineDAO = mockedDao;
    }

    @Test
    public void getAll() throws Exception {
        List<VirtualMachine> expected = new ArrayList<>();
        expected.add(new VirtualMachine());
        when(mockedDao.findAll()).thenReturn(expected);

        List<VirtualMachine> result = virtualMachineService.getAll();

        assertEquals(expected, result);
    }

    @Test
    public void getById() throws Exception {
        VirtualMachine expected = new VirtualMachine();
        expected.setName("Test");

        Optional<VirtualMachine> optional = Optional.of(expected);
        when(mockedDao.findById(anyInt())).thenReturn(optional);

        Optional<VirtualMachine> result = virtualMachineService.getById(1);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    public void deleteById() throws Exception {
        VirtualMachine expected = new VirtualMachine();
        expected.setName("Test");

        Optional<VirtualMachine> optional = Optional.empty();
        when(mockedDao.findById(anyInt())).thenReturn(optional);
        doNothing().when(mockedDao).delete(any(VirtualMachine.class));

        //assert not deleted case
        boolean result = virtualMachineService.deleteById(1);
        assertFalse(result);


        //deleted case
        optional = Optional.of(expected);
        result = virtualMachineService.deleteById(1);
        assertFalse(result);
    }

    @Test
    public void create() throws Exception {
        int expectedId = 50;
        when(mockedDao.create(any(VirtualMachine.class))).then((Answer<VirtualMachine>) invocation -> {
            VirtualMachine virtualMachine = (VirtualMachine) invocation.getArguments()[0];
            virtualMachine.setId(expectedId);
            return virtualMachine;
        });

        VirtualMachine machine = new VirtualMachine();
        virtualMachineService.create(machine);

        assertEquals(expectedId, machine.getId());
    }

    @Test
    public void update() throws Exception {
        String expectedName = "Test";
        when(mockedDao.update(any(VirtualMachine.class))).then((Answer<VirtualMachine>) invocation -> {
            VirtualMachine virtualMachine = (VirtualMachine) invocation.getArguments()[0];
            virtualMachine.setName(expectedName);
            return virtualMachine;
        });

        VirtualMachine machine = new VirtualMachine();
        virtualMachineService.update(machine);

        assertEquals(expectedName, machine.getName());
    }

}