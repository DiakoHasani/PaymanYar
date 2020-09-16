package ir.tdaapp.paymanyar.Model.Services;

import android.graphics.Bitmap;

public interface S_LevelFragment {
    void OnStart();
    void onPositionBubble(float x, float y);
    Float onPixelTodp(float pixel);
    Float onDpTopixel(float dp);
    void onCenterBubble(boolean isCenter);
    void on_Y_Bubble(boolean isY);
    void on_X_Bubble(boolean isX);
}
