/*
 * Copyright (C) 2017 ViperOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.util.viper;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;

import java.util.List;
import java.util.Locale;

import static android.content.Context.ACTIVITY_SERVICE;

public class ViperUtils {

    public static boolean isChineseLanguage() {
        return Resources.getSystem().getConfiguration().locale.getLanguage().startsWith(
                Locale.CHINESE.getLanguage());
    }

    public static boolean isLauncherShown(Context context){
        try{
            ActivityManager am =(ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
            ActivityManager.RunningTaskInfo task = tasks.get(0); // current task
            ComponentName rootActivity = task.baseActivity;
            final PackageManager pm = context.getPackageManager();
            // package manager is provider of all the application information
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_HOME);
            List<ResolveInfo> appList = pm.queryIntentActivities(mainIntent, 0);
            for (ResolveInfo info : appList) {
                if (info.activityInfo.packageName.equals(rootActivity.getPackageName())){
                    return true;
                }
            }
        }catch (Exception ex){

        }
        return false;
    }
}