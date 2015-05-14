package in.srain.cube.views.ptr.demo.ui.header2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.demo.R;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by dupengtao on 15-5-14.
 */
public class SimpleLeLoadingHeader extends RelativeLayout implements PtrUIHandler {
    private Context mContext;
    private PtrFrameLayout mPtrFrameLayout;
    //private PtrTensionIndicator mPtrTensionIndicator;
    private SimpleLeLoadingView mSimpleLeLoadingView;

    public SimpleLeLoadingHeader(Context context) {
        this(context, null);
    }

    public SimpleLeLoadingHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView();
    }

    private void initView() {
        RelativeLayout.LayoutParams rLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,210);
        this.setLayoutParams(rLP);
        View rootView = View.inflate(mContext, R.layout.view_simpleleloading_header, this);
        mSimpleLeLoadingView = (SimpleLeLoadingView) rootView.findViewById(R.id.sllv);
    }

    public SimpleLeLoadingHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUp(PtrFrameLayout ptrFrameLayout) {
        mPtrFrameLayout = ptrFrameLayout;
        //mPtrTensionIndicator = new PtrTensionIndicator();
        //mPtrFrameLayout.setPtrIndicator(mPtrTensionIndicator);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mSimpleLeLoadingView.resetOriginals();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        float percent = ptrIndicator.getCurrentPercent();
        long l = Float.valueOf(percent * 1000).longValue();
        if(l>0) {
            mSimpleLeLoadingView.setPercent(l);
        }else {
            mSimpleLeLoadingView.resetOriginals();
        }
    }
}
