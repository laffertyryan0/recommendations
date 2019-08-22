package recommendations;

import java.util.ArrayList;
import java.util.HashMap;

public class Recommender{
  private ArrayList<Reviewer> reviewers;
  private String distFunction = "pearson";
  public Recommender(){
    reviewers = new ArrayList<Reviewer>();

  }
  public void addReviewer(Reviewer r){
    this.reviewers.add(r);
  }

  //maybe rewrite this better later
  public String recommend(Reviewer r){
    HashMap<String,Double> totals = new HashMap<String,Double>();
    HashMap<String,Double> distSums = new HashMap<String,Double>();
    if(!reviewers.contains(r)){
      return null;
    }
    double dist;
    for(Reviewer reviewer:reviewers){
      if(!reviewer.equals(r)){
        dist = distance(r,reviewer);
        //if dist is less than or equal to 0, ignore
        if(dist<=0);
        else{
          for(String book:reviewer.bookList()){
            //note.. see book here
            boolean readalready = false;
            for(String rbook:r.bookList()){
              if(rbook.equals(book)){
                readalready = true;
              }
            }
            if(!readalready){
              //Find out if book already has a computed value.
              //If not, add it to totals with a value of 0.
              boolean inTotals = false;
              String[] s = new String[totals.size()];
              s = totals.keySet().toArray(s);
              for(String tbook:s){
                if(tbook.equals(book)) inTotals = true;
              }
              if(!inTotals){
                totals.put(book,0.0);
              }
              //same thing for distSums
              boolean inDistSums = false;
              String[] s2;
              s2 = distSums.keySet().toArray(s);
              for(String tbook:s2){
                if(tbook.equals(book)) inDistSums = true;
              }
              if(!inDistSums){
                distSums.put(book,0.0);
              }
              //Actual calculations
              totals.put(book,totals.get(book)+reviewer.getRating(book)*dist);
              distSums.put(book,distSums.get(book)+dist);
          }
        }
      }
      }
    }
    //compute normalized list
    if(totals.isEmpty()){
      return null;
    }
    String[] totalskeys = new String[totals.size()];
    totalskeys = totals.keySet().toArray(totalskeys);
    String bestRanked = totalskeys[0];
    for(String book:totalskeys){
      if(totals.get(book)/distSums.get(book)>=totals.get(bestRanked)/distSums.get(bestRanked)){
        bestRanked = book;
      }
    }
    return bestRanked;
  }

  public double distance(Reviewer r1, Reviewer r2){
    if(distFunction.equals("euclid")) return euclidean(r1,r2);
    if(distFunction.equals("pearson")) return pearson(r1,r2);
    //pearson default
    return pearson(r1,r2);
  }

  private static double euclidean(Reviewer r1, Reviewer r2){
    //Get list of shared items
    ArrayList<String> shared = new ArrayList<>();
    for(String book1:r1.bookList()){
      for(String book2:r2.bookList()){
        if(book1.equals(book2)) shared.add(book1);
      }
    }
    //Return 0 if nothing in common
    if(shared.size()==0){
      return 0;
    }
    //
    double sumsquare = 0;
    for(String s:shared){
      sumsquare += Math.pow(r1.getRating(s)-r2.getRating(s),2);
    }
    return 1/(1+Math.sqrt(sumsquare));
  }
  private static double pearson(Reviewer r1, Reviewer r2){
    //Get list of shared items
    ArrayList<String> shared = new ArrayList<>();
    for(String book1:r1.bookList()){
      for(String book2:r2.bookList()){
        if(book1.equals(book2)) shared.add(book1);
      }
    }
    //get number of elements in shared
    double num_elem = shared.size();
    //Return 0 if nothing in common
    if(num_elem==0){
      return 0;
    }
    //compute ingredients for pearson correlation
    double sum1 = 0;
    double sum2 = 0;
    double pSum = 0;
    double sqSum1 = 0;
    double sqSum2 = 0;
    for(String s:shared){
      sum1 += r1.getRating(s);
      sqSum1 += Math.pow(r1.getRating(s),2);
      sum2 += r2.getRating(s);
      sqSum2 += Math.pow(r2.getRating(s),2);
      pSum += r1.getRating(s)*r2.getRating(s);
    }
    double numer = pSum-(sum1*sum2/num_elem);
    double denom = Math.sqrt((sqSum1-Math.pow(sum1,2)/num_elem)*(sqSum2-Math.pow(sum2,2)/num_elem));
    if(denom == 0){
      return 0;
    }
    return numer/denom;
  }
}
