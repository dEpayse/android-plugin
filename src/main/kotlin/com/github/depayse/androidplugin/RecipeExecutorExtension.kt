package com.github.depayse.androidplugin

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.depayse.androidplugin.mvvm.file_content.generateActivityKt
import com.github.depayse.androidplugin.mvvm.file_content.generateActivityKtWithLayout
import com.github.depayse.androidplugin.mvvm.file_content.generateActivityLayout
import com.github.depayse.androidplugin.mvvm.file_content.generateAndroidManifest

fun RecipeExecutor.generateActivity(
    moduleData: ModuleTemplateData,
    packageName: String,
    activityName: String,
    generateLayout: Boolean,
    isLauncher: Boolean,
    layoutName: String
) {
    // project 의 데이터, source code/resource/manifest 의 경로
    val (projectTemplateData, srcDir, resDir, manifestDir) = moduleData

    addAllKotlinDependencies(moduleData)

    // activity, layout 경로
    val activityPath = srcDir.resolve("${activityName}.kt")
    val layoutPath = resDir.resolve("layout/${layoutName}.xml")

    val androidManifestFileName = "AndroidManifest.xml"
    // AndroidManifest.xml 에 내용 병합
    mergeXml(
        generateAndroidManifest(packageName, activityName, isLauncher),
        manifestDir.resolve(androidManifestFileName)
    )

    // layout 생성 여부
    if (generateLayout) {
        // activity 코틀린 파일 저장
        save(
            generateActivityKtWithLayout(
                packageName = packageName,
                activityName = activityName,
                layoutName = layoutName
            ),
            activityPath
        )
        // layout 파일 저장
        save(
            generateActivityLayout(
                packageName = packageName,
                activityName = activityName
            ),
            layoutPath
        )
    } else {
        // activity 코틀린 파일만 저장
        save(
            generateActivityKt(
                packageName = packageName,
                activityName = activityName
            ),
            activityPath
        )
    }

    // activity, layout 파일을 열어 화면에 표시
    open(activityPath)
    if (generateLayout) open(layoutPath)
}