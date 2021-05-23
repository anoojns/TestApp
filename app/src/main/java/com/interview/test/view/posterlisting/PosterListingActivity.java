package com.interview.test.view.posterlisting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.interview.test.R;
import com.interview.test.databinding.ActivityPosterListingBinding;
import com.interview.test.utils.Constants;
import com.interview.test.utils.PaginationListener;
import com.interview.test.view.posterlisting.adapters.PosterListingAdapter;
import com.interview.test.viewmodel.PosterListingViewmodel;

public class PosterListingActivity extends AppCompatActivity {

    private ActivityPosterListingBinding binding;
    private PosterListingViewmodel viewmodel;
    private PosterListingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_poster_listing);
        if (savedInstanceState == null) {
            viewmodel = new ViewModelProvider(this).get(PosterListingViewmodel.class);
        }
        setLayoutManagerForList();

        setSupportActionBar(binding.toolbar);
        handleClicks();
        handleSearch();

        loadPosterData();
    }

    /**
     * sets up layout manager for the recyclerview
     * detect in which orientation device initially open app. and set layout manager based on it.
     */
    private void setLayoutManagerForList() {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            viewmodel.layoutManager = new GridLayoutManager(this, Constants.PosterListingConstants.COLUMN_COUNT_PORTRAIT);
        } else {
            viewmodel.layoutManager = new GridLayoutManager(this, Constants.PosterListingConstants.COLUMN_COUNT_LANDSCAPE);
        }
        binding.recyclerView.setLayoutManager(viewmodel.layoutManager);

    }


    /**
     * gets data for listing.
     */
    private void loadPosterData() {
        viewmodel.setLoading(true);
        viewmodel.getPosterListResponse(viewmodel.getCurrentPageNumber()).observe(this, posterListResponse -> {
            if (posterListResponse != null && posterListResponse.getPage() != null) {

                if (viewmodel.getCurrentPageNumber() == 1) {
                    binding.tvTitle.setText(posterListResponse.getPage().getTitle());
                }
                setPaginationScrollListener();
                setAdapter();
            }
            viewmodel.setLoading(false);
        });
    }

    /**
     * sets up adapter for the list.
     * create a new list if no adapter is set. (initial case)
     * refresh existing adapter if adper object exist (support pagination)
     */
    private void setAdapter() {
        if (adapter == null) {
            adapter = new PosterListingAdapter(viewmodel.getPosterImages());
            binding.recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        int orientation = newConfig.orientation;

        if (viewmodel.layoutManager == null) {
            viewmodel.layoutManager = new GridLayoutManager(this, Constants.PosterListingConstants.COLUMN_COUNT_PORTRAIT);
        }

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            viewmodel.layoutManager.setSpanCount(Constants.PosterListingConstants.COLUMN_COUNT_PORTRAIT);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewmodel.layoutManager.setSpanCount(Constants.PosterListingConstants.COLUMN_COUNT_LANDSCAPE);
        }
        binding.recyclerView.setLayoutManager(viewmodel.layoutManager);
    }

    /**
     * add scroll listener to recyclerview to handle pagination
     */
    private void setPaginationScrollListener() {
        if (viewmodel.getCurrentPageNumber() == 1) {

            try {
                binding.recyclerView.clearOnScrollListeners();
            } catch (Exception e) {
                e.printStackTrace();
            }


            binding.recyclerView.addOnScrollListener(new PaginationListener(viewmodel.layoutManager, viewmodel.getTotalCount()) {
                @Override
                protected void loadMoreItems() {
                    viewmodel.incrementPage();
                    loadPosterData();
                }

                @Override
                public boolean isLastPage() {
                    return (viewmodel.getCurrentPageNumber() == viewmodel.getMaxPages());
                }

                @Override
                public boolean isLoading() {
                    return viewmodel.isLoading();
                }
            });

        }
    }

    /**
     * register required click listeners
     */
    void handleClicks() {

        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.ivSearch.setOnClickListener(v -> {
           showOrHideSearch();
        });
    }

    /**
     * Showing or hiding search functionality on action bar
     */
    private void showOrHideSearch() {
        if ( binding.etSearch.getVisibility() == View.VISIBLE) {
            binding.etSearch.setVisibility(View.GONE);
            binding.ivBack.setVisibility(View.VISIBLE);
            binding.tvTitle.setVisibility(View.VISIBLE);
            binding.ivSearch.setImageResource(R.drawable.ic_search);

            binding.etSearch.setText("");
        } else {
            binding.ivBack.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.GONE);
            binding.etSearch.setVisibility(View.VISIBLE);
            binding.ivSearch.setImageResource(R.drawable.ic_search_cancel);

            binding.etSearch.requestFocus();
        }
    }

    @Override
    public void onBackPressed() {
        if ( binding.etSearch.getVisibility() == View.VISIBLE) {
            showOrHideSearch();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * handle text change listener for search functionality
     */
    private void handleSearch() {

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().trim();
                if (searchText.length() > 2) {
                    viewmodel.filterData(searchText);
                    setAdapter();
                } else /*if (searchText.length() == 0)*/ {
                    viewmodel.restorePosterImage();
                    setAdapter();
                }

                if (adapter != null) {
                    adapter.setSearchText(searchText);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
