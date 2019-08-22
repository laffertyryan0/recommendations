package recommendations;

import java.util.HashMap;
import java.util.Map;

public class Reviewer{
  private String name;
  private HashMap<String, Double> reviews;
  public Reviewer(String name, HashMap<String, Double> reviews){
    this.name = name;
    this.reviews = reviews;
  }
  public Reviewer(String name){
    this.name = name;
    this.reviews = new HashMap<String,Double>();
  }
  public String getName(){
    return this.name;
  }
  public Double getRating(String title){
    return this.reviews.get(title);
  }
  public void addReview(String title, double rating){
    reviews.put(title,rating);
  }
  public String[] bookList(){
    String[] s = new String[reviews.size()];
    reviews.keySet().toArray(s);
    return s;
  }
}
