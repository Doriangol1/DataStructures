package Lab01;

import javax.lang.model.util.ElementScanner6;

public class Lab01Debug {
    public static void main(String[] args) {
      System.out.println("Welcome to Lab01");
    }

    // This method returns a string representing 
    // the actor selected.
    public String selectActor(int option) {
        String actor = new String();
        switch(option) {
        case 1:
          actor = "Brie Larson";
          break;
        case 2:
          actor = "Chadwick Boseman";
          break;
        case 3:
          actor = "Chris Hemsworth";
          break;
        case 4:
          actor = "Mark Ruffalo";
          break;
        case 5:
          actor = "Tom Holland";
          break;
        default:
          actor = "Invalid option";
          break;
        }
        return actor;
    }
	  
    // This method returns a string representing
    // the specific movie selected and the associated year of release
    public String selectMovie(int option) {
        String movie = null;
        if(option==1){
          movie = "Guardians of the Galaxy";
          movie += " (2014)";
        }
        else if(option==2){
          movie = "Black Panther";
          movie += " (2018)";
        }
        else if(option==3){
          movie = "Captain Marvel";
          movie += " (2019)";
        }
        else if(option==4){
          movie = "Spider-Man: Far From Home";
          movie += " (2019)";
        }
        else if(option==5){
          movie = "Shang-Chi and the Legend of the Ten Rings";
          movie += " (2021)";
        }
        else
          movie = "Does not exist";
        return movie;
    }

    // This method returns a boolean where true represents
    // the movie is available for purchase (i.e. in stock)
    public Boolean isAvengerMovie(String movie) {
      String[] avengerMovie = {"The Avengers", "Avengers: Age of Ultron", "Avengers: Infinity War", "Avengers: Endgame"};
      int i;
      for(i=0;i<avengerMovie.length;i++) {
        if(movie.equals(avengerMovie[i]))
          return true;
      }
      return false;
  }
	  
  // This method performs a circular left shift of elements
  // stored in arr. Specifically, the first element is shifted
  // to the last position, and all other elements are shifted
  // to the left by one position. The shifted array is returned.
  public void circular_left_shift(String[] arr) {
      int i;
     String temp = arr[0];
      for(i=0;i<arr.length-1;i++) {
        arr[i] = arr[i+1];
      }
      arr[arr.length-1] = temp;  // shift original first element to last
  }

}
