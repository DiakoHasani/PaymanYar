package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_PriceRange;

public interface S_PriceRangeFragment {
    void OnStart();

    void onGetDegreeOfImportance(ArrayAdapter adapter);

    void onSetChartNumbers(int lineDown, int lineUp);

    int onGetRoundNumber(int number, boolean isDown);

    void onHideAllChartItems();

    void onShowRightChartNumbers(int number);

    int getMaxPercents();

    void setDefultPointChart();

    void setPointChart(VM_PriceRange point);

    LinearLayout getLinearLayoutRightChart(int percent);

    void getSpaceBetweenRightChartNumbers(layoutSize size);

    Float onPixelTodp(float pixel);

    int IntToDP(int value);

    LinearLayout getFirstNumberLayoutRightChart();

    int getNumberByLayoutChart(LinearLayout layout);

    int getMarginPointChart(int percentPoint, int heightChartLayout);

    RelativeLayout getChartPointByNumber(int number);

    int getTopChartNumber();

    int getSpaceBetweenChartNumbers(int firstNumber, int secondNumber);

    int getMarginRightChartPoint(RelativeLayout layout);

    void winnerSelection(String number, String position, int CUp, int CDown);

    void enableAnimationPointChart(String position);

    void disableAnimationChart();

    void setOutOfRangePointChart(String position);

}