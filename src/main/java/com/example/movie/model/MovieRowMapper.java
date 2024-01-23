/*
 * You can use the following import statements
 *
 * import org.springframework.jdbc.core.RowMapper;
 * import java.sql.ResultSet;
 * import java.sql.SQLException;
 *
 */

// Write your code here
package com.example.movie.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Movie(
                rs.getInt("movieid"),
                rs.getString("moviename"),
                rs.getString("leadactor"));
    }
}