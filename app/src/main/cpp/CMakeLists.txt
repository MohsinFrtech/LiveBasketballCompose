# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

# Declares and names the project.

project("cppproject")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        cppproject

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        file1.cpp
        file2.cpp
        file3.cpp
        file4.cpp
        file5.cpp
        file6.cpp
        file7.cpp
        file8.cpp
        file9.cpp
        file10.cpp
        file11.cpp
        file12.cpp
        file13.cpp
        file14.cpp
        file15.cpp
        file16.cpp
        file17.cpp
        file18.cpp
        file19.cpp
        file20.cpp
        file21.cpp
        file22.cpp
        file23.cpp
        file24.cpp
        file25.cpp
        file26.cpp
        file27.cpp
        file28.cpp
        file29.cpp
        file30.cpp
        file31.cpp
        file32.cpp
        file33.cpp
        file34.cpp
        file35.cpp
        file36.cpp
        file37.cpp
        file38.cpp
        file39.cpp
        file40.cpp
        )


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        cppproject

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})