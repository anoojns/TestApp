package com.interview.test.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.interview.test.model.Poster;
import com.interview.test.model.api_response.PosterListResponse;
import com.interview.test.model.repository.PosterRepository;

import java.util.ArrayList;
import java.util.List;

public class PosterListingViewmodel extends ViewModel {

    PosterRepository repository = new PosterRepository();
    private List<Poster> posterList = new ArrayList<>();
    private List<Poster> posterListBackup = new ArrayList<>();


    public LiveData<PosterListResponse> getPosterListResponse(int page) {
        return repository.getPosterListResponse(page);
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
