// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("compose_compiler_version", "1.5.2")
        set("lifecycle_version", "2.6.2")
        set("retrofit2_version", "2.9.0")
    }
}

plugins {
    id("com.android.application") version "8.2.1" apply false
    id("com.android.library") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}
