package example.com.passwordmanagerinitial.util;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import example.com.passwordmanagerinitial.activity.AuthenticationActivity;
import example.com.passwordmanagerinitial.activity.UpdateAccPassActy;
import example.com.passwordmanagerinitial.adapter.SearchActivityAdapter;
import example.com.passwordmanagerinitial.database.MyDatabaseHelper;
import example.com.passwordmanagerinitial.entity.SearchListEntity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2017/7/12.
 */

public class DataUtil {

    private Context context;
    private Cursor cursor;

    private MyDatabaseHelper dbHelper;
    private SearchActivityAdapter adapter;

    private String account;


    public void updateData(Context context,int position, List<SearchListEntity> mList){
        Intent intent = new Intent(context, AuthenticationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("account",mList.get(position).getPassDesc());
        context.startActivity(intent);
    }

    public void deleteData(Context context,final int position, final List<SearchListEntity> mList){

    }


}
