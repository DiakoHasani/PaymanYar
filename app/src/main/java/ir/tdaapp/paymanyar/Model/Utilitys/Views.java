package ir.tdaapp.paymanyar.Model.Utilitys;

import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import ir.tdaapp.paymanyar.Model.Services.layoutSize;

public class Views {

    /**
     * در اینجا یک لایوت می گیرد و اندازه ارتفاع آن را برگشت می دهد
     **/
    public static void getLinearLayoutHeight(LinearLayout layout, layoutSize size) {
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int width = layout.getMeasuredWidth();
                int height = layout.getMeasuredHeight();

                size.getLayoutSize(width, height);
            }
        });
    }
}
