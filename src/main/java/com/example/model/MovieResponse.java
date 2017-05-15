package com.example.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by trainer9 on 5/15/17.
 */
public class MovieResponse {

    List<HashMap<String,Object>> Search;

    String totalResults;
    String Response;

    @JsonPropertyOrder({ "Search", "totalResults", "Response"})
    public MovieResponse(List<HashMap<String, Object>> Search, String totalResults, String Response) {
        this.Search = Search;
        this.totalResults = totalResults;
        Response = Response;
    }

    public MovieResponse() {
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    @JsonGetter("search")
    public List<HashMap<String, Object>> getSearch() {
        return Search;
    }

    @JsonSetter("Search")
    public void setSearch(List<HashMap<String, Object>> Search) {
        this.Search = Search;
    }
}
