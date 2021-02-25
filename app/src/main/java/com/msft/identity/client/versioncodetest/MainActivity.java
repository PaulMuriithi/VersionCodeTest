package com.msft.identity.client.versioncodetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.PackageInfoCompat;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode; // deprecated approach
            long longVersionCode1 = packageInfo.getLongVersionCode(); // new approach in Apis >= 28
            long longVersionCode2 = PackageInfoCompat.getLongVersionCode(packageInfo); // backward compat approach
            int versionCodeFromLongVersionCode1 = (int) longVersionCode1;
            int versionCodeFromLongVersionCode2 = (int) (longVersionCode1 & 0x00000000ffffffff);
            long majorVersionCode = longVersionCode1 >> 32;
            Log.d("Main", "versionCode: " + versionCode + ", longVersionCode1: " + longVersionCode1 + ", longVersionCode2: " + longVersionCode2
                    + ", versionCodeFromLongVersionCode1: " + versionCodeFromLongVersionCode1 + ", versionCodeFromLongVersionCode2: " + versionCodeFromLongVersionCode2 + ", majorVersionCode: " + majorVersionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
    }
}