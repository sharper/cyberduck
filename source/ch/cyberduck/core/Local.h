/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class Local */

#ifndef _Included_Local
#define _Included_Local
#ifdef __cplusplus
extern "C" {
#endif
/* Inaccessible static: fs */
/* Inaccessible static: separatorChar */
/* Inaccessible static: pathSeparatorChar */
/* Inaccessible static: tmpFileLock */
/* Inaccessible static: counter */
/* Inaccessible static: tmpdir */
#undef Local_serialVersionUID
#define Local_serialVersionUID 301077366599181567LL
/* Inaccessible static: log */
/* Inaccessible static: longDateFormatter */
/* Inaccessible static: shortDateFormatter */
/* Inaccessible static: class_00024ch_00024cyberduck_00024core_00024Local */
/*
 * Class:     Local
 * Method:    setIconFromExtension
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_ch_cyberduck_core_Local_setIconFromExtension
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     Local
 * Method:    setIconFromFile
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_ch_cyberduck_core_Local_setIconFromFile
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     Local
 * Method:    isAlias
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_Local_isAlias
  (JNIEnv *, jobject, jstring);

/*
 * Class:     Local
 * Method:    resolveAlias
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_Local_resolveAlias
  (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif
