package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_PriceRange;

public interface S_PriceRangeFragment {
    void OnStart();

    void onGetDegreeOfImportance(ArrayAdapter adapter);

    Float onPixelTodp(float pixel);

    int IntToDP(int value);

    void winnerSelection(String number, String position, int CUp, int CDown);

    void setDefaultChartNumber();

    void setMarginPointChart(RelativeLayout point, int marginBottom);

    int getMarginRightChartPoint(RelativeLayout layout);

    void setMarginLineChart(RelativeLayout layout, int marginBottom);

    void setOutOfRangePointChart(String position);

    void onSetChartNumbers(int lineDown);

    int roundNumber(int number);

    void setPointChart(VM_PriceRange point);

    int getMarginPointChart(int percentPoint, int heightChartLayout);

    RelativeLayout getChartPointByNumber(int number);

    void enableAnimationPointChart(String position);

    void disableAnimationChart();

}