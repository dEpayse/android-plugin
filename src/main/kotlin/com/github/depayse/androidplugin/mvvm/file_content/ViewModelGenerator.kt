package com.github.depayse.androidplugin.mvvm.file_content

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun generateViewModel(
    packageName: String,
    viewModelName: String
) = """
package ${escapeKotlinIdentifier(packageName)}

import com.passorder.core_ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class $viewModelName: BaseViewModel() {
    
}

"""