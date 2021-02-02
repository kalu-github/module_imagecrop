# Anything that must be linked against the shared C library on Windows must
# be built in this subdirectory, because CMake doesn't allow us to override
# the compiler flags for each build type except at directory scope.  Note
# to CMake developers:  Add a COMPILE_FLAGS_<CONFIG> target property, or
# better yet, provide a friendly way of configuring a Windows target to use the
# static C library.

#set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/..)
#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/..)
#set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/..)

# 遍历
foreach(src ${JPEG_SOURCES})
  set(JPEG_SRCS ${JPEG_SRCS} ../${src})
endforeach()

# 输出
#add_library(jpeg SHARED ${JPEG_SRCS} ${DEFFILE} $<TARGET_OBJECTS:simd>
#        ${SIMD_OBJS})


# 编译libpng
#set(
#        LIBPNG
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/arm/arm_init.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/arm/filter_neon.S
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/arm/filter_neon_intrinsics.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/arm/palette_neon_intrinsics.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/example.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/png.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/png.h
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngconf.h
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngdebug.h
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngerror.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngget.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pnginfo.h
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pnglibconf.h
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngmem.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngpread.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngpriv.h
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngpread.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngread.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngrio.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngrtran.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngrutil.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngset.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngstruct.h
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngtrans.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngwio.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngwrite.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngwtran.c
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/libpng/pngwutil.c
#)
#
#set(
#        LIBUCROP
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/CImg.h
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/uCrop-png-jpegturbo.cpp
#        ${PROJECT_SOURCE_DIR}/src/main/cpp/com_yalantis_ucrop_task_BitmapCropTask.h
#)
#
#add_library(
#        crop
#        SHARED
#        ${JPEG_SRCS} ${DEFFILE} $<TARGET_OBJECTS:simd>
#        ${SIMD_OBJS}
#        ${LIBPNG}
#        ${LIBUCROP}
#)
#
#target_link_libraries(
#        crop
#        log
#        z
#)