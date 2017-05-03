package com.gws.android.tips.domain;

import java.util.Date;

/**
 * Created by gws on 2017/5/3.
 */

public class Tip {
    private String id;
    private String title;
    private String content;
    private String creatTime = new Date().toString();
    private String updateTime;
}
