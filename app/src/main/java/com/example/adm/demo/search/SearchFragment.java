package com.example.adm.demo.search;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

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
    private ListView listView;
    private SearchPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        presenter = new SearchPresenter(this);
        editText.addTextChangedListener(watcher);
    }

    public void onSearchComplete(String key, List<Contacts> data) {

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

    class Adapter extends BaseAdapter {
        final List<Contacts> data = new ArrayList<>();

        void setData(List<Contacts> d) {
            data.clear();
            data.addAll(d);
            notifyDataSetChanged();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
