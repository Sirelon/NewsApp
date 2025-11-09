# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep annotation metadata that Kotlin reflection-based libraries need.
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault,InnerClasses,EnclosingMethod

# ----- kotlinx.serialization -----
# Keep generated serializer instances and related runtime lookups without pinning every app class.
-keepclassmembers class * implements kotlinx.serialization.KSerializer {
    <init>(...);
}
-keepclasseswithmembers class * {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep class kotlinx.serialization.internal.** { *; }
-keep class kotlinx.serialization.SerializationKt { *; }
-dontwarn kotlinx.serialization.**

# ----- Koin dependency injection -----
-keep class org.koin.** { *; }
-dontwarn org.koin.**

# ----- Ktor HTTP client -----
-dontwarn io.ktor.**
