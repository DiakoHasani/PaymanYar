package ir.tdaapp.paymanyar.View.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import es.dmoral.toasty.Toasty;
import ir.tdaapp.li_volley.Enum.ResaultCode;
import ir.tdaapp.paymanyar.Model.Adapters.MaterialAdapter;
import ir.tdaapp.paymanyar.Model.Services.RemoveSupplyNetwork;
import ir.tdaapp.paymanyar.Model.Services.S_MyMaterialFragment;
import ir.tdaapp.paymanyar.Model.Services.clickDeleteDialog;
import ir.tdaapp.paymanyar.Model.Services.onClickPowerSupplyNetwork;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseFragment;
import ir.tdaapp.paymanyar.Model.Utilitys.openUrl;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Material;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Message;
import ir.tdaapp.paymanyar.Presenter.P_MyMaterialFragment;
import ir.tdaapp.paymanyar.R;
import ir.tdaapp.paymanyar.View.Activitys.MainActivity;
import ir.tdaapp.paymanyar.View.Dialogs.AdUpgradeDialog;
import ir.tdaapp.paymanyar.View.Dialogs.DeleteDialog;
import ir.tdaapp.paymanyar.View.Dialogs.ErrorAplicationDialog;

/**
 * مربوط به صفحه مصالح من
 **/
public class MyMaterialFragment extends BaseFragment implements S_MyMaterialFragment, View.OnClickListener {

  public static final String TAG = "MyMaterialFragment";

