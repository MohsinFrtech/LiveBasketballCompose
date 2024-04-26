#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray4(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string jSo = "&2a5l3fI52LWwL$GbAYE!6m0^5jgzwzHXwHxhk8Y";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(jSo.c_str()));
    std::string AV0 = "yTt%JtiwenIdHpi#fVKSCPa1oqr5k1WYX@pD^Vrb";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(AV0.c_str()));
    std::string aqf = "Dr5Q2%ZFq7pkn@kXMQWzRNKexgvG8vgXJoRwCvo0";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(aqf.c_str()));
    std::string Mlf = "NFAzINNCyPYcnU@ex4SzB%Q77!J6hJ3OLe8MfBnC";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Mlf.c_str()));
    std::string Ake = "E776BYMmQCocVY#^PFljX71oz2$a%f6luOi!TSHM";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(Ake.c_str()));
    std::string Rhr = "odAKfK$WA@@1#8TIq2HT9lD^pv#GBEZRe@VfO8kU";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Rhr.c_str()));
    std::string mVi = "cOPUQIF^lLrsgqqn##9PIGQiFi^FQI1TZy1Swkqj";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(mVi.c_str()));
    std::string YC0 = "PFgZveSwvg%pGLtr9gD@at%m6mFGgv0YpUxhUhEB";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(YC0.c_str()));
    std::string MTi = "kkAOqmtyy&Ph8XZgvNrhJHcAe$%LC00Rb#ALaD1F";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(MTi.c_str()));
    std::string EVE = "KlV!9Gd#8TiMoZbxJ3I&kaT@qAah63RM^FH6y1BH";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(EVE.c_str()));

    return strarr;
}