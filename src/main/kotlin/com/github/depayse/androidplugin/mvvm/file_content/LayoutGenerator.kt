package com.github.depayse.androidplugin.mvvm.file_content

fun generateActivityLayout(
    packageName: String,
    activityName: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
            name="activity"
            type="${packageName}.${activityName}" />

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#FFFFFF"
        tools:context="${packageName}.${activityName}">

    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
"""