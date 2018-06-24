package com.emmanuelmess.simpleaccounting;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.emmanuelmess.simpleaccounting.activities.dialogs.CurrencyPicker;
import com.emmanuelmess.simpleaccounting.utils.TinyDB;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

public class MainActivityTestWithCurrency extends MainActivityTest {

    @Override
    public void startSetUp() {
        super.startSetUp();
        TinyDB tinyDB = new TinyDB(context);
        tinyDB.putListString(CurrencyPicker.KEY, new ArrayList<>(Arrays.asList("ARG", "U$D", "--", "fdfasd")));
    }

    @Test
    public void testCurrency() {
        fab.callOnClick();

        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        shadowOf(activity).onCreateOptionsMenu(toolbar.getMenu());

        Menu menu = shadowOf(activity).getOptionsMenu();
        MenuItem currencyItem = menu.findItem(R.id.action_currency);
        Spinner currencyPicker = (Spinner) currencyItem.getActionView();
        shadowOf(currencyPicker).performItemClick(1);

        assertEquals(1, table.getChildCount());

        shadowOf(currencyPicker).performItemClick(0);

        assertEquals(2, table.getChildCount());
    }

}
