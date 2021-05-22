package com.interview.test.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    public static final int PAGE_START = 1;

    @NonNull
    private GridLayoutManager layoutManager;
    private int totalItemsAvailable;

    /**
     * Set scrolling threshold here (for now i'm assuming 20 item in one page)
     */
    /**
     * Supporting only GridLayoutManager for now.
     */
    public PaginationListener(@NonNull GridLayoutManager layoutManager, int totalItemsAvailable) {
        this.layoutManager = layoutManager;
        this.totalItemsAvailable = totalItemsAvailable;
    }

    public PaginationListener(@NonNull GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.totalItemsAvailable = totalItemsAvailable;
    }

    public void setTotalItemsAvailable(int totalItemsAvailable) {
        this.totalItemsAvailable = totalItemsAvailable;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemLoaded = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemLoaded
                    && firstVisibleItemPosition >= 0
                    && totalItemLoaded < totalItemsAvailable) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();
    public abstract boolean isLastPage();
    public abstract boolean isLoading();
}
