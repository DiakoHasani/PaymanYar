package ir.tdaapp.paymanyar.Model.Services;

import android.widget.ArrayAdapter;

public interface S_PriceRangeFragment {
    void OnStart();
    void onGetDegreeOfImportance(ArrayAdapter adapter);
    void onWinnerChoosed(String number,String position,String CUp,String  CDown);
    void onHideAllChPoints();
    int onGetTopChartNumber(int number);
    void onSetChartNumbers(int number);
    void onSetChartVisibility(int maxNumber);
    int[] onGetChartValues();
    void setDefaltChart();
    void onOutOfRange(String id);
}
