package org.globex.gateway.partner.rest;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.globex.gateway.partner.services.PartnerCatalogService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("partners/services")
public class PartnerCatalogResource {

    @RestClient
    PartnerCatalogService partnerCatalogService;


    /**
     * Get a paginated list of products
     */
    @GET
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> readAll(@QueryParam("page") Integer page, @QueryParam("limit") Integer limit,
            @QueryParam("inventory") Boolean inventory) {
                return partnerCatalogService.readAll(page, limit, inventory);
            }

    /**
     * Get product by id
     */
    @Path("/product/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getProductById(@PathParam("id") String id, @QueryParam("inventory") Boolean inventory){
        return partnerCatalogService.getProductById(id, inventory);
    }

    /**
     * Get product list
     */
    @Path("/product/list/{ids}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getProductList(@PathParam("ids") String ids, @QueryParam("inventory") Boolean inventory){
        return partnerCatalogService.getProductList(ids, inventory);
    }

    @Path("/product/category/{categories}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response>  getProductsByCategory(@PathParam("categories") String categories,
            @QueryParam("inventory") Boolean inventory){
                return partnerCatalogService.getProductsByTag(inventory, categories);
            }

    @Path("/product/tag/{tags}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response>  getProductsByTag(@QueryParam("inventory") Boolean inventory, @PathParam("tags") String tags){
        return partnerCatalogService.getProductsByTag(inventory, tags);
    }
}