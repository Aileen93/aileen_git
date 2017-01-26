package com.example.n3815.new_app.assembly;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n3815.new_app.R;
import com.example.n3815.new_app.common.SearchCommon;
import com.example.n3815.new_app.common.bean.AssemBean;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by N3815 on 2016-12-28.
 */
public class AssemblyListAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<AssemBean> assemblyItemList = new ArrayList<AssemBean>();

    public static boolean isSearch = false;

    ArrayList<AssemBean> temp = new ArrayList<AssemBean>();

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

    // 데이터 정렬을 위한 Comparator
    public static Comparator<AssemBean> cmpAsc = new Comparator<AssemBean>() {
        @Override
        public int compare(AssemBean o1, AssemBean o2) {
            return o1.getEmpNm().compareTo(o2.getEmpNm()) ;
        }
    };

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

            // 즐겨찾기 버튼 클릭시
            viewHolder.favoriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // [callback] 현재 선택된 객체 정보를 가져온다.
                    AssemBean selectedAssem = (AssemBean) view.getTag();
                    CheckBox selectedBtn = (CheckBox) view;

                    // 아이템을 클릭했을 경우,
                    if (selectedAssem.getFavorite() == false && selectedBtn.isChecked()){
                        Log.v("★[추가]★","======>>"+selectedAssem.getEmpNm());

                        // 객체의 정보를 true로 설정 - 자동으로 Array 배열의 값이 변경
                        selectedAssem.setFavorite(true);
                        for(AssemBean u : assemblyItemList){
                            Log.v("＊[전체]＊",":"+u.getEmpNm()+",("+u.getFavorite()+")");// assemblyItemList에 담긴 값이 즐겨찾기가 true일 경우
                        }

                        temp.clear();
                        for(int k=0;k <assemblyItemList.size(); k++){
                            if(assemblyItemList.get(k).getFavorite()){
                                Log.v("＊[temp에 add]＊","======>>"+assemblyItemList.get(k).getEmpNm());
                                temp.add(assemblyItemList.get(k));  // 해당 객체 insert
                                assemblyItemList.remove(assemblyItemList.get(k));
                                // 해당 객체 delete 할때 해당 객체가 불안정적으로 구동됨 인덱스가 변화되면서 Error
                            }else{
                                Log.v("＊[X그외X]＊","==>>"+assemblyItemList.get(k).getEmpNm());
                            }
                        }

                        // temp에 담긴 즐겨찾기 리스트 재정렬
                        Collections.sort(temp, cmpAsc);
                        for(AssemBean u : temp){
                            Log.v("＃[즐겨찾기 정렬 중]＃",":"+u.getEmpNm()+",("+u.getFavorite()+")");
                        }

                        // 맨 앞으로 이동
                        assemblyItemList.addAll(0, temp);
                        Log.v("===========","======================================>>>>>>"+assemblyItemList.size());
                        // assemblyItemList를 모두 찍어보기
                        for(AssemBean u : assemblyItemList){
                            Log.v("＃[결과]＃",":"+u.getEmpNm()+",("+u.getFavorite()+")");
                        }

                        notifyDataSetChanged();
                        Toast.makeText(context,"즐겨찾기가 등록 되었습니다.",Toast.LENGTH_LONG).show();

                    }else if(selectedAssem.getFavorite() == true){
                        Log.v("★[해제]★","======>>"+selectedAssem.getEmpNm());

                        // view가 자동으로 dapter에 걸려있는 listArray를 관리해주나봄
                        selectedAssem.setFavorite(false);
                        for(AssemBean u : assemblyItemList){
                            Log.v("＊[해제]＊",":"+u.getEmpNm()+",("+u.getFavorite()+")");
                        }

                        // assemblyItemList를 모두 찍어보기
                        temp.clear();
                        for(int k=0;k <assemblyItemList.size(); k++){
                            if(assemblyItemList.get(k).getFavorite()){
                                Log.v("＊[temp에 add]＊","======>>"+assemblyItemList.get(k).getEmpNm());
                                temp.add(assemblyItemList.get(k));  // 해당 객체 insert
                                assemblyItemList.remove(assemblyItemList.get(k));
                                // 해당 객체 delete 할때 해당 객체가 불안정적으로 구동됨 인덱스가 변화되면서 Error
                            }
                            // 왜 한명은 빠지는거지 현재 배열의 정보가 정확하지 않는 현상확인하기
                        }

                        // 맨 앞으로 이동
                        assemblyItemList.addAll(0, temp);

                        notifyDataSetChanged();
                        Toast.makeText(context,"즐겨찾기가 해제 되었습니다.",Toast.LENGTH_LONG).show();
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
            /*if (listViewItem.getEmpNm().contains("강")) {
                viewHolder.colorView.setBackgroundColor(Color.rgb(255, 0, 0));
            }*/

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
            char prevInitial = SearchCommon.getInitialSound(prevAssemInfo.getEmpNm().charAt(0));

            boolean currCheck = listViewItem.getFavorite();
            char currInitial = SearchCommon.getInitialSound(listViewItem.getEmpNm().charAt(0));

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
     * 검색 기능
     * @param charText  -- 검색어
     * @param assemList -- 국회의원 리스트
     */
    public ArrayList<AssemBean> filter(String charText, ArrayList<AssemBean> assemList) {
        charText = charText.toLowerCase(Locale.getDefault());
        assemblyItemList.clear();   // 어뎁터 뷰에 연결된 배열 초기화

        Log.v("＊＊＊","전체 대상자 :"+assemList.size()+", (검색어 :"+charText+")＊＊＊");

        if (charText.length() == 0) {
            // 검색된 글자가 하나도 없을 경우, 전체 리스트를 넣어주기
            assemblyItemList.addAll(assemList);
            isSearch = false;
        } else {
            // 검색된 글자가 있을 경우, 검색어와 일치하는 국회의원 정보를 담아주기
            for(AssemBean u : assemList){
                String name = u.getEmpNm();  // 국회의원 이름
                if (name.toLowerCase().contains(charText)) {
                    Log.v("＊[확인]＊","일치하는 국회의원 :"+name);
                    assemblyItemList.add(u);
                }
            }
            isSearch = true;
        }

        return orderByAssemList(assemblyItemList);
    }

    /**
     * 대상 배열을 재정렬해줍니다.
     * @param targetArray
     */
    public ArrayList<AssemBean> orderByAssemList(ArrayList<AssemBean> targetArray){

        // 전체정렬
        Collections.sort(targetArray, cmpAsc);

        // 즐겨찾기 골라서 맨위로
        ArrayList<AssemBean> temp = new ArrayList<AssemBean>();
        for(int j = 0; j < targetArray.size(); j++){
            if(targetArray.get(j).getFavorite() == true){
                temp.add(targetArray.get(j));
                targetArray.remove(j);
            }
        }
        Collections.sort(temp, cmpAsc);
        targetArray.addAll(0, temp);

        notifyDataSetChanged();

        return targetArray;
    }

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