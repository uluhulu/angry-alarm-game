package com.ulianasisoeva.angryalarm.screen;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class PlanTextView extends android.support.v7.widget.AppCompatTextView {
    private boolean _stateChanged;
    private boolean _selected;

    public boolean is_stateChanged() {
        return _stateChanged;
    }
    public void set_stateChanged(boolean _stateChanged) {
        this._stateChanged = _stateChanged;
    }

    public boolean is_selected() {
        return _selected;
    }

    public void set_selected(boolean _selected) {
        this._selected = _selected;
    }

    public PlanTextView(Context context) {
        super(context);
    }

    public PlanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlanTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}

