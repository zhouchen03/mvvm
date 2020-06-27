# mvvm and app bundle with dynamic feature
mvvm
coroutines
rxjava
dagger2
espresso
unit test

1. build
1.1 app bundle build from command line
./gradlew app:bundleDebug
1.2 app apk build from command line
./gradlew app:assembleDebug

2. AndroidTest
2.1 setup emulator
Emulator name: Pixel 3a API 28
launch emulator before the testing

2.2 run espresso test
./gradlew app:connectedAndroidTest --no-parallel
./gradlew feature_sample:connectedAndroidTest --no-parallel

2.2 run all espresso test
./gradlew connectedAndroidTest --no-parallel

3 unit test all with coverage report
3.1 run all unit tests
./gradlew clean jacocoUnitTestModuleReportDebug

3.2 merge all test reports and generate coverage report
./gradlew jacocoUnitTestMergedCoverageReport
The report is under {root}/build/reports/jacoco/

./gradlew jacocoUnitTestMergeReportForAndroidTest
It is under {root}/build/jacoco/mergedReport.exec

4. combine android test with unit test coverage report
4.1 run espresso test with coverage report
./gradlew -Pcoverage connectedAndroidTest --no-parallel

4.2 merge test report
./gradlew jacocoAutomationCoverageReport
it will combine unit test report in mergedReport.exec
with all the *.ec espresso test reports together
The html report is under {root}/build/reports/jacoco/jacocoAutomationCoverageReport/html