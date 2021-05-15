package ir.tdaapp.paymanyar.Presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import ir.tdaapp.paymanyar.Model.Repositorys.Server.Api_Advertising;
import ir.tdaapp.paymanyar.Model.Services.S_SplashActivity;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Statistics;
import ir.tdaapp.paymanyar.R;

public class P_SplashActivity {

  Context context;
  S_SplashActivity s_splashActivity;
  Disposable dispose_getVals;
  Api_Advertising api_ads;

  public P_SplashActivity(Context context, S_SplashActivity s_splashActivity) {
    this.context = context;
    this.s_splashActivity = s_splashActivity;

    api_ads = new Api_Advertising();
  }

  public void start() {
    getVals();
  }

  void getVals() {
    Single<List<VM_Statistics>> getStats = api_ads.getStatistics();

    dispose_getVals = getStats.subscribeWith(new DisposableSingleObserver<List<VM_Statistics>>() {

      @Override
      public void onSuccess(@NonNull List<VM_Statistics> vm_statistics) {
        s_splashActivity.onReceivedStatistics(vm_statistics);
      }

      @Override
      public void onError(@NonNull Throwable e) {
        s_splashActivity.onError(context.getString(R.string.please_Checked_Your_Internet_Connection));
      }
    });
  }
}
