# AndroidOpenBLAS

Android test app for JavaCPP + OpenBLAS


# OpenBLAS javacpp preset build

These instructions only apply if you are going to build the OpenBLAS preset, otherwise, you can use the available .jar files in [app/libs](https://github.com/jadarve/AndroidOpenBLAS/tree/master/app/libs).

## Android NDK preparation

Follow the instructions at https://github.com/bytedeco/javacpp-presets/wiki/Build-Environments#android-arm-and-x86 to patch the Android NDK to support Fortran.

## Install javacpp

```
git clone https://github.com/bytedeco/javacpp.git
cd javacpp
mvn install
```

## android-x86

```
export ANDROID_NDK=~/Android/Sdk/ndk-bundle

bash cppbuild.sh -platform android-x86 install openblas

mvn clean install -Djavacpp.platform=android-x86 -Djavacpp.platform.root=$ANDROID_NDK -Djavacpp.platform.compiler=$ANDROID_NDK/toolchains/x86-4.9/prebuilt/linux-x86_64/bin/i686-linux-android-gcc --projects .,openblas
```


## android-armv7

```
export ANDROID_NDK=~/Android/Sdk/ndk-bundle

bash cppbuild.sh -platform android-arm install openblas

mvn clean install -Djavacpp.platform=android-arm -Djavacpp.platform.root=$ANDROID_NDK -Djavacpp.platform.compiler=$ANDROID_NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/bin/arm-linux-androideabi-gcc --projects .,openblas
```


