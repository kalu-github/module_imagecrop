cmake_minimum_required(VERSION 3.4.1)

# 设置生成的 .a 动态库最后输出的路径
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/output/${ANDROID_ABI})
# 设置生成的 .so 动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/output/${ANDROID_ABI})
# 设置编译临时文件最后输出的路径
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/output/${ANDROID_ABI})

# set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -frtti -fexceptions")
add_subdirectory(src/main/cpp/libjpegturbo)

#add_subdirectory(src/main/cpp/libjpegturbo/simd)
#
##set(
##        LIBJPEGTURBO
##        src/main/cpp/libjpegturbo/jcapimin.c
##        src/main/cpp/libjpegturbo/jcapistd.c
##        src/main/cpp/libjpegturbo/jccoefct.c
##        src/main/cpp/libjpegturbo/jccolor.c
##        src/main/cpp/libjpegturbo/jcdctmgr.c
##        src/main/cpp/libjpegturbo/jchuff.c
##        src/main/cpp/libjpegturbo/jcinit.c
##        src/main/cpp/libjpegturbo/jcmainct.c
##        src/main/cpp/libjpegturbo/jcmarker.c
##        src/main/cpp/libjpegturbo/jcmaster.c
##        src/main/cpp/libjpegturbo/jcomapi.c
##        src/main/cpp/libjpegturbo/jcparam.c
##        src/main/cpp/libjpegturbo/jcphuff.c
##        src/main/cpp/libjpegturbo/jcprepct.c
##        src/main/cpp/libjpegturbo/jcsample.c
##        src/main/cpp/libjpegturbo/jctrans.c
##        src/main/cpp/libjpegturbo/jdapimin.c
##        src/main/cpp/libjpegturbo/jdapistd.c
##        src/main/cpp/libjpegturbo/jdatadst.c
##        src/main/cpp/libjpegturbo/jdatasrc.c
##        src/main/cpp/libjpegturbo/jdcoefct.c
##        src/main/cpp/libjpegturbo/jdcolor.c
##        src/main/cpp/libjpegturbo/jddctmgr.c
##        src/main/cpp/libjpegturbo/jdhuff.c
##        src/main/cpp/libjpegturbo/jdinput.c
##        src/main/cpp/libjpegturbo/jdmainct.c
##        src/main/cpp/libjpegturbo/jdmarker.c
##        src/main/cpp/libjpegturbo/jdmaster.c
##        src/main/cpp/libjpegturbo/jdmerge.c
##        src/main/cpp/libjpegturbo/jdphuff.c
##        src/main/cpp/libjpegturbo/jdpostct.c
##        src/main/cpp/libjpegturbo/jdsample.c
##        src/main/cpp/libjpegturbo/jdtrans.c
##        src/main/cpp/libjpegturbo/jerror.c
##        src/main/cpp/libjpegturbo/jfdctflt.c
##        src/main/cpp/libjpegturbo/jfdctfst.c
##        src/main/cpp/libjpegturbo/jfdctint.c
##        src/main/cpp/libjpegturbo/jidctflt.c
##        src/main/cpp/libjpegturbo/jidctfst.c
##        src/main/cpp/libjpegturbo/jidctint.c
##        src/main/cpp/libjpegturbo/jidctred.c
##        src/main/cpp/libjpegturbo/jquant1.c
##        src/main/cpp/libjpegturbo/jquant2.c
##        src/main/cpp/libjpegturbo/jutils.c
##        src/main/cpp/libjpegturbo/jmemmgr.c
##        src/main/cpp/libjpegturbo/jmemnobs.c
##        src/main/cpp/libjpegturbo/jaricom.c
##        src/main/cpp/libjpegturbo/jcarith.c
##        src/main/cpp/libjpegturbo/jdarith.c
###        src/main/cpp/libjpegturbo/turbojpeg.c
###        src/main/cpp/libjpegturbo/transupp.c
###        src/main/cpp/libjpegturbo/jdatadst-tj.c
###        src/main/cpp/libjpegturbo/jdatasrc-tj.c
###        src/main/cpp/libjpegturbo/turbojpeg-mapfile
##)
#
#add_library(
#        libjpeg
#        SHARED
#        ${LIBJPEG}
#)
#
#target_link_libraries(
#        libjpeg
#        log
#        z
#)