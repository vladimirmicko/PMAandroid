package com.randjelovic.vladimir.myapplication.expandableadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.randjelovic.vladimir.myapplication.activities.TestIntroActivity;
import com.randjelovic.vladimir.myapplication.common.MyApplication;
import com.randjelovic.vladimir.myapplication.R;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

	private static final String TAG = MyExpandableListAdapter.class.getName();

	private final SparseArray<Group> groups;
	public LayoutInflater inflater;
    private Context context;

	public MyExpandableListAdapter(LayoutInflater inflater, SparseArray<Group> groups) {
		this.groups = groups;
		this.inflater = inflater;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		Log.d(TAG, "GroupPosition: " + groupPosition + "     childPosition: "+ childPosition);
		return 0;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
		final String children = (String) getChild(groupPosition, childPosition);
		TextView text = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listrow_details, null);
		}
		text = (TextView) convertView.findViewById(R.id.test_list_item);
		text.setText(children);
        context = convertView.getContext();

        Button startButton = (Button) convertView.findViewById(R.id.button_start_test);
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
				MyApplication.setSelectedTestNo(groupPosition);
                Intent intent = new Intent(context, TestIntroActivity.class);
                intent.putExtra("TEST_SELECTED", groupPosition);
                context.startActivity(intent);
            }
        });

//		convertView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(MyApplication.getAppContext(), children,
//						Toast.LENGTH_SHORT).show();
//			}
//		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).children.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		Log.d(TAG, "GET Group position: " + groupPosition);
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
		Log.d(TAG, "Group Collapsed: "+groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
		Log.d(TAG, "Group expanded: "+groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		Log.d(TAG, "Group position: "+groupPosition);
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listrow_group, null);
		}
		Group group = (Group) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(group.string);
		((CheckedTextView) convertView).setChecked(isExpanded);
        Drawable drawable = new BitmapDrawable(convertView.getResources(), group.image);
        ((CheckedTextView) convertView).setCheckMarkDrawable(drawable);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}