package com.gws.android.tips.presentation.model;

import java.util.Date;

/**
 * Created by gws on 2017/5/3.
 */

public class TipModel {
    private String id;
    private String title;
    private String content;
    private String creatTime = new Date().toString();
    private String updateTime;
}
