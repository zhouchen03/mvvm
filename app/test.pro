-keepattributes LocalVariableTable
-verbose

# for testing
-keep class org.jacoco.** { *; }
-keep interface org.jacoco.** { *; }
-keepclassmembers class * {
    *** $jacocoData;
    *** $jacocoInit();
}
