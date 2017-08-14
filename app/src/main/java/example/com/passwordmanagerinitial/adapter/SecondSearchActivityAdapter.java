package example.com.passwordmanagerinitial.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import example.com.passwordmanagerinitial.R;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.entity.SearchListEntity;
import example.com.passwordmanagerinitial.util.DataUtil;
import example.com.passwordmanagerinitial.util.OpenPopupWindow;

/**
 * Created by Administrator on 2017/7/11.
 */

public class SecondSearchActivityAdapter extends RecyclerView.Adapter<SecondSearchActivityAdapter.MyViewHolder> {

    private List<SearchListEntity> mList;
    private LayoutInflater inflater;
    private Context context;

    private DataUtil dataUtil;

    private String account;

    public SecondSearchActivityAdapter(Context context, List<SearchListEntity> mList) {

        this.mList = mList;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_activity_search, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        dataUtil = new DataUtil();

        holder.textView.setText(mList.get(position).getPassDesc());

        if (onItemLongClickListener != null) {
            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onLongClickListener(position);
                    return true;
                }
            });
        }

        if (onItemClickListener != null) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClickListener(position);
                }
            });
        }

        holder.menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popView = inflater.inflate(R.layout.pop_search_activity, null, false);
                LinearLayout linearLayout = (LinearLayout) popView.findViewById(R.id.ll_pop_layout);
                linearLayout.setBackgroundResource(R.mipmap.topic_up);
                final OpenPopupWindow popupWindow = new OpenPopupWindow();
                popupWindow.showPopWindow(holder.menuIv, popView);

                final TextView update = (TextView) popView.findViewById(R.id.tv_update);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        dataUtil.updateData(context,position,mList);
                        popupWindow.hidePopupWindow();
                    }
                });
                TextView delete = (TextView) popView.findViewById(R.id.tv_delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final SQLiteDatabase db = new MyDatabaseHelper(context,"PasswordStore.db",null,1).getWritableDatabase();
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("warning");
                        builder.setMessage("are you sure to delete it?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.execSQL("delete from Bank where account = ?",new String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from webSet where account = ?",new String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from Cloud where account = ?", new String []{mList.get(position).getPassDesc()});
                                db.execSQL("delete from database where account = ?",new String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from entertainment where account = ?",new String[]{mList.get(position).getPassDesc()});
                                db.execSQL("delete from often where account = ?", new String []{mList.get(position).getPassDesc()});
                                db.execSQL("delete from other where account = ?", new String []{mList.get(position).getPassDesc()});
                                deleteData(position);
                            }
                        });
                        builder.setNegativeButton("no",null);
                        builder.show();
                        popupWindow.hidePopupWindow();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView menuIv;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_search);
            menuIv = (ImageView) itemView.findViewById(R.id.iv_search_right);
        }
    }

    public interface OnItemClickListener {
        void onClickListener(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener {
        void onLongClickListener(int position);
    }

    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void deleteData(final int position){
        mList.remove(position);
        notifyItemRemoved(position);

    }


}
