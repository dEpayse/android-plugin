package com.github.depayse.androidplugin.mvvm.file_content

fun generateAndroidManifest(
    packageName: String,
    activityName: String,
    isLauncher: Boolean
) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application>

        <activity
            android:name="${packageName}.$activityName"
            android:screenOrientation="portrait"
            tools:ignore="DiscouragedApi"
            android:exported="$isLauncher"${
    if (isLauncher) {
        ">\n" +
                "            <intent-filter>\n" +
                "                <action android:name=\"android.intent.action.MAIN\" />\n" +
                "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" +
                "            </intent-filter>\n" +
                "        </activity>"
    } else {
        " />"
    }
}
    
    </application>

</manifest>

"""