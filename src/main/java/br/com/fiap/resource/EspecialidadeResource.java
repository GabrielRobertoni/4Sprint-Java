package br.com.fiap.resource;

import br.com.fiap.bo.EspecialidadeBO;
import br.com.fiap.to.EspecialidadeTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/especialidade")
public class EspecialidadeResource {

    private EspecialidadeBO bo = new EspecialidadeBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<EspecialidadeTO> lista = bo.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long codigo) {
        EspecialidadeTO e = bo.findByCodigo(codigo);
        return (e != null) ? Response.ok(e).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(EspecialidadeTO e) {
        EspecialidadeTO novo = bo.save(e);
        if (novo != null)
            return Response.status(Response.Status.CREATED).entity(novo).build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\":\"Falha ao salvar especialidade\"}")
                .build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("codigo") Long codigo, EspecialidadeTO e) {
        e.setCdEspecialidade(codigo);
        EspecialidadeTO atualizado = bo.update(e);
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
