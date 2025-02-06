package com.github.depayse.androidplugin.mvvm.file_content

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import com.github.depayse.androidplugin.util.layoutToBindingActivity

/**
 * Generates the content of a new Activity class in Kotlin
 *
 * @param packageName 파일들이 위치할 패키지명
 * @param namespace 모듈의 namespace
 * @param activityName Activity 이름
 * @param viewModelName ViewModel 이름
 * @param layoutName Layout 이름
 */
fun generateActivityKt(
    packageName: String,
    namespace: String,
    activityName: String,
    generateViewModel: Boolean,
    viewModelName: String,
    layoutName: String
) = if (generateViewModel) {
    """
package ${escapeKotlinIdentifier(packageName)}

import android.os.Bundle
import $namespace.R
import $namespace.databinding.${layoutToBindingActivity(layoutName)}
import ${packageName}.$viewModelName
import androidx.activity.viewModels
import com.passorder.core_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class $activityName : BaseActivity<${layoutToBindingActivity(layoutName)}>(R.layout.${layoutName}) {
    private val viewModel by viewModels<$viewModelName>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
"""
}
else {
    """
package ${escapeKotlinIdentifier(packageName)}

import android.os.Bundle
import $namespace.R
import $namespace.databinding.${layoutToBindingActivity(layoutName)}
import com.passorder.core_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class $activityName : BaseActivity<${layoutToBindingActivity(layoutName)}>(R.layout.${layoutName}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
"""
}
