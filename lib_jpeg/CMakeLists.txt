#设置cmake最小支持版本
cmake_minimum_required(VERSION 3.4.1)

# C的编译选项是 CMAKE_C_FLAGS
# 指定编译参数，可选
# SET(CMAKE_CXX_FLAGS "-Wno-error=format-security -Wno-error=pointer-sign")

# 设置生成的so动态库最后输出的路径
# set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})


#设置头文件搜索路径（和此txt同个路径的头文件无需设置），可选
include_directories(src/main/cpp/include/)

add_library( # Sets the name of the library.
             image-compress
             # Sets the library as a shared library.
             SHARED
             # Provides a relative path to your source file(s).
             src/main/cpp/image-compress.cpp )

set(distribution_DIR   ${CMAKE_SOURCE_DIR}/libs/)


find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

find_library( # Sets the name of the path variable.
              android-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              android )

#lib-image-jpeg.a
add_library(image-jpeg STATIC IMPORTED)
set_target_properties( image-jpeg
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libimage-jpeg.a )

target_link_libraries( # Specifies the target library.
                       image-compress
                       jnigraphics
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib}
                       ${android-lib}
                       image-jpeg )