package br.com.fiap.resource;

import br.com.fiap.bo.MedicoBO;
import br.com.fiap.to.MedicoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/medico")
public class MedicoResource {
    private MedicoBO bo = new MedicoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<MedicoTO> lista = bo.findAll();
        if (lista.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{crm}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("crm") Long crm) {
        MedicoTO medico = bo.findByCodigo(crm);
        if (medico == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(medico).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid MedicoTO medico) {
        MedicoTO m = bo.save(medico);
        if (m != null)
            return Response.status(Response.Status.CREATED).entity(m).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{crm}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("crm") Long crm, @Valid MedicoTO medico) {
        medico.setNrCrm(crm);
        MedicoTO m = bo.update(medico);
        if (m != null)
            return Response.ok(m).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{crm}")
    public Response delete(@PathParam("crm") Long crm) {
        if (bo.delete(crm))
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
