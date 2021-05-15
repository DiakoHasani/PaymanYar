package ir.tdaapp.paymanyar.Model.Services;

import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_Statistics;

public interface S_SplashActivity {

  void onReceivedStatistics(List<VM_Statistics> statistics);

  void onError(String s);
}
