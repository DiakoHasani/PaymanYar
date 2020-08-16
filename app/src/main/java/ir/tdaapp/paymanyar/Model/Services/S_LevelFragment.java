package ir.tdaapp.paymanyar.Model.Services;

import android.graphics.Bitmap;

public interface S_LevelFragment {
    void OnStart();
    void onPositionBubble(int x, int y);
    Float onPixelTodp(float pixel);
    Float onDpTopixel(float dp);
}
