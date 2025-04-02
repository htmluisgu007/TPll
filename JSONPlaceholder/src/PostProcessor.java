import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class PostProcessor { public static List<Post> filterPosts(List<Post> posts, String keyword) {
    return posts.stream()
            .filter(post -> post.getTitle()
            .toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList()); }

    public static List<Post> sortPosts(List<Post> posts) {
        return posts.stream()
                .sorted(Comparator.comparingInt(Post::getId))
                .collect(Collectors.toList());
    }

    public static Map<Integer, Long> groupPostsByUserId(List<Post> posts) {
        return posts.stream()
                .collect(Collectors.groupingBy(Post::getUserId, Collectors.counting()));
    }

    public static List<String> mapPostTitles(List<Post> posts) {
        return posts.stream()
                .map(Post::getTitle)
                .collect(Collectors.toList());
    }

    public static void printResults(List<Post> sortedPosts, Map<Integer, Long> groupedPosts, List<String> postTitles) {
        System.out.println("Posts filtrados e ordenados:");
        sortedPosts.forEach(post -> System.out.println("ID: " + post.getId() + " - Título: " + post.getTitle()));

        System.out.println("\nContagem de posts por usuário:");
        groupedPosts.forEach((userId, count) -> System.out.println("UserID: " + userId + " - Quantidade de Posts: " + count));

        int sumIds = sortedPosts.stream().mapToInt(Post::getId).sum();
        System.out.println("\nSoma total dos IDs dos posts filtrados: " + sumIds);

        System.out.println("\nTítulos dos posts filtrados:");
        postTitles.forEach(System.out::println);
    }

}
