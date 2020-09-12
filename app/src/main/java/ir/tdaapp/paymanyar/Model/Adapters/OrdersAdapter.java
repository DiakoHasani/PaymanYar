package ir.tdaapp.paymanyar.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import ir.tdaapp.paymanyar.Model.Enums.StepsAnalizeTender;
import ir.tdaapp.paymanyar.Model.Services.onClickOrders;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_Orders;
import ir.tdaapp.paymanyar.R;

//آداپتر مربوط به سفارشات
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    Context context;
    List<VM_Orders> vals;
    onClickOrders clickOrders;

    public OrdersAdapter(Context context) {
        this.context = context;
        vals = new ArrayList<>();
    }

    public void setClickOrders(onClickOrders clickOrders) {
        this.clickOrders = clickOrders;
    }

    //در اینجا یک آیتم جدید اضافه می شود
    public void add(VM_Orders order) {
        vals.add(order);
        notifyItemInserted(vals.size());
    }

    /**
     * در اینجا یک سفارش حذف می شود
     * **/
    public void removeOrder(int id) {
        int pos = -1;
        for (int i = 0; i < vals.size(); i++) {
            if (vals.get(i).getId() == id) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            notifyItemRemoved(pos);
            vals.remove(pos);
            notifyItemRangeChanged(pos, vals.size());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_order, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.lbl_Title.setText(vals.get(position).getTitle());

        if (vals.get(position).getDate().equalsIgnoreCase("")) {
            holder.lbl_Date.setText("--/--/----");
        } else {
            holder.lbl_Date.setText(vals.get(position).getDate());
        }

        if (vals.get(position).getPayment().equalsIgnoreCase("")) {
            holder.lbl_Pay.setText("--,---");
        } else {
            holder.lbl_Pay.setText(vals.get(position).getPayment());
        }

        holder.setStepItem(vals.get(position).getStepsAnalizeTender());

        holder.layout.setOnClickListener(view -> {
            if (clickOrders != null) {
                clickOrders.onClickItem(vals.get(position).getId(), vals.get(position).getOrderKind());
            }
        });

        holder.btn_Item.setOnClickListener(view -> {
            if (clickOrders != null) {
                clickOrders.onClickButton(vals.get(position));
            }
        });

        holder.btn_MoreVert.setOnClickListener(view -> {
            if (clickOrders != null) {
                clickOrders.onClickMenu(vals.get(position).getId(), holder.btn_MoreVert);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_Title, lbl_Date, lbl_Pay, lbl_Button;
        View step1, step2, step3, step4, step5, step6, step7;
        RelativeLayout btn_Item;
        ImageView icon_button, btn_MoreVert;
        CardView layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            findItem(itemView);
        }

        void findItem(View view) {
            lbl_Title = view.findViewById(R.id.lbl_Title);
            lbl_Date = view.findViewById(R.id.lbl_Date);
            lbl_Pay = view.findViewById(R.id.lbl_Pay);
            step1 = view.findViewById(R.id.step1);
            step2 = view.findViewById(R.id.step2);
            step3 = view.findViewById(R.id.step3);
            step4 = view.findViewById(R.id.step4);
            step5 = view.findViewById(R.id.step5);
            step6 = view.findViewById(R.id.step6);
            step7 = view.findViewById(R.id.step7);
            btn_Item = view.findViewById(R.id.btn_Item);
            lbl_Button = view.findViewById(R.id.lbl_Button);
            icon_button = view.findViewById(R.id.icon_button);
            layout = view.findViewById(R.id.layout);
            btn_MoreVert = view.findViewById(R.id.btn_MoreVert);
        }

        //در اینجا عملیات مربوط به استپ بندی و آیکون دکمه آیتم ها انجام می شود
        void setStepItem(StepsAnalizeTender stepItem) {

            disableAllSteps();

            if (stepItem != null) {
                switch (stepItem) {
                    case sendOrder:
                    case orderCheck:
                        step1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        lbl_Button.setText(context.getString(R.string.order_check));
                        icon_button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_order_check));
                        break;
                    case duration:
                    case orderCost:
                    case pay:
                        step1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step3.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step4.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step5.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

                        lbl_Button.setText(context.getString(R.string.Pay));
                        icon_button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pay));

                        break;
                    case doing:

                        step1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step3.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step4.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step5.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step6.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

                        lbl_Button.setText(context.getString(R.string.doing));
                        icon_button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_doing));

                        break;
                    case takingOrders:

                        step1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step3.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step4.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step5.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step6.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        step7.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

                        lbl_Button.setText(context.getString(R.string.Taking_orders));
                        icon_button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_taking_orders));

                        break;
                    default:
                        lbl_Button.setText(context.getString(R.string.details));
                        icon_button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_details));
                        break;
                }
            }
        }

        //در اینجا تمام استپ ها غیرفعال می شوند
        void disableAllSteps() {
            step1.setBackgroundColor(context.getResources().getColor(R.color.colorDisable));
            step2.setBackgroundColor(context.getResources().getColor(R.color.colorDisable));
            step3.setBackgroundColor(context.getResources().getColor(R.color.colorDisable));
            step4.setBackgroundColor(context.getResources().getColor(R.color.colorDisable));
            step5.setBackgroundColor(context.getResources().getColor(R.color.colorDisable));
            step6.setBackgroundColor(context.getResources().getColor(R.color.colorDisable));
            step7.setBackgroundColor(context.getResources().getColor(R.color.colorDisable));
        }
    }
}
