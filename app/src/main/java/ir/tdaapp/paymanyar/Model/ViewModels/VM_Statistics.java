package ir.tdaapp.paymanyar.Model.ViewModels;

public class VM_Statistics {

  int orderCount, adCount, activeInstallsCount;

  public int getOrderCount() {
    return orderCount;
  }

  public void setOrderCount(int orderCount) {
    this.orderCount = orderCount;
  }

  public int getAdCount() {
    return adCount;
  }

  public void setAdCount(int adCount) {
    this.adCount = adCount;
  }

  public int getActiveInstallsCount() {
    return activeInstallsCount;
  }

  public void setActiveInstallsCount(int activeInstallsCount) {
    this.activeInstallsCount = activeInstallsCount;
  }
}
