package com.example.practice;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeUtil {
    public static final String LIGHT_MODE ="light";
    public static final String DARK_MODE ="dark";
    public static final String DEFAULT_MODE ="default";

    private static final String TAG ="ThemeUtil";

    public static void applyTheme(String themecolor){
        switch (themecolor) {
            case LIGHT_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Log.d(TAG,"라이트 모드 적용됨");
                break;

            case DARK_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Log.d(TAG,"다크 모드 적용됨");
                break;
        }
    }
    static SharedPreferences sp;
    public static void modSave(Context context,String select_mod) {
        sp = context.getSharedPreferences("mod",context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();
        editor.putString("mod",select_mod);
        editor.commit();
    }
    public static String modLoad(Context context) {

        sp = context.getSharedPreferences("mod",context.MODE_PRIVATE);
        String load_mod= sp.getString("mod","light");
        return load_mod;
    }


}
