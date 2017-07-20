package com.android.internal.util.viper;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

public class Helpers {
    // avoids hardcoding the tag
    private static final String TAG = Thread.currentThread().getStackTrace()[1].getClassName();
    private static final String INTENT_RESTART_SYSTEMUI = "restart_systemui";

    public static void restartSystemUI(Context context) {
        try {
            context.sendBroadcastAsUser(new Intent(INTENT_RESTART_SYSTEMUI), new UserHandle(UserHandle.USER_ALL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
