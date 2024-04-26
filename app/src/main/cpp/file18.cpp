#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray18(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string va1 = "I95JjzV16YHotJQLTwabMD5QxKpQ5YlkQyRCyPTb";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(va1.c_str()));
    std::string Kba = "wnls9qbnlT4jEp2lIlcdVjtCs9PenXl7s8bL83Qy";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(Kba.c_str()));
    std::string Kc5 = "QHivMRBVrBefK21NPxjyibc7fFudJIaDXa5WZJbN";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(Kc5.c_str()));
    std::string Sdj = "URPnB5nYjWGghp5tJf6pZ3YOYfnGBj0kJlzmB5Bx";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Sdj.c_str()));
    std::string Ne1 = "LS6ApW9BU4s7Bqjij45XYxsQiyR2ZsimFIZznnba";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(Ne1.c_str()));
    std::string FjT = "i1643BjkWwx4lmklA6NNgvbLPQVsHb9s5rPGZa8Ix1";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(FjT.c_str()));
    std::string uwQ = "W2erGma1DtyRdCjqBU8Uzz1fZulxOQ3Hf67Cbe69";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(uwQ.c_str()));
    std::string h5 = "uC1RMH0CfgMlCb2szChxpShwwzZLtk4unfzyNMja";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(h5.c_str()));
    std::string ifG = "v15vlGf2PbqYjhE2155wwqfzbJB9sT3jagVfVDms";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(ifG.c_str()));
    std::string Gm9 = "rsnI9vvm105AgfAYnynT5ip8OvqV8qlS1xQ4RZ5E";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Gm9.c_str()));

    return strarr;
}