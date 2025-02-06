package com.github.depayse.androidplugin.mvvm.file_content

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import com.android.tools.idea.wizard.template.layoutToActivity
import com.github.depayse.androidplugin.util.layoutToBindingActivity

/**
 * Generates the content of a new Activity class in Kotlin
 *
 * @param packageName 파일들이 위치할 패키지명
 * @param namespace 모듈의 namespace
 * @param activityName Activity 이름
 * @param layoutName Layout 이름
 */
fun generateActivityKtWithLayout(
    packageName: String,
    namespace: String,
    activityName: String,
    layoutName: String
) = """
package ${escapeKotlinIdentifier(packageName)}

import $namespace.R
import $namespace.databinding.${layoutToActivity(layoutName)}Binding
import com.passorder.core_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class $activityName : BaseActivity<${layoutToBindingActivity(layoutName)}>(R.layout.${layoutName}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
"""
