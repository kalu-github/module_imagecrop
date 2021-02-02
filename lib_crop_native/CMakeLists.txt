cmake_minimum_required(VERSION 3.4.1)
# set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -frtti -fexceptions")

enable_language(C CXX ASM)
set(CMAKE_ASM_COMPILER_TARGET "${CMAKE_CXX_COMPILER_TARGET}")

# 编译libjpegturbo
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/output/${ANDROID_ABI})
add_subdirectory(src/main/cpp/libjpegturbo)
add_library(libjpegturbo STATIC IMPORTED)
set_target_properties(libjpegturbo
        PROPERTIES
        IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/output/${ANDROID_ABI}/libturbojpeg.a)

add_library(
        crop
        SHARED

        # libpng
        src/main/cpp/libpng/arm/arm_init.c
        src/main/cpp/libpng/arm/filter_neon.S
        src/main/cpp/libpng/arm/filter_neon_intrinsics.c
        src/main/cpp/libpng/arm/palette_neon_intrinsics.c
        src/main/cpp/libpng/example.c
        src/main/cpp/libpng/png.c
        src/main/cpp/libpng/png.h
        src/main/cpp/libpng/pngconf.h
        src/main/cpp/libpng/pngdebug.h
        src/main/cpp/libpng/pngerror.c
        src/main/cpp/libpng/pngget.c
        src/main/cpp/libpng/pnginfo.h
        src/main/cpp/libpng/pnglibconf.h
        src/main/cpp/libpng/pngmem.c
        src/main/cpp/libpng/pngpread.c
        src/main/cpp/libpng/pngpriv.h
        src/main/cpp/libpng/pngpread.c
        src/main/cpp/libpng/pngread.c
        src/main/cpp/libpng/pngrio.c
        src/main/cpp/libpng/pngrtran.c
        src/main/cpp/libpng/pngrutil.c
        src/main/cpp/libpng/pngset.c
        src/main/cpp/libpng/pngstruct.h
        src/main/cpp/libpng/pngtrans.c
        src/main/cpp/libpng/pngwio.c
        src/main/cpp/libpng/pngwrite.c
        src/main/cpp/libpng/pngwtran.c
        src/main/cpp/libpng/pngwutil.c

        # libcrop
        src/main/cpp/CImg.h
        src/main/cpp/crop_turbo.cpp
        src/main/cpp/com_yalantis_ucrop_task_BitmapCropTask.h
)

target_link_libraries(
        crop
        log
        z
        libjpegturbo
)