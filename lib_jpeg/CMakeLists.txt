cmake_minimum_required(VERSION 3.4.1)

# C的编译选项是 CMAKE_C_FLAGS
# 指定编译参数，可选
# SET(CMAKE_CXX_FLAGS "-Wno-error=format-security -Wno-error=pointer-sign")

add_subdirectory(src/main/cpp)

# create by kalu on custom
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/output/jpeg-android/${ANDROID_ABI})
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/output/jpeg-android/${ANDROID_ABI})
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/output/jpeg-android/${ANDROID_ABI})

add_library( # Sets the name of the library.
        jpeg-android
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        src/main/cpp/customlib/compress.cpp)

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

find_library( # Sets the name of the path variable.
        android-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        android)


#libjpeg.a
add_library(jpeg-core STATIC IMPORTED)

set_target_properties(
        jpeg-core
        PROPERTIES
        IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/output/jpeg/${ANDROID_ABI}/libjpeg.a)

target_link_libraries(
        # so
        jpeg-android

        # Specifies the target library.
        jnigraphics

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib}
        ${android-lib}

        # Specifies the target library.
        jpeg-core
)
