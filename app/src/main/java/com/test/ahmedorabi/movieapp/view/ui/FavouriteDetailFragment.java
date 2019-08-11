package com.test.ahmedorabi.movieapp.view.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.FragmentDetailActivityBinding;
import com.test.ahmedorabi.movieapp.di.Injectable;
import com.test.ahmedorabi.movieapp.repository.data.MovieType;
import com.test.ahmedorabi.movieapp.api.Status;
import com.test.ahmedorabi.movieapp.repository.data.TvGenresResponse.Network;
import com.test.ahmedorabi.movieapp.repository.data.backdropsModel.Backdrop;
import com.test.ahmedorabi.movieapp.repository.data.creditsModel.Cast;
import com.test.ahmedorabi.movieapp.repository.data.creditsModel.Crew;
import com.test.ahmedorabi.movieapp.repository.data.genresmodel.Genre;
import com.test.ahmedorabi.movieapp.repository.data.genresmodel.SpokenLanguage;
import com.test.ahmedorabi.movieapp.repository.data.reviewModel.Result;
import com.test.ahmedorabi.movieapp.repository.data.trailermodel.TrailerResult;
import com.test.ahmedorabi.movieapp.repository.db.Movie;
import com.test.ahmedorabi.movieapp.view.adapter.MyRecyclerViewAdapter;
import com.test.ahmedorabi.movieapp.view.adapter.MyRecyclerViewTrailerAdapter;
import com.test.ahmedorabi.movieapp.view.adapter.SimilarMovieAdapter;
import com.test.ahmedorabi.movieapp.view.adapter.SimilarTvAdapter;
import com.test.ahmedorabi.movieapp.view.callback.CastCallback;
import com.test.ahmedorabi.movieapp.view.callback.SimilarMovieCallback;
import com.test.ahmedorabi.movieapp.view.callback.SimilarTVCallback;
import com.test.ahmedorabi.movieapp.view.callback.TrailerCallBack;
import com.test.ahmedorabi.movieapp.viewmodel.DetailActivityViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

public class FavouriteDetailFragment extends Fragment implements Injectable {


