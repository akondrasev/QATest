package com.bytelife.qatest.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.bytelife.qatest.model.VirtualMachine;
import com.bytelife.qatest.model.VirtualMachineState;
import com.bytelife.qatest.service.VCenterService;
import com.bytelife.qatest.service.VirtualMachineService;

/**
 * @author Dmitri Maksimov
 */
@Path("vm")
@Produces(MediaType.APPLICATION_JSON)
public class VirtualMachineEndpoint {

  @Inject
  private VirtualMachineService virtualMachineService;
  @Inject
  private VCenterService vCenterService;

  @GET
  public List<VirtualMachine> getAll() {
    return virtualMachineService.getAll();
  }

  @GET
  @Path("{id}")
  public VirtualMachine getById(@PathParam("id") int id) {
    if (id == 0) {
      return new VirtualMachine();
    }
    return virtualMachineService.getById(id).orElseThrow(NotFoundException::new);
  }

  @POST
  public VirtualMachine create(VirtualMachine vm) {
    return virtualMachineService.create(vm);
  }

  @PUT
  @Path("{id}")
  public VirtualMachine update(VirtualMachine vm) {
    return virtualMachineService.update(vm);
  }

  @DELETE
  @Path("{id}")
  public Response delete(@PathParam("id") int id) {
    boolean deleted = virtualMachineService.deleteById(id);
    return deleted ? Response.ok().build() : Response.notModified().build();
  }

  @GET
  @Path("{id}/state")
  public VirtualMachineState getState(@PathParam("id") int id) {
    VirtualMachine virtualMachine = virtualMachineService.getById(id).orElseThrow(NotFoundException::new);

    return vCenterService.retrieveVirtualMachineState(virtualMachine);
  }

}