  P_MyMaterialFragment p_myMaterialFragment;
  Toolbar toolbar;
  RecyclerView recycler;
  ShimmerFrameLayout loading;
  SwipeRefreshLayout reload;
  ErrorAplicationDialog errorAplicationDialog;
  LinearLayoutManager layoutManager;
  ImageView btn_refresh;
  LinearLayout empty;
  MaterialAdapter materialAdapter;
  RelativeLayout loading_Delete;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.my_material_fragment, container, false);

    findItem(view);
    implement();
    setToolbar();

    p_myMaterialFragment.start();

    return view;
  }

  void findItem(View view) {
    toolbar = view.findViewById(R.id.toolbar);
    recycler = view.findViewById(R.id.recycler);
    loading = view.findViewById(R.id.loading);
    reload = view.findViewById(R.id.reload);
    btn_refresh = view.findViewById(R.id.btn_refresh);
    empty = view.findViewById(R.id.empty);
    loading_Delete = view.findViewById(R.id.loading_Delete);
  }

  void implement() {
    p_myMaterialFragment = new P_MyMaterialFragment(getContext(), this);
    btn_refresh.setOnClickListener(this);

    reload.setOnRefreshListener(() -> {
      p_myMaterialFragment.start();
    });
  }

  void setToolbar() {
    toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
    toolbar.setTitle(getContext().getResources().getString(R.string.MyMaterial));
    ((MainActivity) getActivity()).setSupportActionBar(toolbar);
    ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
    toolbar.setNavigationOnClickListener(v -> {
      getActivity().onBackPressed();
    });
    setHasOptionsMenu(true);
  }

  @Override
  public void onLoading(boolean load) {
    if (load) {
      loading.startShimmerAnimation();
      loading.setVisibility(View.VISIBLE);
    } else {
      loading.stopShimmerAnimation();
      loading.setVisibility(View.GONE);
    }
  }

  @Override
  public void OnStart() {
    materialAdapter = new MaterialAdapter(getContext());
    layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    materialAdapter.setShowDeleteButton(true);

    recycler.setAdapter(materialAdapter);
    recycler.setLayoutManager(layoutManager);

    materialAdapter.setClickPowerSupplyNetwork(new onClickPowerSupplyNetwork() {
      @Override
      public void click(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);

        AddMaterialFragment addMaterialFragment = new AddMaterialFragment();
        addMaterialFragment.setArguments(bundle);

        ((MainActivity) getActivity()).onAddFragment(addMaterialFragment, R.anim.fadein, R.anim.short_fadeout, true, AddMaterialFragment.TAG);
      }

      @Override
      public void remove(int id) {
        DeleteDialog deleteDialog = new DeleteDialog(new clickDeleteDialog() {
          @Override
          public void clickedYes() {
            p_myMaterialFragment.deleteMaterial(id, new RemoveSupplyNetwork() {
              @Override
              public void onSuccess(VM_Message message) {
                if (message.isResult()) {
                  materialAdapter.delete(id);
                } else {
                  Toasty.error(getContext(), message.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
              }

              @Override
              public void onError(ResaultCode result) {
                String text = "";

                switch (result) {
                  case NetworkError:
                    text = getString(R.string.please_Checked_Your_Internet_Connection);
                    break;
                  case TimeoutError:
                    text = getString(R.string.YourInternetIsVrySlow);
                    break;
                  case ServerError:
                    text = getString(R.string.There_Was_an_Error_In_The_Server);
                    break;
                  case ParseError:
                  case Error:
                    text = getString(R.string.There_Was_an_Error_In_The_Application);
                    break;
                }
                Toasty.error(getContext(), text, Toast.LENGTH_SHORT, true).show();
              }
            });
          }

          @Override
          public void clickedNo() {

          }
        });
        deleteDialog.show(getActivity().getSupportFragmentManager(), DeleteDialog.TAG);
      }

      @Override
      public void upgrade(int adId) {
        if (adId != 0) {
          AdUpgradeDialog adUpgradeDialog = new AdUpgradeDialog(id -> {
            String url = paymentUrl + "PaymentAd/Index?Parmetr=" + adId + ":" + id + ":3";
            openUrl.getWeb(url, getContext());
          });
          adUpgradeDialog.show(getActivity().getSupportFragmentManager(), AdUpgradeDialog.TAG);
        }
      }
    });
  }

  @Override
  public void onSuccess() {
    recycler.setVisibility(View.VISIBLE);
  }

  @Override
  public void onError(ResaultCode result) {

    String text = "";

    switch (result) {
      case NetworkError:
        text = getString(R.string.please_Checked_Your_Internet_Connection);
        break;
      case TimeoutError:
        text = getString(R.string.YourInternetIsVrySlow);
        break;
      case ServerError:
        text = getString(R.string.There_Was_an_Error_In_The_Server);
        break;
      case ParseError:
      case Error:
        text = getString(R.string.There_Was_an_Error_In_The_Application);
        break;
    }

    btn_refresh.setVisibility(View.VISIBLE);
    errorAplicationDialog = new ErrorAplicationDialog(getString(R.string.Error), text, getString(R.string.Again), R.drawable.ic_error, R.color.colorError, () -> {
      p_myMaterialFragment.start();
      errorAplicationDialog.dismiss();
    });
    errorAplicationDialog.show(getActivity().getSupportFragmentManager(), ErrorAplicationDialog.TAG);
  }

  @Override
  public void onHideAll() {
    loading.setVisibility(View.GONE);
    recycler.setVisibility(View.GONE);
    btn_refresh.setVisibility(View.GONE);
    empty.setVisibility(View.GONE);
    reload.setRefreshing(false);
  }

  @Override
  public void onItem(VM_Material machinery) {
    materialAdapter.add(machinery);
  }

  @Override
  public void onFinish() {

  }

  @Override
  public void noAccount() {
    btn_refresh.setVisibility(View.VISIBLE);
    ((MainActivity) getActivity()).onAddFragment(new LoginFragment(), R.anim.fadein, R.anim.short_fadeout, true, LoginFragment.TAG);
  }

  @Override
  public void onEmpty() {
    empty.setVisibility(View.VISIBLE);
  }

  @Override
  public void onLoadingDelete(boolean load) {
    if (load) {
      loading_Delete.setVisibility(View.VISIBLE);
    } else {
      loading_Delete.setVisibility(View.GONE);
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_refresh:
        p_myMaterialFragment.start();
        break;
    }
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    menu.clear();
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    p_myMaterialFragment.cancel(TAG);
  }
}
