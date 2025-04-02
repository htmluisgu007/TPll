import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI; import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;

public class Main { public static void main(String[] args) {
    String url = "https://jsonplaceholder.typicode.com/posts";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

    try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonResponse = response.body();

        List<Post> posts = new
                Gson().fromJson(jsonResponse, new
                TypeToken<List<Post>>()
                {}.getType());

        List<Post> filteredPosts = PostProcessor
                .filterPosts(posts, "qui");
        List<Post> sortedPosts = PostProcessor
                .sortPosts(filteredPosts);
        Map<Integer, Long> groupedPosts = PostProcessor
                .groupPostsByUserId(sortedPosts);
        List<String> postTitles = PostProcessor
                .mapPostTitles(sortedPosts);

        PostProcessor.printResults(sortedPosts, groupedPosts, postTitles);
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
}

}
 