    public TrailerCallBack mCallback = key -> {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + key));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    };
    String year;
    String seasons;
    FragmentDetailActivityBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private float width;
    private String tomatoesRating;
    private String facebookId, twitter, instagram, imdbId;
    private int id_movie;
    private String title, imageurl, overview, release_date, type, language, backdrop_path;
    private double vote_average;
    private long voteCount;
    private MyRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView_trailer, recyclerView_similar, recyclerView;
    private MyRecyclerViewTrailerAdapter myRecyclerViewTrailerAdapter;
    private List<Result> reviewList;
    private ViewPagerAdapter pagerAdapter;
    private List<Backdrop> backdropList;
    private List<Cast> castList;
    private SimilarMovieAdapter similarMovieAdapter;
    private FavDetailFragmentListener mListener;
    public CastCallback castCallback = new CastCallback() {
        @Override
        public void onActorClick(int id, String name) {
            mListener.onFavDetailActorFinish(id, name);

        }
    };
    public SimilarMovieCallback movieCallback = new SimilarMovieCallback() {
        @Override
        public void onClickSimilarMovie(com.test.ahmedorabi.movieapp.repository.data.similar.Result item) {
            mListener.onSimilarFavFragmentFinish(item.getId(), item.getTitle(), item.getPosterPath(), item.getOverview(), item.getReleaseDate(),
                    item.getVoteAverage(), item.getBackdropPath(), item.getOriginalLanguage(), "movie", item.getVoteCount());

        }
    };
    public SimilarTVCallback tvCallback = item -> mListener.onSimilarFavFragmentFinish(item.getId(), item.getName(), item.getPosterPath(), item.getOverview(), item.getFirstAirDate(),
            item.getVoteAverage(), item.getBackdropPath(), item.getOriginalLanguage(), "tv", item.getVoteCount());
    private List<com.test.ahmedorabi.movieapp.repository.data.similar.Result> similarList;
    private List<com.test.ahmedorabi.movieapp.repository.data.similarTvModel.Result> similarTvList;
    private SimilarTvAdapter tvAdapter;

    DetailActivityViewModel viewModel;
    private boolean isFav = false;

    public FavouriteDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof FavDetailFragmentListener)) throw new AssertionError();
        mListener = (FavDetailFragmentListener) context;

    }


    @SuppressLint({"Recycle", "SetTextI18n"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_activity, container, false);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel.class);


        Intent intent = Objects.requireNonNull(getActivity()).getIntent();

        if (intent != null) {
            id_movie = intent.getIntExtra("id", 0);
            title = intent.getStringExtra("title");
            imageurl = intent.getStringExtra("image");
            overview = intent.getStringExtra("overview");
            release_date = intent.getStringExtra("release_date");
            vote_average = intent.getDoubleExtra("vote_average", 0.0);
            backdrop_path = intent.getStringExtra("backdrop_path");
            type = intent.getStringExtra(MainActivity.TYPE);
            language = intent.getStringExtra("language");
            voteCount = intent.getLongExtra("count", 0);
        }


        Bundle arguments = getArguments();

        if (arguments != null) {
            id_movie = arguments.getInt("id");
            title = arguments.getString("title");
            imageurl = arguments.getString("image");
            overview = arguments.getString("overview");
            release_date = arguments.getString("release_date");
            vote_average = arguments.getDouble("vote_average");
            backdrop_path = arguments.getString("backdrop_path");
            type = arguments.getString(MainActivity.TYPE);
            language = arguments.getString("language");
            voteCount = arguments.getLong("count");


        }

        binding.collapsingToolbar.setTitle(title);

        String baseUrl = "http://image.tmdb.org/t/p/w500";
        String finalUrl = baseUrl + imageurl;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Glide.with(Objects.requireNonNull(getActivity()).getApplicationContext()).load(finalUrl).into(binding.fileImage);
        }

        binding.overviewTv.setText(overview);

        binding.VoteTv.setText(vote_average + "");

        switch (language) {
            case "en":
                binding.tvLanguage.append("Original Language : " + "English");
                break;
            case "hi":
                binding.tvLanguage.append("Original Language : " + "Hindi");
                break;
            case "fr":
                binding.tvLanguage.append("Original Language : " + "French");
                break;
            case "ja":
                binding.tvLanguage.append("Original Language : " + "Japanese");
                break;
            case "es":
                binding.tvLanguage.append("Original Language : " + "Spanish");
                break;
            case "ko":
                binding.tvLanguage.append("Original Language : " + "Korean");
                break;
            case "de":
                binding.tvLanguage.append("Original Language : " + "German");
                break;
            case "el":
                binding.tvLanguage.append("Original Language : " + "Greek");
                break;
            case "ar":
                binding.tvLanguage.append("Original Language : " + "Arabic");
                break;
        }


        initViews();
        fixWidth();
        checkFavList();


        if (type.equals(MoviesFragment.MOVIE_VALUE)) {
            binding.allSeasonBTN.setVisibility(View.GONE);


        } else {
            binding.imageViewTomatoes.setVisibility(View.GONE);

        }


        binding.imageViewInsta.setOnClickListener(v -> {
            String url = "http://instagram.com/_u/" + instagram;

            final Intent intent12 = new Intent(Intent.ACTION_VIEW);
            try {
                if (Objects.requireNonNull(getActivity()).getPackageManager().getPackageInfo("com.instagram.android", 0) != null) {
                    intent12.setData(Uri.parse("http://instagram.com/_u/" + instagram));
                    intent12.setPackage("com.instagram.android");
                    startActivity(intent12);
                }
            } catch (PackageManager.NameNotFoundException ignored) {
            }
            intent12.setData(Uri.parse(url));
            startActivity(intent12);
        });

        binding.allSeasonBTN.setOnClickListener(v -> {
            Intent intent13 = new Intent(getActivity(), SeasonsActivity.class);
            intent13.putExtra("seasons", seasons);
            intent13.putExtra("idTV", id_movie);
            intent13.putExtra("imdbIID", imdbId);
            startActivity(intent13);
        });

        binding.imageViewImadb.setOnClickListener(v -> {

            try {
                Intent intent15 = new Intent(Intent.ACTION_VIEW, Uri.parse("imdb:///title/" + imdbId));
                startActivity(intent15);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "IMDB app not found", Toast.LENGTH_SHORT).show();
            }


        });

        binding.imageViewTwitter.setOnClickListener(v -> {
            Intent intent14;

            try {
                Objects.requireNonNull(getActivity()).getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent14 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("twitter://user?screen_name=" + twitter));
                intent14.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } catch (Exception e) {
                intent14 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + twitter));

            }

            startActivity(intent14);
        });

        binding.imageViewFace.setOnClickListener(v -> {

            Intent intent1;

            try {
                Objects.requireNonNull(getActivity()).getPackageManager().getPackageInfo("com.facebook.katana", 0);
                intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + facebookId));
            } catch (Exception e) {
                intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + facebookId));
            }

            startActivity(intent1);

        });

        binding.readMoreImageView.setOnClickListener(v -> mListener.onFavDetailFragmentFinish(id_movie, type));

        binding.fileImage.setOnClickListener(v -> mListener.DisplayPosterFav(id_movie, type));


        return binding.getRoot();

    }

    private void NetworkFailure() {
        Toast.makeText(getActivity(), "network failure.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getAllMovies().observe(this, movies -> {
            for (Movie movie : movies) {
                if (title.equals(movie.getTitle())) {
                    binding.likeFab.setImageResource(R.drawable.ic_heart_white_48dp);
                    isFav = true;
                    Log.e("detail", "sate " + isFav);
                    break;
                }
            }
        });


        viewModel.setMovieType(new MovieType(id_movie, type));
        binding.setViewModel(viewModel);
        observeViewModel(viewModel);

    }

    @SuppressLint("SetTextI18n")
    private void observeViewModel(final DetailActivityViewModel viewModel) {

        if (type.equals(MoviesFragment.MOVIE_VALUE)) {
            viewModel.getMoviesGenresObservable().observe(this, response -> {
                assert response != null;
                if (response.status == Status.ERROR || response.data == null) {
                    NetworkFailure();

                } else {

                    List<Genre> genreList = response.data.getGenres();
                    if (genreList.size() == 0) {
                        binding.genresTv.setText("no genres for this movie");
                    } else {
                        genreList.size();
                        if (genreList.size() == 1) {
                            binding.genresTv.setText(genreList.get(0).getName());
                        } else if (genreList.size() == 2) {
                            binding.genresTv.setText(genreList.get(0).getName() + ", " + genreList.get(1).getName());
                        } else {
                            binding.genresTv.setText(genreList.get(0).getName() + ", " + genreList.get(1).getName() + ", " + genreList.get(2).getName());
                        }
                    }


                    if (type.equals(MoviesFragment.MOVIE_VALUE)) {
                        int hour;
                        int min;

                        if (response.data.getRuntime() == null) {
                            binding.tvTime.setText("0min");
                        } else {
                            long time = response.data.getRuntime();
                            if (time < 120) {
                                hour = (int) (time / 60);
                                min = (int) (time - 60);
                            } else {
                                hour = (int) (time / 60);
                                min = (int) (time - 120);
                            }

                            binding.tvTime.setText(hour + "h " + min + "min");

                        }


                        String releaseDate = response.data.getReleaseDate();
                        if (!TextUtils.isEmpty(releaseDate)) {
                            String date = formatDate(releaseDate);
                            binding.releaseDateTv.setText("Release Date: " + date);
                        } else {
                            binding.releaseDateTv.setText("Release Date: " + "N/A");

                        }


                        if (response.data.getProductionCompanies().size() == 0) {
                            binding.companyNameTv.setText("Company Name: " + "N/A");
                            binding.originCountryTv.setText("Origin Country: " + "N/A");
                        } else {
                            String companyName = response.data.getProductionCompanies().get(0).getName();
                            String country = response.data.getProductionCompanies().get(0).getOriginCountry();
                            binding.companyNameTv.setText("Company Name: " + companyName);
                            binding.originCountryTv.setText("Origin Country: " + country);
                        }

                        if (response.data.getProductionCountries().size() == 0) {
                            binding.productionCountryTv.setText("Production Country: " + "N/A");
                        } else {
                            String productionCountry = response.data.getProductionCountries().get(0).getName();
                            binding.productionCountryTv.setText("Production Country: " + productionCountry);

                        }


                        long budget = response.data.getBudget();
                        binding.budgetTv.setText("Budget: $" + budget);


                        long revenue = response.data.getRevenue();
                        binding.revenueTv.setText("Revenue: $" + revenue);

                        String status = response.data.getStatus();
                        if (!TextUtils.isEmpty(status)) {
                            binding.statusTv.setText("Status: " + status);
                        } else {
                            binding.statusTv.setText("Status: " + "N/A");
                        }


                        String tagLine = response.data.getTagline();
                        if (!TextUtils.isEmpty(tagLine)) {
                            binding.tagLineTv.setText("Tagline: " + tagLine);
                        } else {
                            binding.tagLineTv.setText("Tagline: " + "N/A");
                        }


                        List<SpokenLanguage> languageList = response.data.getSpokenLanguages();

                        int x = 1;
                        for (SpokenLanguage item : languageList) {
                            if (x == 1) {
                                binding.spokenLanguageTv.setText("Spoken Language: " + item.getName());
                                x = 2;
                            } else {
                                binding.spokenLanguageTv.append(", " + item.getName());

                            }
                        }


                    }
                }


            });

            viewModel.getSimilarResponseLiveData().observe(this, similarResponseResource -> {

                assert similarResponseResource != null;
                if (similarResponseResource.status == Status.ERROR || similarResponseResource.data == null) {
                    NetworkFailure();
                } else {
                    similarList = similarResponseResource.data.getResults();
                    if (similarList != null) {
                        if (similarList.size() > 0) {
                            similarMovieAdapter = new SimilarMovieAdapter(getActivity(), similarList, movieCallback);
                            recyclerView_similar.setAdapter(similarMovieAdapter);
                        } else {
                            binding.cardViewSimilar.setVisibility(View.GONE);
                        }
                    }
                }
            });
        }

        if (type.equals(TVShowFragemnt.TV_VALUE)) {

            viewModel.getTvResponseLiveData().observe(this, response -> {
                assert response != null;
                if (response.status == Status.ERROR || response.data == null) {
                    NetworkFailure();
                } else {
                    similarTvList = response.data.getResults();
                    if (similarTvList != null) {
                        if (similarTvList.size() > 0) {
                            tvAdapter = new SimilarTvAdapter(getActivity(), similarTvList, tvCallback);
                            recyclerView_similar.setAdapter(tvAdapter);
                        } else {
                            binding.cardViewSimilar.setVisibility(View.GONE);
                        }
                    }
                }

            });

            viewModel.getTvGenresObservable().observe(this, response -> {
                assert response != null;
                if (response.status == Status.ERROR || response.data == null) {
                    NetworkFailure();

                } else {
                    List<com.test.ahmedorabi.movieapp.repository.data.TvGenresResponse.Genre> list = response.data.getGenres();

                    if (list.size() == 0) {
                        binding.genresTv.setText("no genres for this movie");

                    } else {
                        list.size();
                        if (list.size() == 1) {
                            binding.genresTv.setText(list.get(0).getName());
                        } else if (list.size() == 2) {
                            binding.genresTv.setText(list.get(0).getName() + ", " + list.get(1).getName());
                        } else {
                            binding.genresTv.setText(list.get(0).getName() + ", " + list.get(1).getName() + ", " + list.get(2).getName());
                        }
                    }

                    String firstAireDate = response.data.getFirstAirDate();
                    List<String> language = response.data.getLanguages();
                    List<String> originCountryList = response.data.getOriginCountry();
                    String status = response.data.getStatus();
                    String lastAirDate = response.data.getLastAirDate();


                    if (response.data.getNumberOfEpisodes() == null) {
                        binding.budgetTv.setText("Number of episodes: " + "N/A");
                    } else {
                        long episodes = response.data.getNumberOfEpisodes();
                        binding.budgetTv.setText("Number of episodes: " + episodes);

                    }

                    String type = response.data.getType();

                    if (response.data.getEpisodeRunTime() == null) {
                        binding.tagLineTv.setText("Episode run Time: " + "N/A");
                    } else {
                        if (response.data.getEpisodeRunTime().size() != 0) {
                            List<Long> runTimelist = response.data.getEpisodeRunTime();
                            binding.tagLineTv.setText("Episode run Time: " + runTimelist.get(0));
                        }

                    }


                    if (TextUtils.isEmpty(firstAireDate)) {
                        binding.releaseDateTv.setText("First Air Date: " + "N/A");

                    } else {
                        String date = formatDate(firstAireDate);
                        binding.releaseDateTv.setText("First Air Date: " + date);

                    }


                    if (TextUtils.isEmpty(lastAirDate)) {
                        binding.productionCountryTv.setText("Last Air Date: " + "N/A");

                    } else {
                        String lastDate = formatDate(lastAirDate);
                        binding.productionCountryTv.setText("Last Air Date: " + lastDate);

                    }

                    binding.revenueTv.setText("Type: " + type);
                    binding.statusTv.setText("Status: " + status);

                    int x = 1;
                    for (String item : language) {
                        if (x == 1) {
                            binding.spokenLanguageTv.setText("Languages: " + item);
                            x = 2;
                        } else {
                            binding.spokenLanguageTv.append(", " + item);

                        }
                    }

                    if (response.data.getNetworks() == null) {
                        binding.companyNameTv.setText("Network: " + "N/A");

                    } else {
                        if (response.data.getNetworks().size() != 0) {
                            List<Network> networkList = response.data.getNetworks();
                            binding.companyNameTv.setText("Network: " + networkList.get(0).getName());
                        } else {
                            binding.companyNameTv.setText("Network: " + "N/A");

                        }


                    }

                    int y = 1;
                    for (String item : originCountryList) {
                        if (y == 1) {
                            binding.originCountryTv.setText("Origin Country: " + item);
                            y = 2;
                        } else {
                            binding.originCountryTv.append(", " + item);

                        }
                    }
                }


            });
        }

        viewModel.getBackdropsObservable().observe(this, backdropsModelResource -> {
            assert backdropsModelResource != null;
            if (backdropsModelResource.status == Status.ERROR || backdropsModelResource.data == null) {
                NetworkFailure();
            } else {
                backdropList = backdropsModelResource.data.getBackdrops();
                if (backdropList != null) {
                    if (backdropList.size() > 0) {
                        pagerAdapter = new ViewPagerAdapter(getActivity());
                        binding.backdropViewPager.setAdapter(pagerAdapter);
                        binding.tabDots.setupWithViewPager(binding.backdropViewPager, true);
                    }
                }
            }
        });


        viewModel.getReviewResponseLiveData().observe(this, reviewResponseResource -> {
            assert reviewResponseResource != null;
            if (reviewResponseResource.status == Status.ERROR || reviewResponseResource.data == null) {
                NetworkFailure();
            } else {
                reviewList = reviewResponseResource.data.getResults();
                if (reviewList != null) {
                    if (reviewList.size() > 0) {
                        Result mReviewItem = reviewList.get(0);
                        viewModel.setReveiwItem(mReviewItem);

                    } else {
                        binding.cardViewReviews.setVisibility(View.GONE);
                    }
                }
            }
        });


        viewModel.getTrailerObservable().observe(this, trailerResponseResource -> {
            assert trailerResponseResource != null;
            if (trailerResponseResource.status == Status.ERROR || trailerResponseResource.data == null) {
                NetworkFailure();
            } else {
                List<TrailerResult> trailerList = trailerResponseResource.data.getResults();
                if (trailerList != null) {
                    if (trailerList.size() > 0) {
                        myRecyclerViewTrailerAdapter = new MyRecyclerViewTrailerAdapter(trailerList, Objects.requireNonNull(getActivity()).getApplicationContext(), mCallback);
                        recyclerView_trailer.setAdapter(myRecyclerViewTrailerAdapter);
                    }
                }
            }

        });


        viewModel.getCastObservable().observe(this, response -> {

            assert response != null;
            if (response.status == Status.ERROR || response.data == null) {
                NetworkFailure();

            } else {
                castList = response.data.getCast();
                List<Crew> crewList = response.data.getCrew();
                if (response.data.getCast() == null) {
                    recyclerView.setVisibility(View.GONE);
                } else {
                    if (castList.size() > 0) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            recyclerViewAdapter = new MyRecyclerViewAdapter(castList, Objects.requireNonNull(getActivity()).getApplicationContext(), castCallback);
                            recyclerView.setAdapter(recyclerViewAdapter);

                        }
                    }
                }

                if (type.equals(MoviesFragment.MOVIE_VALUE)) {
                    int x = 1;
                    int y = 1;
                    int z = 1;
                    for (Crew item : crewList) {
                        if (item.getDepartment().equals("Directing")) {
                            if (x == 1) {
                                binding.tvDirector.setText(item.getName());
                                x = 2;
                            } else {
                                binding.tvDirector.append(", " + item.getName());

                            }

                        }

                        if (item.getDepartment().equals("Writing")) {
                            if (y == 1) {
                                binding.tvWriter.setText(item.getName());
                                y = 2;
                            } else {
                                binding.tvWriter.append(", " + item.getName());

                            }
                        }

                        if (item.getDepartment().equals("Production")) {
                            if (z == 1) {
                                binding.tvProduction.setText(item.getName());
                                z = 2;

                            } else {
                                binding.tvProduction.append(", " + item.getName());

                            }
                        }

                    }
                } else {
                    boolean writer = false;
                    boolean directori = false;
                    boolean producer = false;

                    for (Crew item : crewList) {
                        switch (item.getDepartment()) {
                            case "Production":
                                producer = true;
                                break;
                            case "Writing":
                                writer = true;
                                break;
                            case "Directing":
                                directori = true;
                                break;
                        }

                    }


                    if (!producer && !writer && !directori) {
                        binding.productionText.setVisibility(View.GONE);
                        binding.tvProduction.setVisibility(View.GONE);
                        binding.directorText.setVisibility(View.GONE);
                        binding.tvDirector.setVisibility(View.GONE);
                        binding.writerText.setVisibility(View.GONE);
                        binding.tvWriter.setVisibility(View.GONE);

                    } else if (!writer && !directori) {
                        binding.directorText.setVisibility(View.GONE);
                        binding.tvDirector.setVisibility(View.GONE);
                        binding.writerText.setVisibility(View.GONE);
                        binding.tvWriter.setVisibility(View.GONE);

                        int z = 1;
                        for (Crew item : crewList) {
                            if (item.getDepartment().equals("Production")) {
                                if (z == 1) {
                                    binding.tvProduction.setText(item.getName());
                                    z = 2;

                                } else {
                                    binding.tvProduction.append(", " + item.getName());

                                }
                            }
                        }

                    } else if (producer && writer && !directori) {
                        binding.directorText.setVisibility(View.GONE);
                        binding.tvDirector.setVisibility(View.GONE);

                        int z = 1;
                        int y = 1;
                        for (Crew item : crewList) {
                            if (item.getDepartment().equals("Production")) {
                                if (z == 1) {
                                    binding.tvProduction.setText(item.getName());
                                    z = 2;

                                } else {
                                    binding.tvProduction.append(", " + item.getName());

                                }
                            }
                            if (item.getDepartment().equals("Writing")) {
                                if (y == 1) {
                                    binding.tvWriter.setText(item.getName());
                                    y = 2;
                                } else {
                                    binding.tvWriter.append(", " + item.getName());

                                }
                            }

                        }

                    } else if (producer && !writer) {
                        binding.writerText.setVisibility(View.GONE);
                        binding.tvWriter.setVisibility(View.GONE);
                        int z = 1;
                        int x = 1;
                        for (Crew item : crewList) {
                            if (item.getDepartment().equals("Production")) {
                                if (z == 1) {
                                    binding.tvProduction.setText(item.getName());
                                    z = 2;

                                } else {
                                    binding.tvProduction.append(", " + item.getName());

                                }

                                if (x == 1) {
                                    binding.tvDirector.setText(item.getName());
                                    x = 2;
                                } else {
                                    binding.tvDirector.append(", " + item.getName());

                                }
                            }


                        }
                    } else if (writer && !producer && !directori) {
                        binding.productionText.setVisibility(View.GONE);
                        binding.tvProduction.setVisibility(View.GONE);
                        binding.directorText.setVisibility(View.GONE);
                        binding.tvDirector.setVisibility(View.GONE);

                        int y = 1;
                        for (Crew item : crewList) {
                            if (item.getDepartment().equals("Writing")) {
                                if (y == 1) {
                                    binding.tvWriter.setText(item.getName());
                                    y = 2;
                                } else {
                                    binding.tvWriter.append(", " + item.getName());

                                }
                            }
                        }

                    }
                }
            }


        });


        viewModel.getIdsLiveData().observe(this, response -> {

            assert response != null;
            if (response.status == Status.ERROR || response.data == null) {
                NetworkFailure();
            } else {
                imdbId = response.data.getImdbId();
                facebookId = response.data.getFacebookId();
                instagram = response.data.getInstagramId();
                twitter = response.data.getTwitterId();


                viewModel.setImdb(imdbId);

                if (type.equals(MoviesFragment.MOVIE_VALUE)) {
                    viewModel.getRatingResponseLiveData().observe(this, ratingResponse -> {
                        assert ratingResponse != null;
                        if (ratingResponse.status == Status.ERROR || ratingResponse.data == null) {
                            NetworkFailure();

                        } else {
                            String rating = ratingResponse.data.getImdbRating();
                            String imdbVotes = ratingResponse.data.getImdbVotes();

                            if (!TextUtils.isEmpty(imdbVotes)) {
                                binding.imdbVotes.setText("(" + imdbVotes + ") " + "votes");
                            } else {
                                binding.imdbVotes.setText("(0)" + "votes");
                            }

                            if (!TextUtils.isEmpty(rating)) {
                                binding.tvImdbRate.setText(rating);
                            } else {
                                binding.tvImdbRate.setText("N/A");
                            }

                            String rated = ratingResponse.data.getRated();
                            if (!TextUtils.isEmpty(rated)) {
                                binding.tvRated.setText(rated);
                            } else {
                                binding.tvRated.setText("N/A");
                            }

                            String year = ratingResponse.data.getYear();

                            if (TextUtils.isEmpty(year)) {
                                binding.releaseTv.setText("N/A");
                            } else {
                                binding.releaseTv.setText(year);

                            }


                            if (ratingResponse.data.getRatings() == null) {
                                binding.tvTomatoes.setVisibility(View.GONE);
                                binding.imageViewTomatoes.setVisibility(View.GONE);
                            } else {
                                if (ratingResponse.data.getRatings().size() == 0 || ratingResponse.data.getRatings().size() == 1) {
                                    binding.tvTomatoes.setText("N/A");
                                } else {
                                    tomatoesRating = ratingResponse.data.getRatings().get(1).getValue();
                                    binding.tvTomatoes.setText(tomatoesRating);
                                }
                            }
                        }


                    });
                } else {
                    viewModel.getTvRatingResponseLiveData().observe(this, tvRatingResponse -> {

                        assert tvRatingResponse != null;
                        if (tvRatingResponse.status == Status.ERROR || tvRatingResponse.data == null) {
                            NetworkFailure();

                        } else {

                            String imdbRateing = tvRatingResponse.data.getImdbRating();
                            String imdbVotes = tvRatingResponse.data.getImdbVotes();

                            if (!TextUtils.isEmpty(imdbVotes)) {
                                binding.imdbVotes.setText("(" + imdbVotes + ") " + "votes");
                            } else {
                                binding.imdbVotes.setText("(0)" + "votes");
                            }


                            if (!TextUtils.isEmpty(imdbRateing)) {
                                binding.tvImdbRate.setText(imdbRateing);
                            } else {
                                binding.tvImdbRate.setText("N/A");
                            }

                            String rated = tvRatingResponse.data.getRated();
                            if (!TextUtils.isEmpty(rated)) {
                                binding.tvRated.setText(rated);
                            } else {
                                binding.tvRated.setText("N/A");
                            }

                            year = tvRatingResponse.data.getYear();
                            String time = tvRatingResponse.data.getRuntime();
                            seasons = tvRatingResponse.data.getTotalSeasons();

                            if (TextUtils.isEmpty(time)) {
                                binding.tvTime.setText("");
                            } else {
                                binding.tvTime.setText(time);
                            }

                            if (TextUtils.isEmpty(year)) {
                                binding.releaseTv.setText("");
                            } else {
                                binding.releaseTv.setText(year);
                            }

                            if (TextUtils.isEmpty(seasons)) {
                                binding.tvTomatoes.setText(" 0 Seasons");
                            } else {
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                layoutParams.topMargin = 21;
                                binding.tvTomatoes.setLayoutParams(layoutParams);
                                binding.tvTomatoes.setText(seasons + " Seasons");
                            }
                        }


                    });
                }
            }


            if (!TextUtils.isEmpty(imdbId) && TextUtils.isEmpty(facebookId) && TextUtils.isEmpty(instagram) && TextUtils.isEmpty(twitter)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Glide.with(Objects.requireNonNull(getContext())).load(R.drawable.logoimdb).into(binding.imageViewImadb);
                    binding.imageViewFace.setVisibility(View.GONE);
                    binding.imageViewInsta.setVisibility(View.GONE);
                    binding.imageViewTwitter.setVisibility(View.GONE);
                }

            } else if (!TextUtils.isEmpty(imdbId) && !TextUtils.isEmpty(facebookId) && TextUtils.isEmpty(instagram) && TextUtils.isEmpty(twitter)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Glide.with(Objects.requireNonNull(getContext())).load(R.drawable.logoimdb).into(binding.imageViewImadb);
                    Glide.with(getContext()).load(R.drawable.logoface).into(binding.imageViewFace);
                    binding.imageViewInsta.setVisibility(View.GONE);
                    binding.imageViewTwitter.setVisibility(View.GONE);
                }


            } else if (!TextUtils.isEmpty(imdbId) && !TextUtils.isEmpty(facebookId) && !TextUtils.isEmpty(instagram) && TextUtils.isEmpty(twitter)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Glide.with(Objects.requireNonNull(getContext())).load(R.drawable.logoimdb).into(binding.imageViewImadb);
                    Glide.with(getContext()).load(R.drawable.logoface).into(binding.imageViewFace);
                    Glide.with(getContext()).load(R.drawable.logoinst).into(binding.imageViewInsta);
                    binding.imageViewTwitter.setVisibility(View.GONE);
                }


            } else if (!TextUtils.isEmpty(imdbId) && !TextUtils.isEmpty(facebookId) && TextUtils.isEmpty(instagram) && !TextUtils.isEmpty(twitter)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Glide.with(Objects.requireNonNull(getContext())).load(R.drawable.logoimdb).into(binding.imageViewImadb);
                    Glide.with(getContext()).load(R.drawable.logoface).into(binding.imageViewFace);
                    Glide.with(getContext()).load(R.drawable.logotwitt).into(binding.imageViewTwitter);
                    binding.imageViewInsta.setVisibility(View.GONE);
                }

            } else if (!TextUtils.isEmpty(facebookId) && !TextUtils.isEmpty(instagram) && !TextUtils.isEmpty(twitter) && !TextUtils.isEmpty(imdbId)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Glide.with(Objects.requireNonNull(getContext())).load(R.drawable.logoface).into(binding.imageViewFace);
                    Glide.with(getContext()).load(R.drawable.logoimdb).into(binding.imageViewImadb);
                    Glide.with(getContext()).load(R.drawable.logoinst).into(binding.imageViewInsta);
                    Glide.with(getContext()).load(R.drawable.logotwitt).into(binding.imageViewTwitter);
                }

            }


        });


    }

    private void checkFavList() {


        binding.likeFab.setOnClickListener(view -> {

            if (!isFav) {
                binding.likeFab.setImageResource(R.drawable.ic_heart_white_48dp);
                Movie movie = new Movie(id_movie + "", title, imageurl, overview, release_date, vote_average + "", "", "", backdrop_path, type, language, voteCount + "");
                viewModel.insertMovie(movie);
                Toast.makeText(getActivity(), "Added to your Watchlist", Toast.LENGTH_SHORT).show();
                isFav = true;

            } else {
                binding.likeFab.setImageResource(R.drawable.ic_heart_outline_white_48dp);
                viewModel.deleteMovie(String.valueOf(id_movie));
                Toast.makeText(getActivity(), "Removed from your Watchlist", Toast.LENGTH_SHORT).show();
                isFav = false;

            }
        });
    }

    private void fixWidth() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ScreanWidth(Objects.requireNonNull(getActivity()));
        }


        if (width > 599 && width < 1000) { // tablet in portrait and land,
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
            binding.appbar.setLayoutParams(layoutParams);
        }


        if (width > 590 && width < 600) { // phone in land
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
            binding.appbar.setLayoutParams(layoutParams);

            LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            Params.leftMargin = 50;
            binding.linearLayoutDetails.setLayoutParams(Params);

            LinearLayout.LayoutParams par = new LinearLayout.LayoutParams(300, 400);
            par.topMargin = 20;
            par.bottomMargin = 20;
            par.leftMargin = 100;
            binding.fileImage.setLayoutParams(par);
        }

    }

    private void initViews() {

        recyclerView_similar = binding.recyclerSimilar;
        recyclerView = binding.recyclerView;
        recyclerView_trailer = binding.recyclerViewTrailer;

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridItemDecoration(12));
        recyclerView.setLayoutManager(new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView_similar.setItemAnimator(new DefaultItemAnimator());
        recyclerView_similar.addItemDecoration(new GridItemDecoration(12));
        recyclerView_similar.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        recyclerView_trailer.setItemAnimator(new DefaultItemAnimator());
        recyclerView_trailer.addItemDecoration(new GridItemDecoration(12));
        recyclerView_trailer.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


    }

    private String formatDate(String datestr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date date = fmt.parse(datestr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d, yyyy", Locale.US);
            return fmtOut.format(date);
        } catch (ParseException ignored) {

        }

        return "";
    }

    private void ScreanWidth(Activity activity) {


        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        float density = activity.getResources().getDisplayMetrics().density;

        width = displayMetrics.widthPixels / density;


    }


    public interface FavDetailFragmentListener {
        void onFavDetailFragmentFinish(int id_movie, String type);

        void onFavDetailActorFinish(int id, String name);

        void onSimilarFavFragmentFinish(int id, String title, String image, String overview, String release_date, Double vote_average,
                                        String backdrop_path, String language, String type, long voteCount);

        void DisplayImage(String url, String type);

        void DisplayPosterFav(int id, String type);
    }


    class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private LayoutInflater layoutInflater;


        ViewPagerAdapter(Context context) {
            this.context = context;
        }


        @Override
        public int getCount() {
            return backdropList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.viewerpager_item, null);

            ImageView imageView = view.findViewById(R.id.backdrop_image_viewPager);


            final String file_path = backdropList.get(position).getFilePath();

            String url = "http://image.tmdb.org/t/p/w780";

            String finalUrl = url + file_path;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Glide.with(Objects.requireNonNull(getActivity()).getApplicationContext()).load(finalUrl).into(imageView);
            }

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);

            imageView.setOnClickListener(v -> mListener.DisplayImage(file_path, "backdrop"));

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ViewPager viewPager = (ViewPager) container;
            View view = (View) object;
            viewPager.removeView(view);

        }
    }


}
