package com.example.li.contacts;

import android.content.Context;
import android.database.Cursor;

import static android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
import static android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER;
import static android.provider.ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

/**
 * Created by Li on 2016/10/11 0011.
 */

public class GetNumber {
    public static String getNumber(Context context) {
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        String phoneNumber;
        String phoneName;
        while (cursor.moveToNext())
        {
            phoneNumber = cursor.getString(cursor.getColumnIndex(NUMBER));
            phoneName = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            System.out.println("Number:" + phoneNumber);
            System.out.println("Name:" + phoneName);
        }
        return null;
    }
}
