package webscraper;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Tester {

    static class PingURL implements Callable<TagCounter> {
        String url;
        PingURL(String url) {
            this.url = url;
        }
        @Override
        public TagCounter call() throws Exception {
            TagCounter tc = new TagCounter(url);
            tc.doWork();
            return tc;
        }
    }

    public static List<TagCounter> runSequental() {
        List<TagCounter> urls = new ArrayList();
        urls.add(new TagCounter("https://www.fck.dk"));
        urls.add(new TagCounter("https://www.google.com"));
        urls.add(new TagCounter("https://politiken.dk"));
        urls.add(new TagCounter("https://cphbusiness.dk"));
        for (TagCounter tc : urls) {
            tc.doWork();
        }
        return urls;
    }

    public static List<TagCounter> runParrallel() throws ExecutionControl.NotImplementedException, ExecutionException, InterruptedException {
        //throw new ExecutionControl.NotImplementedException("Pleeeeease implement me!");
        ExecutorService executor = Executors.newCachedThreadPool();
        List<TagCounter> urls = new ArrayList<>();
        urls.add(new TagCounter("https://www.fck.dk"));
        urls.add(new TagCounter("https://www.google.com"));
        urls.add(new TagCounter("https://politiken.dk"));
        urls.add(new TagCounter("https://cphbusiness.dk"));

        List<Future<TagCounter>> futures = new ArrayList<>();
        List<TagCounter> statusList = new ArrayList<>();
        for (TagCounter url : urls) {
            Future future = executor.submit(new PingURL(url.getUrl()));
            futures.add(future);
        }

        for (Future<TagCounter> future : futures) {
            TagCounter result = future.get();
            statusList.add(result);
        }
        return statusList;
    }

    public static void main(String[] args) throws Exception {
        long timeSequental;
        long start = System.nanoTime();

        List<TagCounter> fetchedData = new Tester().runSequental();
        long end = System.nanoTime();
        timeSequental = end - start;
        System.out.println("Time Sequential: " + ((timeSequental) / 1_000_000) + " ms.");

        for (TagCounter tc : fetchedData) {
            System.out.println("Title: " + tc.getTitle());
            System.out.println("Div's: " + tc.getDivCount());
            System.out.println("Body's: " + tc.getBodyCount());
            System.out.println("----------------------------------");
        }

        start = System.nanoTime();
        runParrallel();
        long timeParallel = System.nanoTime() - start;
        System.out.println("Time Parallel: " + ((timeParallel) / 1_000_000) + " ms.");
        System.out.println("Paralle was " + timeSequental / timeParallel + " times faster");

    }
}

// TODO: Det samme eksempel med threads
//
//static class PingURL implements Runnable {
//    String url;
//    PingURL(String url) {
//        this.url = url;
//    }
//    @Override
//    public void run() {
//        TagCounter tc = new TagCounter(url);
//        tc.doWork();
//        //return null;
//    }
//}
//
//    public static List<TagCounter> runSequental() {
//        List<TagCounter> urls = new ArrayList();
//        urls.add(new TagCounter("https://www.fck.dk"));
//        urls.add(new TagCounter("https://www.google.com"));
//        urls.add(new TagCounter("https://politiken.dk"));
//        urls.add(new TagCounter("https://cphbusiness.dk"));
//        for (TagCounter tc : urls) {
//            tc.doWork();
//        }
//        return urls;
//    }

//    public static List<TagCounter> runParrallel() throws ExecutionControl.NotImplementedException, ExecutionException, InterruptedException {
//        //throw new ExecutionControl.NotImplementedException("Pleeeeease implement me!");
//        ExecutorService executor = Executors.newCachedThreadPool();
//        List<TagCounter> urls = new ArrayList<>();
//        urls.add(new TagCounter("https://www.fck.dk"));
//        urls.add(new TagCounter("https://www.google.com"));
//        urls.add(new TagCounter("https://politiken.dk"));
//        urls.add(new TagCounter("https://cphbusiness.dk"));
//
//        List<Future<TagCounter>> futures = new ArrayList<>();
//        List<TagCounter> statusList = new ArrayList<>();
//        for (TagCounter url : urls) {
//            Future future = executor.submit(new Tester.PingURL(url.getUrl()));
//            futures.add(future);
//        }
//        for (TagCounter url : urls) {
//            new Thread().start();
//
//        }
//
//        for (Future<TagCounter> future : futures) {
//            TagCounter result = future.get();
//            statusList.add(result);
//        }
//        return statusList;
//    }