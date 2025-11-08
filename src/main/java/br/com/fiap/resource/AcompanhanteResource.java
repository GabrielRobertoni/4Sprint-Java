package br.com.fiap.resource;

import br.com.fiap.bo.AcompanhanteBO;
import br.com.fiap.to.AcompanhanteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/acompanhante")
public class AcompanhanteResource {

    private AcompanhanteBO bo = new AcompanhanteBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<AcompanhanteTO> lista = bo.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id") Long id) {
        AcompanhanteTO a = bo.findByCodigo(id);
        return (a != null) ? Response.ok(a).build() : Response.status(404).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid AcompanhanteTO a) {
        AcompanhanteTO novo = bo.save(a);
        return (novo != null) ? Response.ok(novo).build() : Response.status(400).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, @Valid AcompanhanteTO a) {
        a.setCdAcompanhante(id);
        AcompanhanteTO atualizado = bo.update(a);
        return (atualizado != null) ? Response.ok(atualizado).build() : Response.status(400).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return bo.delete(id) ? Response.noContent().build() : Response.status(404).build();
    }
}
