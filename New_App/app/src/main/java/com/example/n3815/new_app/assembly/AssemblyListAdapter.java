package com.example.n3815.new_app.assembly;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n3815.new_app.R;
import com.example.n3815.new_app.common.bean.AssemBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by N3815 on 2016-12-28.
 */
public class AssemblyListAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<AssemBean> assemblyItemList = new ArrayList<AssemBean>();
    private ArrayList<AssemBean> temp = new ArrayList<AssemBean>();

    public AssemblyListAdapter(){
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return assemblyItemList.size() ;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return assemblyItemList.get(position) ;
    }

    /**
     * 어뎁터의 뷰를 구현하는 함수
     * @param position      -- 행의 index를 의미
     * @param convertView   -- 행 전체를 나타내는 뷰를 의미
     * @param parent         -- 어댑터를 가지고 있는 부모의 뷰를 의미
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();
        ViewHolder viewHolder;
        View v = convertView;

        // getView에서 넘어오는 convertView는 이전에 그려졌던 view를 넘기는데요.
        // 한번도 inflate되지 않은 view라면 null로 전달되는 경우가 있으니 반드시 null체크
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_assem_item, parent, false);

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 데이터 획득
            viewHolder = new ViewHolder();
            viewHolder.imgView = (ImageView) v.findViewById(R.id.assem_empImg);
            viewHolder.empNmView = (TextView) v.findViewById(R.id.assem_empNm);
            viewHolder.origNmView = (TextView) v.findViewById(R.id.assem_origNm);
            viewHolder.favoriteBtn = (CheckBox) v.findViewById(R.id.favorite);
            viewHolder.colorView = (TextView) v.findViewById(R.id.color);
            viewHolder.titleLayoutView = (LinearLayout) v.findViewById(R.id.titleLayout);
            viewHolder.titleView = (TextView) v.findViewById(R.id.title);
            v.setTag(viewHolder);

            // 기능1) 즐겨찾기
            viewHolder.favoriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // [callback] 현재 선택된 객체 정보를 가져온다.
                    AssemBean selectedAssem = (AssemBean) view.getTag();
                    CheckBox selectedBtn = (CheckBox) view;

                    // 아이템을 클릭했을 경우,
                    if (selectedAssem.getFavorite() == false && selectedBtn.isChecked()){

                        // 객체의 정보를 true로 설정 - 자동으로 Array 배열의 값이 변경
                        selectedAssem.setFavorite(true);

                        // 변경된 정보값을 다시 원본 배열에게 알리기
                        ((AssemblyListActivity) context).changeItemInfo(selectedAssem);

                        Toast.makeText(context,"즐겨찾기가 등록 되었습니다!",Toast.LENGTH_LONG).show();

                    }else if(selectedAssem.getFavorite() == true){

                        // 객체의 정보를 true로 설정 - 자동으로 Array 배열의 값이 변경
                        selectedAssem.setFavorite(false);

                        // 변경된 정보값을 다시 원본 배열에게 알리기
                        ((AssemblyListActivity) context).changeItemInfo(selectedAssem);

                        Toast.makeText(context,"즐겨찾기가 해제 되었습니다!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
        else{
            viewHolder = (ViewHolder) v.getTag();
        }

        // Data Set(assemblyItemList)에서 position에 위치한 데이터 획득
        final AssemBean listViewItem = assemblyItemList.get(position);
        if(listViewItem != null) {
            // 현재 클릭한 아이템이 해당 객체에 들어가게된다.
            viewHolder.favoriteBtn.setTag(listViewItem);

            // 정당 색깔 구분
            viewHolder.colorView.setBackgroundColor(Color.rgb(128, 128, 128));

            // 각 위젯에 데이터 반영
            Picasso.with(context).load(listViewItem.getJpgLink()).into(viewHolder.imgView);
            viewHolder.empNmView.setText(listViewItem.getEmpNm());
            viewHolder.origNmView.setText(listViewItem.getOrigNm());
            viewHolder.favoriteBtn.setChecked(listViewItem.getFavorite());
            viewHolder.titleLayoutView.setVisibility(View.GONE);

            // 이전 아이템의 이름가져오기
            int c = 0;
            if (position > 0) {
                c = position - 1;
            }

            // 그룹 라벨을 위한 로직
            final AssemBean prevAssemInfo = assemblyItemList.get(c);

            boolean prevCheck = prevAssemInfo.getFavorite();
            char prevInitial = prevAssemInfo.getInitialEmpNm();

            boolean currCheck = listViewItem.getFavorite();
            char currInitial = listViewItem.getInitialEmpNm();

            // 포지션이 0일 경우,
            if(position == 0) {
                if(currCheck) {
                    viewHolder.titleLayoutView.setVisibility(View.VISIBLE);
                    viewHolder.titleView.setText("즐겨찾기 그룹");
                }else{
                    // 그외 ㄱ으로 시작하는 사람들
                    viewHolder.titleLayoutView.setVisibility(View.VISIBLE);
                    viewHolder.titleView.setText(String.valueOf(currInitial));
                }

            // 그외 포지션
            }else{
                // 1. 첫번째 ㄱ을 위해서
                if( (prevCheck== true && currCheck == false)) {
                    viewHolder.titleLayoutView.setVisibility(View.VISIBLE);
                    viewHolder.titleView.setText(String.valueOf(currInitial)+" 그룹");

                // 2. 이전 자음과 현재 자음이 다를 경우
                }else if( prevInitial != currInitial &&  (prevCheck == false && currCheck == false)){
                    viewHolder.titleLayoutView.setVisibility(View.VISIBLE);
                    viewHolder.titleView.setText(String.valueOf(currInitial)+ " 그룹");
                }
            }
        }

        return v;
    }

    /**
     * 아이템 추가하기
     * @param result -- 추가할 아이템
     */
    public void addItem(ArrayList<AssemBean> result) {
        assemblyItemList.clear();   // 어뎁터 뷰에 연결된 배열 초기화

        temp.clear();
        for(AssemBean u : result){
           if(u.getFavorite()){
               temp.add(u);
           }else{
               assemblyItemList.add(u);
           }
        }

        assemblyItemList.addAll(0, temp);
        notifyDataSetChanged();
    }

    /**
     * viewHolder
     */
    public class ViewHolder
    {
        TextView titleView;
        ImageView imgView;
        TextView empNmView;
        TextView origNmView;
        CheckBox favoriteBtn;
        TextView colorView;
        LinearLayout titleLayoutView;
    }
}