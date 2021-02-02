cmake_minimum_required(VERSION 3.4.1)
# set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -frtti -fexceptions")

# 编译-libjpegturbo
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/output/${ANDROID_ABI})
add_subdirectory(src/main/cpp/libjpegturbo)
add_library(libjpegturbo STATIC IMPORTED)
set_target_properties(libjpegturbo
        PROPERTIES
        IMPORTED_LOCATION ${PROJECT_SOURCE_DIR}/output/${ANDROID_ABI}/libjpeg.a)

## 编译-libjpeg
#set(
#        libjpeg
#        src/main/cpp/libjpeg/jerror.h
#        src/main/cpp/libjpeg/jmorecfg.h
#        src/main/cpp/libjpeg/jpeglib.h
#        src/main/cpp/libjpeg/jconfig.h
#        src/main/cpp/libjpeg/jmemnobs.c
#        src/main/cpp/libjpeg/jaricom.c
#        src/main/cpp/libjpeg/jcapimin.c
#        src/main/cpp/libjpeg/jcapistd.c
#        src/main/cpp/libjpeg/jcarith.c
#        src/main/cpp/libjpeg/jccoefct.c
#        src/main/cpp/libjpeg/jccolor.c
#        src/main/cpp/libjpeg/jcdctmgr.c
#        src/main/cpp/libjpeg/jchuff.c
#        src/main/cpp/libjpeg/jcinit.c
#        src/main/cpp/libjpeg/jcmainct.c
#        src/main/cpp/libjpeg/jcmarker.c
#        src/main/cpp/libjpeg/jcmaster.c
#        src/main/cpp/libjpeg/jcomapi.c
#        src/main/cpp/libjpeg/jcparam.c
#        src/main/cpp/libjpeg/jcprepct.c
#        src/main/cpp/libjpeg/jcprepct.c
#        src/main/cpp/libjpeg/jcsample.c
#        src/main/cpp/libjpeg/jctrans.c
#        src/main/cpp/libjpeg/jdapimin.c
#        src/main/cpp/libjpeg/jdapistd.c
#        src/main/cpp/libjpeg/jdarith.c
#        src/main/cpp/libjpeg/jdatadst.c
#        src/main/cpp/libjpeg/jdatasrc.c
#        src/main/cpp/libjpeg/jdcoefct.c
#        src/main/cpp/libjpeg/jdcolor.c
#        src/main/cpp/libjpeg/jddctmgr.c
#        src/main/cpp/libjpeg/jdhuff.c
#        src/main/cpp/libjpeg/jdinput.c
#        src/main/cpp/libjpeg/jdmainct.c
#        src/main/cpp/libjpeg/jdmarker.c
#        src/main/cpp/libjpeg/jdmaster.c
#        src/main/cpp/libjpeg/jdmerge.c
#        src/main/cpp/libjpeg/jdpostct.c
#        src/main/cpp/libjpeg/jdsample.c
#        src/main/cpp/libjpeg/jdtrans.c
#        src/main/cpp/libjpeg/jerror.c
#        src/main/cpp/libjpeg/jfdctflt.c
#        src/main/cpp/libjpeg/jfdctfst.c
#        src/main/cpp/libjpeg/jfdctint.c
#        src/main/cpp/libjpeg/jidctflt.c
#        src/main/cpp/libjpeg/jidctfst.c
#        src/main/cpp/libjpeg/jidctint.c
#        src/main/cpp/libjpeg/jquant1.c
#        src/main/cpp/libjpeg/jquant2.c
#        src/main/cpp/libjpeg/jutils.c
#        src/main/cpp/libjpeg/jmemmgr.c
#        src/main/cpp/libjpeg/cderror.h
#        src/main/cpp/libjpeg/cdjpeg.h
#        src/main/cpp/libjpeg/jdct.h
#        src/main/cpp/libjpeg/jinclude.h
#        src/main/cpp/libjpeg/jmemsys.h
#        src/main/cpp/libjpeg/jpegint.h
#        src/main/cpp/libjpeg/jversion.h
#        src/main/cpp/libjpeg/transupp.h
#        src/main/cpp/libjpeg/jpegdll.def
#)

# 编译-libpng
set(
        libpng
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
)

set(
        libcrop
        src/main/cpp/crop_turbo.h
        src/main/cpp/crop_turbo.cpp
        src/main/cpp/com_yalantis_ucrop_task_BitmapCropTask.h
)
#set(
#        libcrop
#        src/main/cpp/crop.h
#        src/main/cpp/crop.cpp
#        src/main/cpp/com_yalantis_ucrop_task_BitmapCropTask.h
#)

add_library(
        crop
        SHARED
        ${libpng}
        ${libcrop}
)

target_link_libraries(
        crop
        log
        z
        libjpegturbo
)

#add_library(
#        crop
#        SHARED
#        ${libjpeg}
#        ${libpng}
#        ${libcrop}
#)
#
#target_link_libraries(
#        crop
#        log
#        z
#)