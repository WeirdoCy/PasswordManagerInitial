package example.com.passwordmanagerinitial.adapter;

import android.content.Context;
import android.media.MediaCodecList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.entity.PasswordListEntity;

/**
 * Created by Administrator on 2017/6/29.
 */

public class FloatListAdapter  extends RecyclerView.Adapter<FloatListAdapter.MyViewHolder>{

    private List<PasswordListEntity> mList;
    private LayoutInflater inflater;

    public FloatListAdapter(List<PasswordListEntity> mList, Context context) {
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_activity_float_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.image.setImageResource(mList.get(position).getImageId());
        holder.text.setText(mList.get(position).getPassDescribe());

        if (onItemClickListener != null){
            holder.text.setOnClickListener(new View.OnClickListener() {
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

        private ImageView image;
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.iv_float_icon);
            text = (TextView)itemView.findViewById(R.id.tv_float_describe);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
