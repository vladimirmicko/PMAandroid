package com.randjelovic.vladimir.myapplication.expandableadapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Group {

	public String string;
	public Bitmap image;
	public List<String> children = new ArrayList<String>();
	public Group(String string) {
		this.string = string;
	}
}
