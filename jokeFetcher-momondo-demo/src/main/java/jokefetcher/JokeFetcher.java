package jokefetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.google.gson.Gson;
import dto.ChuckDTO;
import dto.DadDTO;
import dto.OurDTO;
import utils.HttpUtils;


public class JokeFetcher {

    static class PingChuck implements Callable<ChuckDTO> {
        String url;
        PingChuck(String url) {
            this.url = url;
        }
        @Override
        public ChuckDTO call() throws Exception {
            Gson gson = new Gson();
            String joke = HttpUtils.fetchData(url);
            ChuckDTO chuckDTO = gson.fromJson(joke, ChuckDTO.class);
            return chuckDTO;
        }
    }

    static class PingDad implements Callable<DadDTO> {
        String url;
        PingDad(String url) {
            this.url = url;
        }
        @Override
        public DadDTO call() throws Exception {
            Gson gson = new Gson();
            String joke = HttpUtils.fetchData(url);
            DadDTO dadDTO = gson.fromJson(joke, DadDTO.class);
            return dadDTO;
        }
    }

    public static OurDTO runParrallel() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<String> urls = new ArrayList<>();
        urls.add("https://api.chucknorris.io/jokes/random");
        urls.add("https://icanhazdadjoke.com");


        Future future1 = executor.submit(new PingChuck(urls.get(0)));
        System.out.println(future1.get());
        Future future2 = executor.submit(new PingDad(urls.get(1)));

        OurDTO jokes = new OurDTO((ChuckDTO) future1.get(), (DadDTO) future2.get());
//
//        for (Future<String> future : futures) {
//            String result = future.get();
//            statusList.add(result);
//        }
//        return statusList;

        return jokes;
    }




    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {


        String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        String dad = HttpUtils.fetchData("https://icanhazdadjoke.com");
        
        System.out.println("JSON fetched from chucknorris:");
        System.out.println(chuck);
        System.out.println("JSON fetched from dadjokes:");
        System.out.println(dad);
        
       runParrallel();
    }
}
