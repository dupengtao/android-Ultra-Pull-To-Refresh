package in.srain.cube.views.ptr.demo.ui.classic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.demo.R;
import in.srain.cube.views.ptr.demo.ui.header2.SimpleLeLoadingHeader;

public class EvenOnlyATextView extends TitleBaseFragment {

    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.ptr_demo_block_only_text_view);

        final View contentView = inflater.inflate(R.layout.fragment_classic_header_with_textview, container, false);

        final PtrClassicFrameLayout ptrFrame = (PtrClassicFrameLayout) contentView.findViewById(R.id.fragment_rotate_header_with_text_view_frame);


        SimpleLeLoadingHeader header = new SimpleLeLoadingHeader(getContext());

        //final RentalsSunHeaderView header = new RentalsSunHeaderView(getContext());
        //header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        //header.setPadding(0, LocalDisplay.dp2px(15), 0, LocalDisplay.dp2px(10));
        header.setUp(ptrFrame);

        //ptrFrame.setLoadingMinTime(1000);
        //ptrFrame.setDurationToCloseHeader(1500);
        ptrFrame.setHeaderView(header);
        ptrFrame.addPtrUIHandler(header);

        //ptrFrame.setLastUpdateTimeRelateObject(this);

        ptrFrame.setEnabledNextPtrAtOnce(true);

        ptrFrame.setLastUpdateTimeRelateObject(this);
        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrame.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
        return contentView;
    }

}
