/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.movie.service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.movie.repository.*;
import com.example.movie.model.*;
import java.util.*;

@Service
public class MovieH2Service implements MovieRepository {

  @Autowired
  private JdbcTemplate db;

  @Override
  public ArrayList<Movie> getMovies() {
    List<Movie> mList = db.query("select * from MOVIELIST", new MovieRowMapper());
    ArrayList<Movie> movies = new ArrayList<>(mList);
    return movies;
  }

  @Override
  public Movie getMovieById(int movieId) {
    try {

      Movie book = db.queryForObject("select * from MOVIELIST where movieid = ?", new MovieRowMapper(), movieId);

      return book;
    } catch (Exception e) {

      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
  @Override 
  public Movie addMovie(Movie movie){
    db.update("insert into MOVIELIST(moviename,leadactor) values(?,?)", movie.getMovieName(),movie.getLeadActor());
    Movie savedMovie = db.queryForObject("select * from MOVIELIST where moviename = ? and leadactor = ?", new MovieRowMapper(),movie.getMovieName(),movie.getLeadActor());
    return savedMovie;
  }
  @Override 
   public Movie updateMovie(int movieId,Movie movie) {
      if(movie.getMovieName()!=null){
        db.update("update MOVIELIST set moviename = ? where movieid = ?",movie.getMovieName(),movieId);
      }
      if(movie.getLeadActor()!=null){
        db.update("update MOVIELIST set leadactor = ? where movieid = ?",movie.getLeadActor(),movieId);
      }
      return getMovieById(movieId);
    } 
  @Override 
  public void deleteMovie(int movieId){
    db.update("delete from MOVIELIST where movieid = ?",movieId);
  }

}
