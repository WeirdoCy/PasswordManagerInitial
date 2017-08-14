package example.com.passwordmanagerinitial.view;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/7/12.
 */

public class RecyclerItemDivider extends RecyclerView.ItemDecoration {

    private static final int[] ATTR = new int[]{android.R.attr.listDivider};

    private Drawable mDivider;

    private int mOrientation;

    public RecyclerItemDivider(Context context,int orientation) {
        TypedArray ta = context.obtainStyledAttributes(ATTR);
        mDivider = ta.getDrawable(0);
        ta.recycle();
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (mOrientation == LinearLayout.HORIZONTAL){
            drawHorizontal(c,parent);
        }
        drawVertical(c,parent);
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getLeft();
        int right = parent.getRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getTop();
        int bottom = parent.getBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == LinearLayout.HORIZONTAL){
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }else if (mOrientation == LinearLayout.VERTICAL){
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }
    }
}
