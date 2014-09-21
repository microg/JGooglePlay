LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := JGooglePlay
LOCAL_STATIC_JAVA_LIBRARIES := wire-runtime JGoogle
LOCAL_SRC_FILES := $(call all-java-files-under, src)

include $(BUILD_STATIC_JAVA_LIBRARY)
