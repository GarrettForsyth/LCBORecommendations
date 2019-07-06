import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project


class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.all {
            when (this) {
                is LibraryPlugin -> {
                    val extension = project.extensions.getByType(
                        LibraryExtension::class.java
                    )
                    extension.configureLibrary()
                }
                is AppPlugin -> {
                    val extension = project.extensions.getByType(
                        AppExtension::class.java)
                    extension.configureLibrary()
                }
            }
        }
    }
}

private fun LibraryExtension.configureLibrary() {
    setCompileSdkVersion(Config.compiledSdkVersion)
    defaultConfig.apply {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
    }

    sourceSets {
        val sharedTestDir = "src/sharedTest/java"
        findByName("test")?.java?.srcDir(sharedTestDir)
        findByName("androidTest")?.java?.srcDir(sharedTestDir)
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding.isEnabled = true
}

private fun AppExtension.configureLibrary() {
    setCompileSdkVersion(Config.compiledSdkVersion)
    defaultConfig.apply {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
    }

    sourceSets {
        val sharedTestDir = "src/sharedTest/java"
        findByName("test")?.java?.srcDir(sharedTestDir)
        findByName("androidTest")?.java?.srcDir(sharedTestDir)
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding.isEnabled = true
}
