package com.interview.test.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.interview.test.data.model.api_response.PosterListResponse;
import com.interview.test.utils.DataReader;

public class PosterRepository {

    public LiveData<PosterListResponse> getPosterListResponse(int page) {
        MutableLiveData<PosterListResponse> response = new MutableLiveData<>();

        switch (page) {
            case 1 :
                response.setValue(PosterListResponse.getClassDataFromJsonString(
                        new DataReader().readData("CONTENTLISTINGPAGE-PAGE1.json")
                ));
                break;

            case 2 :
                response.setValue(PosterListResponse.getClassDataFromJsonString(
                        new DataReader().readData("CONTENTLISTINGPAGE-PAGE2.json")
                ));
                break;

            case 3:
                response.setValue(PosterListResponse.getClassDataFromJsonString(
                        new DataReader().readData("CONTENTLISTINGPAGE-PAGE3.json")
                ));
                break;


        }
        return response;
    }
}
