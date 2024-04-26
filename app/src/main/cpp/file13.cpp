#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray13(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(13, env->FindClass("java/lang/String"), nullptr);
    std::string Dq = "MwuxBy@Lt5Y1oNP63DGuAf^EWb0jDm0hA6kWm&ga";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(Dq.c_str()));
    std::string Jlm = "iFRQoljvCS1C1dH&8vbXjPnLtRYNAo2M%CV4xQK7";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(Jlm.c_str()));
    std::string HA = "2Yug5VS7!^nKG!LfJ4ivnkpJ7L!DdNKgAKm1NSHO";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(HA.c_str()));
    std::string RQD = "c7f7pF21Em&%YeXBlsQwQ8GRL#q6JB0#XoSuaXx9";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(RQD.c_str()));
    std::string mAK = "SBgrld%D$qy@!cE6OTVhpqpzRqPLAf2UFukD^Isz";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(mAK.c_str()));
    std::string mIT = "XmwpDkDPsnpUC4Y6vx$R$jQE4YqTlZ1a9$WZprOg";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(mIT.c_str()));
    std::string GFz = "RpEai5o4W#vgO9ikCE8H%6obclvzuBe4zbK%Hvem";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(GFz.c_str()));
    std::string Kji = "bJqvCqZL5nM4tS8!Whd&uSl&xKjRX@EBj4&bM8ZB";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Kji.c_str()));
    std::string OS = "3w8Uhn8tR6JUjEUoeVHDPTZyi!Xmc$CoqxL1GRzx";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(OS.c_str()));
    std::string Pg = "Z0$pU#S1$K1b88V05&!Ely99Tf^NP9GWu9pgXQo3";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Pg.c_str()));

    return strarr;
}