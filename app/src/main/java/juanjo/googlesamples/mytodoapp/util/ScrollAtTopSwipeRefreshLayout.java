package juanjo.googlesamples.mytodoapp.util;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public class ScrollAtTopSwipeRefreshLayout extends SwipeRefreshLayout {
    private View mScrollUpChild;

    public ScrollAtTopSwipeRefreshLayout(Context context) {
        super(context);
    }

    public ScrollAtTopSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        if(mScrollUpChild !=null)
            return ViewCompat.canScrollHorizontally(mScrollUpChild,-1);
        return super.canChildScrollUp();

    }

    public void setScrollUpChild(View view){
        this.mScrollUpChild = view;
    }
}
