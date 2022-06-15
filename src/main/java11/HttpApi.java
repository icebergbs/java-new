package main.java11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 *   6、标准Java异步HTTP客户端
 * @author bingshan
 * @date 2022/5/30 12:38
 */
public class HttpApi {

    public static void main(String[] args) {
        //JDK11的新的HttpClient支持HTTP/2和WebSocket，并且可以使用异步接口
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).GET().build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, bodyHandler);
        future.thenApply(HttpResponse::body).thenAccept(System.out::println).join();
    }

}
