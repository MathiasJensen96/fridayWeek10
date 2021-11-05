package rest;

import com.google.gson.Gson;
import dto.ChuckDTO;
import dto.OurDTO;
import dto.DadDTO;
import jokefetcher.JokeFetcher;
import utils.HttpUtils;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * REST Web Service
 *
 * @author lam
 */
@Path("jokes")
public class JokeResource {

    @Context
    private UriInfo context;

   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokes() throws IOException, ExecutionException, InterruptedException {
        Gson gson = new Gson();
        //String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        //ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);
        //String dad = HttpUtils.fetchData("https://icanhazdadjoke.com");
        //DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);
        //OurDTO combinedDTO = new OurDTO(chuckDTO,daCCcccccdDTO);
        OurDTO dataFeched = JokeFetcher.runParrallel();
        //DadDTO dadDTO = new DadDTO()
        String combinedJSON = gson.toJson(dataFeched);
        return combinedJSON;
    }
}
