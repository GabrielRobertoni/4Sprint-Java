package br.com.fiap.resource;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.to.PacienteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/paciente")
public class PacienteResource {

    private PacienteBO bo = new PacienteBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<PacienteTO> lista = bo.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id") Long id) {
        PacienteTO p = bo.findByCodigo(id);
        return (p != null) ? Response.ok(p).build() : Response.status(404).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid PacienteTO p) {
        PacienteTO novo = bo.save(p);
        return (novo != null) ? Response.ok(novo).build() : Response.status(400).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, @Valid PacienteTO p) {
        p.setIdPaciente(id);
        PacienteTO atualizado = bo.update(p);
        return (atualizado != null) ? Response.ok(atualizado).build() : Response.status(400).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return bo.delete(id) ? Response.noContent().build() : Response.status(404).build();
    }
}
