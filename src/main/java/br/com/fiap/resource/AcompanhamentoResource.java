package br.com.fiap.resource;

import br.com.fiap.bo.AcompanhamentoBO;
import br.com.fiap.to.AcompanhamentoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.ArrayList;

@Path("/acompanhamento")
public class AcompanhamentoResource {

    private final AcompanhamentoBO bo = new AcompanhamentoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<AcompanhamentoTO> lista = bo.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{idPaciente}/{cdAcompanhante}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOne(@PathParam("idPaciente") Long idPaciente,
                            @PathParam("cdAcompanhante") Long cdAcompanhante) {
        AcompanhamentoTO a = bo.findOne(idPaciente, cdAcompanhante);
        return (a != null) ? Response.ok(a).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid AcompanhamentoTO a) {
        AcompanhamentoTO novo = bo.save(a);
        if (novo != null) {
            return Response.created(URI.create(String.format("/acompanhamento/%d/%d",
                    novo.getIdPaciente(), novo.getCdAcompanhante()))).entity(novo).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\":\"Falha ao salvar acompanhamento\"}").build();
    }

    @PUT
    @Path("/{idPaciente}/{cdAcompanhante}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idPaciente") Long idPaciente,
                           @PathParam("cdAcompanhante") Long cdAcompanhante,
                           @Valid AcompanhamentoTO body) {
        // Garante que atualizaremos a PK correta
        body.setIdPaciente(idPaciente);
        body.setCdAcompanhante(cdAcompanhante);

        AcompanhamentoTO atualizado = bo.update(body);
        return (atualizado != null) ? Response.ok(atualizado).build()
                : Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\":\"Falha ao atualizar acompanhamento\"}").build();
    }

    @DELETE
    @Path("/{idPaciente}/{cdAcompanhante}")
    public Response delete(@PathParam("idPaciente") Long idPaciente,
                           @PathParam("cdAcompanhante") Long cdAcompanhante) {
        return bo.delete(idPaciente, cdAcompanhante)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
