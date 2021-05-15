package ir.tdaapp.paymanyar.View.Dialogs;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import es.dmoral.toasty.Toasty;
import ir.tdaapp.paymanyar.Model.Services.OnProgressDownloadFile;
import ir.tdaapp.paymanyar.Model.Services.S_UpdateAppDialog;
import ir.tdaapp.paymanyar.Model.Utilitys.BaseDialogFragment;
import ir.tdaapp.paymanyar.Presenter.P_UpdateAppDialog;
import ir.tdaapp.paymanyar.R;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class UpdateAppDialog extends BaseDialogFragment implements View.OnClickListener, S_UpdateAppDialog {

  public static final String TAG = "UpdateAppDialog";

  CardView btn_playStore, btn_cancel, btn_update;
  P_UpdateAppDialog p_updateAppDialog;
  ProgressBar progress;
  TextView lbl_Progress;
  String[] Permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
  LinearLayout loadingLayout;

  private boolean hadUpdate;

  public UpdateAppDialog(boolean hadUpdate) {
    this.hadUpdate = hadUpdate;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    View view = inflater.inflate(R.layout.update_app_dialog, container, false);

    getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    findItem(view);
    implement();

    return view;
  }

  void findItem(View view) {
    btn_playStore = view.findViewById(R.id.btn_playStore);
    btn_cancel = view.findViewById(R.id.btn_cancel);
    btn_update = view.findViewById(R.id.btn_update);
    progress = view.findViewById(R.id.progress);
    lbl_Progress = view.findViewById(R.id.lbl_Progress);
    loadingLayout = view.findViewById(R.id.loadingLayout);
  }

  void implement() {
    btn_playStore.setOnClickListener(this);
    btn_cancel.setOnClickListener(this);
    btn_update.setOnClickListener(this);
    p_updateAppDialog = new P_UpdateAppDialog(getContext(), this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_playStore:

        try {
          Intent intent = new Intent(Intent.ACTION_VIEW);
          intent.setData(Uri.parse("bazaar://details?id=" + "ir.tdaapp.paymanyar"));
          intent.setPackage("com.farsitel.bazaar");
          startActivity(intent);
        } catch (Exception e) {
          Toasty.error(getContext(),"از نصب بودن کافه بازار اطمینان حاصل کنید");
        }
        break;
      case R.id.btn_cancel:

        if (!hadUpdate) {
          getDialog().dismiss();
        }
        break;

      case R.id.btn_update:
//        Dexter.withActivity(getActivity()).withPermissions(Permissions).withListener(new MultiplePermissionsListener() {
//          @Override
//          public void onPermissionsChecked(MultiplePermissionsReport report) {
//            btn_update.setEnabled(false);
//            p_updateAppDialog.updateApplication(new OnProgressDownloadFile() {
//              @Override
//              public void onStart() {
//                lbl_Progress.setText("0%");
//                progress.setProgress(0);
//              }
//
//              @Override
//              public void onProgress(int percent) {
//                lbl_Progress.setText(percent + "%");
//                progress.setProgress(percent);
//              }
//
//              @Override
//              public void onFinish() {
//
//              }
//            });
//          }
//
//          @Override
//          public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//          }
//        }).check();
        break;
    }
  }

  /**
   * در اینجا آدرس اپلیکیشن برای دانلود گرفته می شود
   **/
  @Override
  public String getApplicationUrl() {
    return apkUrl;
  }

  /**
   * اگر در دانلود فایل خطای رخ دهد متد زیر فراخوانی می شود
   **/
  @Override
  public void onError() {
    btn_update.setEnabled(true);
    Toasty.error(getContext(), getString(R.string.An_error_has_occurred), Toast.LENGTH_SHORT, true).show();
  }

  /**
   * اگر فایل با موفقیت دانلود شود متد زیر فراخوانی می شود
   **/
  @Override
  public void onSuccess(String fileName) {
    btn_update.setEnabled(true);

    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

    if (file.exists()) {

      try {

        String extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        Uri uri = FileProvider.getUriForFile(getContext(), getActivity().getApplicationContext().getPackageName() + ".provider", file);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          Intent intent = new Intent(Intent.ACTION_VIEW).setDataAndType(uri, mimeType);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
          intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
          startActivity(intent);
        } else {
          Intent intent = new Intent(Intent.ACTION_VIEW);
          intent.setDataAndType(Uri.fromFile(file), mimeType);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        }

      } catch (Exception e) {
        onError();
      }

    } else {
      onError();
    }
  }

  @Override
  public void onLoading(boolean load) {
    if (load) {
      loadingLayout.setVisibility(View.VISIBLE);
    } else {
      loadingLayout.setVisibility(View.GONE);
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    p_updateAppDialog.cancel();
  }
}
