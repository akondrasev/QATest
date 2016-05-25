package com.bytelife.qatest.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityTransaction;

/**
 * @author akondrasev
 */
public class VirtualMachineDAOTest {

    VirtualMachineDAO virtualMachineDAO;
    EntityTransaction transaction;


    @Before
    public void setUp() throws Exception {
        virtualMachineDAO = new VirtualMachineDAO();
//        virtualMachineDAO.em = Persistence.createEntityManagerFactory("integration").createEntityManager();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

}