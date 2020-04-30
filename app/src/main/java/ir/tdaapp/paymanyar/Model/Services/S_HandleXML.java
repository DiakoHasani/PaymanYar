package ir.tdaapp.paymanyar.Model.Services;

import java.util.List;

import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;

public interface S_HandleXML {
    void onSuccess(List<VM_NewsPaper> newsPapers);
    void onError(Exception e);
}
