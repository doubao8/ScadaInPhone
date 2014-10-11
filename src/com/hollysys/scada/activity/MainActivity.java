package com.hollysys.scada.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hollysys.scada.R;
import com.hollysys.scada.basic.ExitApplication;
import com.hollysys.scada.util.Util;

public class MainActivity extends ListActivity {

	private List<Map<String, Object>> lstData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);

		String[] svgFiles = null;
		try {
			svgFiles = this.getAssets().list("svg");
		} catch (IOException e) {
			return;
		}

		getLstData(svgFiles);
		MainAdapter adapter = new MainAdapter(this);
		setListAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Intent intent = new Intent(MainActivity.this, SvgActivity.class);

		Bundle bundle = new Bundle();
		bundle.putString("svg", (String) lstData.get(position).get("info"));
		intent.putExtra("svgInfo", bundle);

		startActivity(intent);

	}

	private void getLstData(String[] svgFiles) {
		this.lstData = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		Properties properties = new Properties();
		for (String svgFile : svgFiles) {
			map = new HashMap<String, Object>();
			map.put("info", svgFile);
			try {
				properties.load(this.getAssets().open(
						"svg/" + svgFile + "/description.properties"));
				map.put("title", properties.getProperty("name"));
				map.put("img",
						Util.getPropertyValue(R.drawable.class,
								properties.getProperty("img")));
			} catch (IOException e) {
			}

			this.lstData.add(map);
		}
	}

	public final class ViewHolder {
		public ImageView img;
		public TextView title;
	}

	class MainAdapter extends BaseAdapter {

		private LayoutInflater mInflater = null;

		public MainAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return lstData.size();
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();

				convertView = mInflater.inflate(R.layout.activity_main, null);
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.img.setBackgroundResource((Integer) lstData.get(position)
					.get("img"));
			holder.title.setText((String) lstData.get(position).get("title"));

			return convertView;
		}
	}

}
