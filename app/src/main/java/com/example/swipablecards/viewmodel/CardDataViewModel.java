package com.example.swipablecards.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.swipablecards.model.CardMetaData;
import com.example.swipablecards.model.CardsResponse;
import com.example.swipablecards.repository.SwipeCardRepository;
import com.example.swipablecards.retrofit.RetrofitInstance;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Lokesh chennamchetty
 * @date 08/08/2020
 */

public class CardDataViewModel extends AndroidViewModel {

    private SwipeCardRepository swipeCardRepository;
    private MutableLiveData<CardsResponse> mutableLiveData = new MutableLiveData<>();
    private List<CardMetaData> cardMetaDataList;
    private CompositeDisposable compositeDisposable;

    public CardDataViewModel(@NonNull Application application) {
        super(application);
        swipeCardRepository = new SwipeCardRepository(RetrofitInstance.getRetrofit());
        compositeDisposable = new CompositeDisposable();
    }

    public void fetchCardsData() {
        compositeDisposable.add(swipeCardRepository.getCardsData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCardDataResponse,this::onErrorResponse));
    }

    private void onErrorResponse(Throwable throwable) {
        if (throwable!=null)
            mutableLiveData.postValue(new CardsResponse(throwable));
    }

    private void onCardDataResponse(CardsResponse cardsResponse) {
        cardMetaDataList = cardsResponse.getData();
        mutableLiveData.postValue(cardsResponse);
    }

    public MutableLiveData<CardsResponse> getMutableLiveData() {
        return mutableLiveData;
    }

    public List<CardMetaData> getCardMetaDataList() {
        return cardMetaDataList;
    }

    @Override
    protected void onCleared () {
        compositeDisposable.dispose();
        super.onCleared ();
    }
}
