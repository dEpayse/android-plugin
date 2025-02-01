package com.github.depayse.androidplugin.mvvm.template

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageNameWidget
import com.android.tools.idea.wizard.template.StringParameter
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.activityToLayout
import com.android.tools.idea.wizard.template.booleanParameter
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import com.android.tools.idea.wizard.template.layoutToActivity
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template
import com.github.depayse.androidplugin.generateActivity


fun mvvmActivityTemplate() = template {
    name = "Custom Activity"
    description = "Activity 생성"
    minApi = 21

    // 우클릭시 메뉴가 표시 될 위치, New>Activity>Custom Activity 로 표시
    category = Category.Activity
    // 하드웨어 종류, Mobile, Wear, Tv, Automotive, Generic 총 5가지
    formFactor = FormFactor.Mobile
    // 표시 될 위치, 우클릭시 Activity 메뉴 아래와 Activity>Gallery 선택 시 맨 끝, 신규 프로젝트나 모듈 생성시 표시
    screens = listOf(
        WizardUiContext.ActivityGallery,
        WizardUiContext.MenuEntry,
        WizardUiContext.NewProject,
        WizardUiContext.NewModule
    )

    // 기본 패키지명을 저장
    val packageName = defaultPackageNameParameter
    lateinit var layoutName: StringParameter
    // activity 이름을 받기 위한 string parameter
    val activityName = stringParameter {
        name = "Activity 이름"
        default = "MainActivity"
        constraints = listOf(Constraint.ACTIVITY, Constraint.NONEMPTY)
        // layout 이름으로 activity 이름 제안
        suggest = { layoutToActivity(layoutName.value) }
    }
    // layout 을 구성하는 xml 파일을 만들지 체크하기 위한 boolean parameter
    val generateLayout = booleanParameter {
        name = "layout file 생성 여부"
        default = true
    }
    // layout 이름을 받을 string parameter 로 위의 boolean 값에 따라 화면 표시 여부 추가
    layoutName = stringParameter {
        name = "Layout 이름"
        visible = { generateLayout.value }
        default = "activity_main"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY, Constraint.UNIQUE)
        // activity 이름으로 layout 이름 제안
        suggest = { activityToLayout(activityName.value) }
    }
    // launcher activity 설정
    val isLauncher = booleanParameter {
        name = "Launcher Activity"
        default = false
        help = "선택하면 CATEGORY_LAUNCHER intent filter 가 추가 됩니다."
    }

    // 화면 구성
    widgets(
        TextFieldWidget(activityName),
        CheckBoxWidget(generateLayout),
        TextFieldWidget(layoutName),
        CheckBoxWidget(isLauncher),
        PackageNameWidget(packageName)
    )

    // recipe 를 사용하여 template 의 결과물 생성
    recipe = { data: TemplateData ->
        generateActivity(
            moduleData = data as ModuleTemplateData,
            packageName = packageName.value,
            activityName = activityName.value,
            generateLayout = generateLayout.value,
            isLauncher = isLauncher.value,
            layoutName = layoutName.value
        )
    }
}
