package com.interview.test.viewmodel;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;

import com.interview.test.data.model.Poster;
import com.interview.test.data.model.api_response.PosterListResponse;
import com.interview.test.data.repository.PosterRepository;

import java.util.ArrayList;
import java.util.List;

public class PosterListingViewmodel extends ViewModel {

    PosterRepository repository = new PosterRepository();
    private List<Poster> posterList = new ArrayList<>();
    private List<Poster> posterListBackup = new ArrayList<>();

    //pagination constants
    public GridLayoutManager layoutManager;
    private int currentPageNumber = 1;
    private int pageSize;
    private int totalCount;
    private int maxPages;
    private boolean isLoading = false;

    public GridLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void incrementPage() {
        currentPageNumber++;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public LiveData<PosterListResponse> getPosterListResponse(int page) {

        Log.d("Check Pagination", "getPosterListResponse: for page "+page);
        return Transformations.map(repository.getPosterListResponse(page), new Function<PosterListResponse, PosterListResponse>() {
            @Override
            public PosterListResponse apply(PosterListResponse input) {

                if (input != null && input.getPage() != null) {

                    currentPageNumber = input.getPage().getPagenum();
                    pageSize = input.getPage().getPagesize();
                    totalCount = input.getPage().getTotalcontentitems();
                    maxPages = (int) Math.ceil((float) totalCount / (float) pageSize);

                    setPosterList(input.getPage().getContentitems().getContent());
                }

                return input;
            }
        });
       // return repository.getPosterListResponse(page);
    }

    public void setPosterList(List<Poster> posterList) {
        if (posterList != null) {
            this.posterList.addAll(posterList);
            this.posterListBackup.addAll(posterList);
        }
    }

    public void restorePosterImage() {
        posterList.clear();
        posterList.addAll(posterListBackup);
    }

    public List<Poster> getPosterImages() {
        return posterList;
    }

    public void filterData(String searchText) {
        posterList.clear();
        for (Poster posterData : posterListBackup) {
            if (posterData.getName().toLowerCase().contains(searchText.toLowerCase())) {
                posterList.add(posterData);
            }
        }
    }

}
