package com.github.depayse.androidplugin.mvvm

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.depayse.androidplugin.mvvm.template.mvvmActivityTemplate

class GenerateActivityTemplateProvider: WizardTemplateProvider() {
    override fun getTemplates(): List<Template> {
        return listOf(mvvmActivityTemplate())
    }
}