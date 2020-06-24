#设置cmake最小支持版本
cmake_minimum_required(VERSION 3.4.1)

# C的编译选项是 CMAKE_C_FLAGS
# 指定编译参数，可选
# SET(CMAKE_CXX_FLAGS "-Wno-error=format-security -Wno-error=pointer-sign")

# 设置生成的so动态库最后输出的路径
# set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/../../jniLibs/${ANDROID_ABI})

add_library( # Sets the name of the library.
        kalu-image
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        compress.cpp)

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

#libjpeg.so
add_library(jpeg-lib SHARED IMPORTED)

set_target_properties(
        jpeg-lib
        PROPERTIES
        IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../../../../output/jpeg/jni/${ANDROID_ABI}/libjpeg.so)

target_link_libraries(

        # so
        kalu-image

        # Specifies the target library.
        jnigraphics

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib}
        ${android-lib}

        # Specifies the target library.
        jpeg-lib
)
