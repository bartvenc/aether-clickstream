package com.aetherstream.aether_clickstream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AnalyticsController() {
        System.out.println("AnalyticsController loaded");
    }


  @GetMapping("/clicks")
  public List<AggregatedClick> getAggregatedClicks(@RequestParam(required = false) String type,
                                                       @RequestParam(required = false, defaultValue = "month") String timeRange) {

      long now = System.currentTimeMillis();
      long threshold = switch (timeRange.toLowerCase()) {
          case "hour" -> now - 60 * 60 * 1000L; // 1 hour
          case "day" -> now - 24 * 60 * 60 * 1000L; // 1 day
          case "week" -> now - 7 * 24 * 60 * 60 * 1000L; // 1 week
          case "month" -> now - 30L * 24 * 60 * 60 * 1000L; // approximate 1 month
          case "year" -> now - 365L * 24 * 60 * 60 * 1000L; // approximate 1 year
          default ->
              // default to week if no match
                  now - 7 * 24 * 60 * 60 * 1000L;
      };


      String sql = "SELECT item_id AS itemId, item_type AS itemType, " +
                   "COUNT(*) AS clickCount " +
                   "FROM clicks " +
                   "WHERE timestamp >= ?";

      // List to hold query parameters
      List<Object> params = new ArrayList<>();

      params.add(threshold);
      System.out.println("threshold: " + threshold);
      System.out.println("sql: " + sql);
      // Add a condition for filtering by type if the parameter is provided
      if (type != null) {
          sql += " AND item_type = ?";
          params.add(type);
      }

      // If no type is provided, execute the query without the type filter
      sql += " GROUP BY item_id, item_type";
      return jdbcTemplate.query(sql,params.toArray(),
              BeanPropertyRowMapper.newInstance(AggregatedClick.class));
  }
}
