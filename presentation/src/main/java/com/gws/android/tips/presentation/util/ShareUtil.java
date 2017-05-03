package com.gws.android.tips.presentation.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by jara on 2017-3-24.
 */

public class ShareUtil {

    private static final String EMAIL_ADDRESS = "";

    public static void shareText(Context context, String text, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, title));
    }

    public static void shareImage(Context context, Uri uri, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, title));
    }

    public static void shareEmail(Context context, String title) {
        Intent intent = new Intent(Intent.ACTION_SENDTO
                , Uri.parse("mailto:" + EMAIL_ADDRESS));
        context.startActivity(Intent.createChooser(intent, title));
    }

}
