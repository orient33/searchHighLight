package com.example.adm.demo.search;

import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Adm on 2017/10/13.
 */

public class SearchPresenter implements Filterable {

    final SearchFragment view;

    public SearchPresenter(SearchFragment fragment) {
        view = fragment;
    }

    @Override
    public Filter getFilter() {
        return new ContactsFilter();
    }

    private static boolean isListContain(List<Contacts> contactsList, Contacts contacts) {
        if (contactsList == null || contactsList.size() == 0 || contacts == null)
            return false;

        for (Contacts item : contactsList) {
            if (item.getNumber().equals(contacts.getNumber()))
                return true;
        }

        return false;
    }

    private class ContactsFilter extends Filter {
        private final List<Contacts> mContactsList1 = new ArrayList<>();

        ContactsFilter() {
//            mContactsList1 = BTStorage.getInstance().getCallLog(BTGlobal.getConnectedDevice_call());
//            mContactsList2 = BTStorage.getInstance().getPhoneBook(BTGlobal.getConnectedDevice_call());
            if (mContactsList1.size() == 0)
                fakeData(mContactsList1);
        }

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (TextUtils.isEmpty(prefix)) {
                return results;
            }

            if ((mContactsList1 == null || mContactsList1.size() == 0)) {
                return results;
            }

            String prefixString = prefix.toString().toLowerCase(Locale.getDefault());

            List<Contacts> filteredValue = new ArrayList<>();

            List<Contacts> numberMatchList = new ArrayList<>();
            List<Contacts> markMatchPartList = new ArrayList<>();

            // 查找通话记录
            for (Contacts contacts : mContactsList1) {
                if (contacts != null) {
                    String number = contacts.getNumber();
                    String mark = contacts.getMark();

                    number = PhoneNumberUtils.process(number);

                    if (number != null && number.contains(prefixString)) {
                        contacts.mMatchStart = number.indexOf(prefixString);
                        contacts.mMatchEnd = prefixString.length() + contacts.mMatchStart;
                        if (!isListContain(numberMatchList, contacts))
                            numberMatchList.add(contacts);
//                    } else if (mark != null && mark.equals(prefixString)) {
//                        if (!isListContain(markMatchTotalList, contacts))
//                            markMatchTotalList.add(contacts); // 全部匹配--这个没必要
                    } else if (mark != null && mark.contains(prefixString)) {
                        contacts.mMatchStart = mark.indexOf(prefixString) + Contacts.MATCH_NAME_BASE;
                        contacts.mMatchEnd = contacts.mMatchStart + prefixString.length();
                        if (!isListContain(markMatchPartList, contacts))
                            markMatchPartList.add(contacts); // 部分匹配
                    }
                }
            }

            filteredValue.addAll(numberMatchList);
            filteredValue.addAll(markMatchPartList);

            results.values = filteredValue;
            results.count = filteredValue.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            final List<Contacts> dataList = (List<Contacts>) results.values;
            //mView.showFilteredList(dataList);
//            EventBus.getDefault().post(new BTPhoneSearchResultUpdateEvent(dataList));
            view.onSearchComplete(constraint + "", dataList);
        }
    }

    private static void fakeData(List<Contacts> call) {
        Contacts contact = Contacts.getFake1();
        call.add(contact);
        for (int i = 0; i < 10; ++i) {
            Contacts other = contact.cloneSame();
            other.setName(other.getName() + i);
            other.setNumber(other.getNumber() + i);
            other.setMark(other.getMark() + i);
            call.add(other);
        }
    }
}
