package com.github.depayse.androidplugin.util

// 레이아웃명을 바인딩 객체명으로 변경
fun layoutToBindingActivity(layoutName: String) =
    "${snakeCaseToPascalCase(layoutName)}Binding"

// 액티비티명을 ViewModel 이름으로 변경
fun activityToViewModel(activityName: String) =
    "${activityName.replace("Activity", "")}ViewModel"