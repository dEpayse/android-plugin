package com.github.depayse.androidplugin.mvvm.file_content

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import com.android.tools.idea.wizard.template.layoutToActivity

fun generateActivityKt(
    packageName: String,
    activityName: String
) = """
package ${escapeKotlinIdentifier(packageName)}

import com.example.testapplication.BaseActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class $activityName : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        // unused remove
                    },
                    bottomBar = {
                        // unused remove
                    },
                    containerColor = colorResource(id = R.color.contents_bg)
                ) { contentPadding ->
                    TODO("Not yet implemented")
                }
            }
        }
    }
}
"""


fun generateActivityKtWithLayout(
    packageName: String,
    activityName: String,
    layoutName: String
) = """
package ${escapeKotlinIdentifier(packageName)}

import com.example.testapplication.R
import com.example.testapplication.databinding.${layoutToActivity(layoutName)}Binding
import com.example.testapplication.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class $activityName : BaseActivity() {

    private val binding by binding<${layoutToActivity(layoutName)}Binding>(R.layout.${layoutName})
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            TODO("Not yet implemented")
        }
    }
}
"""
