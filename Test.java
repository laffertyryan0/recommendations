import recommendations.*;

public class Test{
  public static void main(String[] args){
    Reviewer lisa = new Reviewer("Lisa");
    lisa.addReview("Lady", 2.5);
    lisa.addReview("Snakes", 3.5);
    lisa.addReview("Just", 3.0);
    lisa.addReview("Super",3.5);
    lisa.addReview("You",2.5);
    lisa.addReview("Night",3.0);
    Reviewer gene = new Reviewer("Gene");
    gene.addReview("Lady",3);
    gene.addReview("Snakes",3.5);
    gene.addReview("Just",1.5);
    gene.addReview("Super",5.0);
    gene.addReview("Night",3.0);
    gene.addReview("You",2.5);
    Reviewer michael = new Reviewer("Michael");
    michael.addReview("Lady", 2.5);
    michael.addReview("Snakes", 3.0);
    michael.addReview("Super",3.5);
    michael.addReview("Night",4.0);
    Reviewer claudia = new Reviewer("Claudia");
    claudia.addReview("Snakes",3.5);
    claudia.addReview("Just",3.0);
    claudia.addReview("Super",4.0);
    claudia.addReview("Night",4.5);
    claudia.addReview("You",2.5);
    Reviewer mike = new Reviewer("Mike");
    mike.addReview("Lady", 3.0);
    mike.addReview("Snakes",4.0);
    mike.addReview("Just",2.0);
    mike.addReview("Super",3.0);
    mike.addReview("Night",3);
    mike.addReview("You",2.0);
    Reviewer jack = new Reviewer("Jack");
    jack.addReview("Lady", 3.0);
    jack.addReview("Night",3.0);
    jack.addReview("Snakes", 4);
    jack.addReview("Super",5.0);
    jack.addReview("You",3.5);
    Reviewer toby = new Reviewer("Toby");
    toby.addReview("Snakes",4.5);
    toby.addReview("You", 1.0);
    toby.addReview("Super", 4.0);
    //System.out.println(rv.getRating("Lord of the Rings"));
    /*for(int i = 0;i<rv.bookList().length;i++){
      System.out.println(rv.bookList()[i]);
    }*/
    Recommender rec = new Recommender();
    rec.addReviewer(lisa);
    rec.addReviewer(gene);
    rec.addReviewer(michael);
    rec.addReviewer(claudia);
    rec.addReviewer(mike);
    rec.addReviewer(jack);
    rec.addReviewer(toby);


    System.out.println(rec.recommend(toby));
  }


}
