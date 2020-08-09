package com.example.swipablecards;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.swipablecards.databinding.ActivityMainBinding;
import com.example.swipablecards.model.CardMetaData;
import com.example.swipablecards.adapter.CardViewAdapter;
import com.example.swipablecards.viewmodel.CardDataViewModel;
import com.mindorks.placeholderview.SwipeDecor;

public class MainActivity extends AppCompatActivity {

    public static final int ONE = 1;
    public static final int VIEW_COUNT = 3;
    public static final int PADDING_TOP = 20;
    public static final float RELATIVE_SCALE = 0.01f;
    private CardDataViewModel viewModel;
    private int maxCards;
    private int seenCardContact = 1;
    private ActivityMainBinding binding;
    private boolean shouldDecrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(CardDataViewModel.class);
        initViews();
    }

    private void initViews() {
        viewModel.fetchCardsData();
        initSwipeView();
        clickListeners();
        listenToCardDataResponse();
        swipeChangeListener();
    }

    private void initSwipeView() {
        binding.swipeView.getBuilder()
                .setDisplayViewCount(VIEW_COUNT)
                .setIsUndoEnabled(true)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(PADDING_TOP)
                        .setRelativeScale(RELATIVE_SCALE));
    }

    private void swipeChangeListener() {
        binding.swipeView.addItemRemoveListener(count -> {
            shouldDecrement = true;
            seenCardContact += ONE;
            if (seenCardContact == maxCards + ONE) {
                seenCardContact = ONE;
                for (CardMetaData cardMetaData : viewModel.getCardMetaDataList()) {
                    binding.swipeView.addView(new CardViewAdapter(cardMetaData));
                }
            }
            binding.progressText.setText(String.valueOf(seenCardContact));
            binding.progressBar.setProgress(seenCardContact);
            setBackUndoBtnVisible(enableBackBtn());
        });
    }

    private boolean enableBackBtn(){
        return shouldDecrement && seenCardContact > ONE;
    }

    private void setBackUndoBtnVisible(boolean value) {
        if (value) {
            binding.backword.setVisibility(View.VISIBLE);
        } else {
            binding.backword.setVisibility(View.GONE);
        }
    }

    private void listenToCardDataResponse() {
        viewModel.getMutableLiveData().observe(this, cardsResponse -> {
            binding.progressView.setVisibility(View.GONE);
            if (cardsResponse.getData() != null) {
                maxCards = cardsResponse.getData().size();
                binding.progressBar.setMax(cardsResponse.getData().size());
                for (CardMetaData cardMetaData : cardsResponse.getData()) {
                    binding.swipeView.addView(new CardViewAdapter(cardMetaData));
                }
                binding.contentView.setVisibility(View.VISIBLE);
            } else {
                binding.noContentView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void clickListeners() {
        findViewById(R.id.forward).setOnClickListener(v -> binding.swipeView.doSwipe(true));
        findViewById(R.id.backword).setOnClickListener(v -> undoLastSwipe());
    }

    private void undoLastSwipe() {
        seenCardContact -= ONE;
        binding.progressBar.setProgress(seenCardContact);
        shouldDecrement = false;
        binding.progressText.setText(String.valueOf(seenCardContact));
        binding.swipeView.undoLastSwipe();
        setBackUndoBtnVisible(enableBackBtn());
    }

    @Override
    public void onBackPressed() {
        if (enableBackBtn()) {
            undoLastSwipe();
        } else {
            super.onBackPressed();
        }
    }
}
