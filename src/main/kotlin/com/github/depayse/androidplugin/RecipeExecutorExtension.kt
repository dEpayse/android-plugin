package com.github.depayse.androidplugin

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.depayse.androidplugin.mvvm.file_content.generateActivityKtWithLayout
import com.github.depayse.androidplugin.mvvm.file_content.generateActivityLayout
import com.github.depayse.androidplugin.mvvm.file_content.generateAndroidManifest
import com.github.depayse.androidplugin.mvvm.file_content.generateViewModel

fun RecipeExecutor.generateActivity(
    moduleData: ModuleTemplateData,
    packageName: String,
    activityName: String,
    viewModelName: String,
    generateViewModel: Boolean,
    isLauncher: Boolean,
    layoutName: String
) {
    // project 의 데이터, source code/resource/manifest 의 경로
    val (projectTemplateData, srcDir, resDir, manifestDir) = moduleData

    addAllKotlinDependencies(moduleData)

    // activity, layout 경로
    val activityPath = srcDir.resolve("${activityName}.kt")
    val layoutPath = resDir.resolve("layout/${layoutName}.xml")
    val viewModelPath = srcDir.resolve("${viewModelName}.kt")

    val androidManifestFileName = "AndroidManifest.xml"
    // AndroidManifest.xml 에 내용 병합
    mergeXml(
        generateAndroidManifest(packageName, activityName, isLauncher),
        manifestDir.resolve(androidManifestFileName)
    )

    // activity 코틀린 파일 저장
    save(
        generateActivityKtWithLayout(
            namespace = moduleData.namespace,
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
    if (generateViewModel) {
        // viewModel 파일 저장
        save(
            generateViewModel(
                packageName = packageName,
                viewModelName = viewModelName
            ),
            viewModelPath
        )
    }
    // activity, layout, viewmodel 파일을 열어 화면에 표시
    open(activityPath)
    open(layoutPath)
    open(viewModelPath)
}