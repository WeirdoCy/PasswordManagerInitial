package example.com.passwordmanagerinitial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.entity.PasswordListEntity;

/**
 * Created by Administrator on 2017/7/4.
 */

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.MyViewHolder>{

    private List<PasswordListEntity> mList;
    private LayoutInflater inflater;

    public AccountListAdapter(Context context,List<PasswordListEntity> mList) {
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_activity_main,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.icon.setImageResource(mList.get(position).getImageId());
        holder.descTv.setText(mList.get(position).getPassDescribe());

        if (onItemClickListener != null){
            holder.descTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon;
        private TextView descTv;

        public MyViewHolder(View itemView) {
            super(itemView);
//            icon = (ImageView)itemView.findViewById(R.id.iv_item_icon);
            descTv = (TextView)itemView.findViewById(R.id.tv_item_describe);
        }

//        private RelativeLayout relativeLayout;
//        private TextView tvParent;
//        private TextView tvChild;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl_parent);
//            tvParent = (TextView) itemView.findViewById(R.id.tv_item_describe);
//            tvChild = (TextView)itemView.findViewById(R.id.tv_child);
//        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
