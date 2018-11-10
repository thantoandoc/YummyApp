package com.team.ymmy.model;

import com.team.ymmy.interfaces.ITable;
import com.team.ymmy.yummyapp.R;

/**
 * Created by Admin on 10/1/2018.
 */

public class TableHasCustomer implements ITable {

    @Override
    public int getImage() {
        return R.drawable.table_choose;
    }
}
