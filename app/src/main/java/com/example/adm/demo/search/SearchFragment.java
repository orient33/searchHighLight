package com.example.adm.demo.search;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adm.demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adm on 2017/10/13.
 */

public class SearchFragment extends Fragment {

    public SearchFragment() {
    }

    @BindView(R.id.et)
    EditText editText;

    @BindView(R.id.listView)
    ListView listView;
    private SearchPresenter presenter;
    private Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        presenter = new SearchPresenter(this);
        editText.addTextChangedListener(watcher);
        listView.setAdapter(adapter = new Adapter());
    }

    public void onSearchComplete(String key, List<Contacts> data) {
        adapter.setData(data);
    }

    final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.e("df", "afterTextChanged  " + s);
            presenter.getFilter().filter(s.toString());
        }
    };

    private class Adapter extends BaseAdapter {
        final List<Contacts> data = new ArrayList<>();

        void setData(List<Contacts> d) {
            data.clear();
            if (d != null && d.size() > 0)
                data.addAll(d);
            notifyDataSetChanged();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Contacts getItem(int position) {
            return data.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = new TextView(getActivity());
                tv.setTextSize(20);
            } else {
                tv = (TextView) convertView;
            }
            Contacts item = getItem(position);
            if (item.mMatchStart != -1 && item.mMatchEnd != -1) {
                if (item.mMatchStart < Contacts.MATCH_NAME_BASE) {
                    SpannableString number = new SpannableString(item.getNumber());
                    number.setSpan(new ForegroundColorSpan(0xffff0000), item.mMatchStart, item.mMatchEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    tv.setText(number);
                    tv.append("-" + item.getName());
                } else {//
                    SpannableString name = new SpannableString(item.getName());
                    name.setSpan(new ForegroundColorSpan(0xffff8888), item.mMatchStart - Contacts.MATCH_NAME_BASE, item.mMatchEnd - Contacts.MATCH_NAME_BASE, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    tv.setText(item.getNumber() + "-");
                    tv.append(name);
                }
            } else {
                tv.setText(item.getNumber() + " - " + item.getName());
            }
            return tv;
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }
}
