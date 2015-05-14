package in.srain.cube.views.ptr.demo.ui.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;

public class RentalsSunHeaderView extends View implements PtrUIHandler {

    private RentalsSunDrawable mDrawable;
    private PtrFrameLayout mPtrFrameLayout;
    private PtrTensionIndicator mPtrTensionIndicator;

    private static final String TAG="PtrUIHandler";

    public RentalsSunHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RentalsSunHeaderView(Context context) {
        super(context);
        init();
    }

    public RentalsSunHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setUp(PtrFrameLayout ptrFrameLayout) {
        mPtrFrameLayout = ptrFrameLayout;
        mPtrTensionIndicator = new PtrTensionIndicator();
        mPtrFrameLayout.setPtrIndicator(mPtrTensionIndicator);
    }

    private void init() {
        mDrawable = new RentalsSunDrawable(getContext(), this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = mDrawable.getTotalDragDistance() * 5 / 4;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        mDrawable.setBounds(pl, pt, pl + right - left, pt + bottom - top);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mDrawable.resetOriginals();
        Log.v(TAG, "onUIReset ==>");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mDrawable.draw(canvas);
        float percent = mPtrTensionIndicator.getOverDragPercent();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        Log.v(TAG,"onUIRefreshPrepare ==>");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mDrawable.start();
        float percent = mPtrTensionIndicator.getOverDragPercent();
        int currentPosY = mPtrTensionIndicator.getCurrentPosY();
        mDrawable.offsetTopAndBottom(currentPosY);
        mDrawable.setPercent(percent);
        Log.v(TAG,"onUIRefreshBegin ==> percent = "+percent+"  getCurrentPosY = " + currentPosY);
        invalidate();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        float percent = mPtrTensionIndicator.getOverDragPercent();
        mDrawable.stop();
        int currentPosY = mPtrTensionIndicator.getCurrentPosY();
        mDrawable.offsetTopAndBottom(currentPosY);
        mDrawable.setPercent(percent);
        Log.v(TAG, "onUIRefreshComplete ==> percent = " + percent + "  getCurrentPosY = " + currentPosY);
        invalidate();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        float percent = mPtrTensionIndicator.getOverDragPercent();
        int currentPosY = mPtrTensionIndicator.getCurrentPosY();
        mDrawable.offsetTopAndBottom(currentPosY);
        mDrawable.setPercent(percent);
        Log.v(TAG, "onUIPositionChange ==> percent = " + percent + "  getCurrentPosY = " + currentPosY);
        invalidate();
    }

    @Override
    public void invalidateDrawable(Drawable dr) {
        if (dr == mDrawable) {
            invalidate();
        } else {
            super.invalidateDrawable(dr);
        }
    }
}
