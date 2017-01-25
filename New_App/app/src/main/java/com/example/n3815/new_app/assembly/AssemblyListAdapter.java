package com.example.n3815.new_app.assembly;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n3815.new_app.R;
import com.example.n3815.new_app.common.bean.AssemBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created by N3815 on 2016-12-28.
 */

public class AssemblyListAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<AssemBean> assemblyItemList = new ArrayList<AssemBean>();

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
        // 한번도 inflate되지 않은 view라면 null로 전달되는 경우가 있으니 반드시 null체크는 해야합니다.
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
                            Log.v("＊[전체]＊",":"+u.getEmpNm()+",("+u.getFavorite()+")");
                        }

                        // 현재 객체의 정보를 저장
                        view.setTag(selectedAssem);

                        temp.clear();
                        for(int q=0; q < assemblyItemList.size(); q++){
                            // assemblyItemList에 담긴 값이 즐겨찾기가 true일 경우
                            if(assemblyItemList.get(q).getFavorite()){
                                Log.v("＊[temp에 add]＊","======>>"+assemblyItemList.get(q).getEmpNm()+"("+q+")");
                                temp.add(assemblyItemList.get(q));  // 해당 객체 insert
                                assemblyItemList.remove(assemblyItemList.get(q));   // 해당 객체 delete
                            }else{
                                Log.v("＊[X그외X]＊","==>>"+assemblyItemList.get(q).getEmpNm()+"("+q+")");
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

                        selectedAssem.setFavorite(false);

                        // assemblyItemList를 모두 찍어보기
                        for(AssemBean u : assemblyItemList){
                            Log.v("＊[전체]＊",":"+u.getEmpNm()+",("+u.getFavorite()+")");
                        }
                        Log.v("＊[전체 총개수]＊",":("+assemblyItemList.size()+")");

                        temp.clear();

                        Collections.sort(assemblyItemList, cmpAsc);
                        for(int q=0; q < temp.size(); q++){
                            // 해당 정보가 false일 경우
                            if(assemblyItemList.get(q).getFavorite() && selectedAssem.getFavorite()){
                                Log.v("＊[temp에 add]＊","======>>"+assemblyItemList.get(q).getEmpNm());
                                temp.add(assemblyItemList.get(q));
                                assemblyItemList.remove(assemblyItemList.get(q));
                            }
                        }

                        // temp에 담긴 즐겨찾기 리스트 재정렬
                        Collections.sort(temp, cmpAsc);

                        // 맨 앞으로 이동
                        assemblyItemList.addAll(0, temp);

                        // assemblyItemList를 모두 찍어보기
                        for(AssemBean u : assemblyItemList){
                            Log.v("＃[즐겨찾기 정렬 중]＃",":"+u.getEmpNm()+",("+u.getFavorite()+")");
                        }

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
        if(listViewItem != null){
            // 현재 클릭한 아이템이 해당 객체에 들어가게된다.
            viewHolder.favoriteBtn.setTag(listViewItem);

            // 정당 색깔 구분
            viewHolder.colorView.setBackgroundColor(Color.rgb(128, 128, 128));
            if(listViewItem.getEmpNm().contains("강")) {
                viewHolder.colorView.setBackgroundColor(Color.rgb(255, 0, 0));
            }

            // 각 위젯에 데이터 반영
            Picasso.with(context).load(listViewItem.getJpgLink()).into(viewHolder.imgView);
            viewHolder.empNmView.setText(listViewItem.getEmpNm());
            viewHolder.origNmView.setText(listViewItem.getOrigNm());
            viewHolder.favoriteBtn.setChecked(listViewItem.getFavorite());
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
        } else {
            // 검색된 글자가 있을 경우, 검색어와 일치하는 국회의원 정보를 담아주기
            for(AssemBean u : assemList){
                String name = u.getEmpNm();  // 국회의원 이름
                if (name.toLowerCase().contains(charText)) {
                    Log.v("＊[확인]＊","일치하는 국회의원 :"+name);
                    assemblyItemList.add(u);
                }
            }
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
        Log.v("===========","========================================");

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
        public int number;
        ImageView imgView;
        TextView empNmView;
        TextView origNmView;
        CheckBox favoriteBtn;
        TextView colorView;
    }
}