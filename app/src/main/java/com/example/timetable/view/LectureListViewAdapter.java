package com.example.timetable.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.timetable.R;

import java.util.List;

public class LectureListViewAdapter extends BaseAdapter {
    private List<LectureListViewItem> lectureListViewItemList;
    private Context context;
    private LayoutInflater inflate;

    public LectureListViewAdapter(List<LectureListViewItem> list, Context context){
        this.lectureListViewItemList = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lectureListViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return lectureListViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lecture_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득

        TextView subjectNameTextView = (TextView) convertView.findViewById(R.id.item_subject_name) ;
        TextView startTimeTextView = (TextView) convertView.findViewById(R.id.item_start_time) ;
        TextView endTimeTextView = (TextView) convertView.findViewById(R.id.item_end_time) ;
        TextView dayOfWeekTextView = (TextView) convertView.findViewById(R.id.item_dayOfWeek) ;
        TextView classCodeTextView = (TextView) convertView.findViewById(R.id.item_class_code) ;
        TextView professorNameTextView = (TextView) convertView.findViewById(R.id.item_professor_name) ;
        TextView locationTextView = (TextView) convertView.findViewById(R.id.item_location) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        LectureListViewItem listViewItem = lectureListViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        subjectNameTextView.setText(listViewItem.getSubjectName());
        startTimeTextView.setText(listViewItem.getStartTime());
        endTimeTextView.setText(listViewItem.getEndTime());
        dayOfWeekTextView.setText(listViewItem.getDayOfWeek());
        classCodeTextView.setText(listViewItem.getClassCode());
        professorNameTextView.setText(listViewItem.getProfessorName());
        locationTextView.setText(listViewItem.getLocation());

        return convertView;
    }

    public List<LectureListViewItem> getList(){
        return lectureListViewItemList;
    }
}