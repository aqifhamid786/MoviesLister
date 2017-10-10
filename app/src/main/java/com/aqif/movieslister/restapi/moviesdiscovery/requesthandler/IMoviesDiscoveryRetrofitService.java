package com.aqif.movieslister.restapi.moviesdiscovery.requesthandler;


import com.aqif.movieslister.restapi.moviesdiscovery.responsedao.MoviesDiscoveryDAO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface IMoviesDiscoveryRetrofitService
{

	@GET("discover/movie")
	Call<MoviesDiscoveryDAO> discoverMoviesWithRange(
			@Query("api_key") String apiKey,
			@Query("language") String language,
			@Query("include_adult") boolean includeAdult,
			@Query("include_video") boolean includeVideo,
			@Query("page") int pageNumber,
			@Query("release_date.gte") String releaseFilterStartDate,
			@Query("release_date.lte") String releaseFilterEndDate
	);

	@GET("discover/movie")
	Call<MoviesDiscoveryDAO> discoverMovies(
			@Query("api_key") String apiKey,
			@Query("language") String language,
			@Query("include_adult") boolean includeAdult,
			@Query("include_video") boolean includeVideo,
			@Query("page") int pageNumber
	);

}
