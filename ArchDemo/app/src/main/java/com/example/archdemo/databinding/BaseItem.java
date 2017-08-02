package com.example.archdemo.databinding;

import android.view.View;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.example.archdemo.BR;

/**
 * Created by baurine on 8/2/17.
 */

public abstract class BaseItem implements MultiTypeAdapter.IItem {
    @Override
    public int getVariableId() {
        return BR.item;
    }

    ////////////////////////////////////////////
    // handle event
    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
