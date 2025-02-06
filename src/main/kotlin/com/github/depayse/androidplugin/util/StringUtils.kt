package com.github.depayse.androidplugin.util

import java.util.Locale

fun snakeCaseToPascalCase(snakeCase: String): String {
    return snakeCase
        .split("_")
        .joinToString("") {
            it.replaceFirstChar { firstChar ->
                if (firstChar.isLowerCase()) firstChar.titlecase(Locale.getDefault()) else firstChar.toString()
            }
        }
}