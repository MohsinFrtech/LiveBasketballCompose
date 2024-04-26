#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray21(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string jao = "&2a5l3fI52LWly$GbAYE!6m0^5jgzwzHXwHxhk8Y";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(jao.c_str()));
    std::string Ab0 = "yTt%JtiwmnIdHpi#fVKSCPa1oqr5k1WYX@pD^Vrb";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(Ab0.c_str()));
    std::string acf = "Dr5Q2%ZFq7mpn@kXMQWzRNKexgvG8vgXJoRwCvo0";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(acf.c_str()));
    std::string Mdf = "NFAzINNghPYcnU@ex4SzB%Q77!J6hJ3OLe8MfBnC";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Mdf.c_str()));
    std::string Aee = "E776BYMmcyocVY#^PFljX71oz2$a%f6luOi!TSHM";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(Aee.c_str()));
    std::string Rfr = "odAKfKoiA@@1#8TIq2HT9lD^pv#GBEZRe@VfO8kU";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Rfr.c_str()));
    std::string mgi = "cOPUQIF^lLrqtqqn##9PIGQiFi^FQI1TZy1Swkqj";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(mgi.c_str()));
    std::string Yh0 = "PFgZveSabg%pGLtr9gD@at%m6mFGgv0YpUxhUhEB";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Yh0.c_str()));
    std::string Mii = "kkAOqmtyy&pq8XZgvNrhJHcAe$%LC00Rb#ALaD1F";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(Mii.c_str()));
    std::string EjE = "KlV!9Gd#8TonoZbxJ3I&kaT@qAah63RM^FH6y1BH";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(EjE.c_str()));

    return strarr;
}