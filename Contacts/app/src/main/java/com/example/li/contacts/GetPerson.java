package com.example.li.contacts;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
import static android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER;
import static android.provider.ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

/**
 * Created by Li on 2016/10/11 0011.
 */

public class GetPerson {
    public static ArrayList<Person> persons = new ArrayList<>();

    public static ArrayList<Person> getPerson(Context context) {

        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        String phoneNumber;
        String phoneName;
        while (cursor.moveToNext())
        {
            phoneNumber = cursor.getString(cursor.getColumnIndex(NUMBER));
            phoneName = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            persons.add(new Person(phoneName, phoneNumber));
            System.out.println("Name:" + phoneName);
            System.out.println("Number:" + phoneNumber);
        }
        return persons;
    }
}
