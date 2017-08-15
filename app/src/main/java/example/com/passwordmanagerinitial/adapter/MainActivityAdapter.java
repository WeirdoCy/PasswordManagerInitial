package example.com.passwordmanagerinitial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.entity.ParentEntity;

/**
 * Created by Administrator on 2017/8/10.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder>{

    private Context context;
    private List mList;

    private boolean isClick = true;

    public MainActivityAdapter(Context context, List<?> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_activity_main,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (mList.get(position) instanceof ParentEntity){
            holder.relativeLayout.setVisibility(View.VISIBLE);
            holder.rlChild.setVisibility(View.GONE);
            ParentEntity parentEntity = (ParentEntity) mList.get(position);
            holder.tvParent.setText(parentEntity.getName());
//            holder.imageView.setImageResource(R.mipmap.right);
        }else{
            holder.rlChild.setVisibility(View.VISIBLE);
            holder.relativeLayout.setVisibility(View.GONE);
            ParentEntity.ChildEntity childEntity = (ParentEntity.ChildEntity) mList.get(position);
            holder.tvChild.setText(childEntity.getName());
//            holder.imageView.setImageResource(R.mipmap.down);
        }

        if (onItemClickListener != null){
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(position);
//                    if (holder.relativeLayout.getVisibility() == View.GONE){
//                        holder.imageView.setImageResource(R.mipmap.down);
//                    }else{
//                        holder.imageView.setImageResource(R.mipmap.right);
//                    }
                }
            });
            holder.tvChild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(pos);
                }
            });

        }

//        if (holder.relativeLayout.getVisibility() == View.VISIBLE){
//            if (isClick){
//                holder.imageView.setImageResource(R.mipmap.down);
//                isClick = !isClick;
//            }else{
//                holder.imageView.setImageResource(R.mipmap.right);
//            }
//        }else{
//            holder.imageView.setImageResource(R.mipmap.down);
//        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout relativeLayout,rlChild;
        private TextView tvParent;
        private TextView tvChild;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl_parent);
            tvParent = (TextView)itemView.findViewById(R.id.tv_item_describe);
            tvChild = (TextView)itemView.findViewById(R.id.tv_child);
            rlChild = (RelativeLayout)itemView.findViewById(R.id.rl_child);
            imageView = (ImageView)itemView.findViewById(R.id.iv_item_right);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addChild(int position,List<?> list){
        mList.addAll(position,list);
        notifyItemRangeChanged(position,list.size());
        notifyDataSetChanged();
    }

    public void removeChild(int position,int itemCount){
        for (int i = 0; i < itemCount;i++){
            mList.remove(position);
        }
        notifyItemRangeRemoved(position,itemCount);
        notifyDataSetChanged();
    }
}
