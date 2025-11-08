package br.com.fiap.resource;

import br.com.fiap.bo.AtendimentoBO;
import br.com.fiap.to.AtendimentoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/atendimento")
public class AtendimentoResource {

    private AtendimentoBO bo = new AtendimentoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<AtendimentoTO> lista = bo.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long codigo) {
        AtendimentoTO a = bo.findByCodigo(codigo);
        return (a != null) ? Response.ok(a).build() : Response.status(404).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid AtendimentoTO a) {
        try {
            AtendimentoTO novo = bo.save(a);
            return (novo != null)
                    ? Response.status(Response.Status.CREATED).entity(novo).build()
                    : Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\":\"Erro ao salvar atendimento: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("codigo") Long codigo, @Valid AtendimentoTO a) {
        a.setCdAtendimento(codigo);
        AtendimentoTO atualizado = bo.update(a);
        return (atualizado != null)
                ? Response.ok(atualizado).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        return bo.delete(codigo)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
