import java.util.ArrayList;


public class Tester {

	public static void main(String[] args) {
	NewsFeed s = new NewsFeed("feed.x");
	s.updateFeed();
	ArrayList<News> result = new ArrayList<News>();
	result=s.getFeed();
	for(News i : result)
		System.out.println(i);
	}
}
