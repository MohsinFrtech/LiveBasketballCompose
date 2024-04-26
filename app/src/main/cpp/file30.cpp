#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray30(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string aq = "MwuxBy@Lt5Y1oom63DGuAf^EWb0jDm0hA6kWm&ga";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(aq.c_str()));
    std::string Jom = "iFRQoljvCS1C1dH&8klXjPnLtRYNAo2M%CV4xQK7";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(Jom.c_str()));
    std::string kA = "2Yug5VS7!^nKG!LfJ4imokpJ7L!DdNKgAKm1NSHO";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(kA.c_str()));
    std::string RyD = "c7f7pF21ot&%YeXBlsQwQ8GRL#q6JB0#XoSuaXx9";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(RyD.c_str()));
    std::string mbK = "SBgrld%D$qy@!cE6OTVloqpzRqPLAf2UFukD^Isz";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(mbK.c_str()));
    std::string maT = "XmwpDkDPsnpUC4Y6bn$R$jQE4YqTlZ1a9$WZprOg";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(maT.c_str()));
    std::string Gaz = "RpEai5o4W#vgO9ikmo8H%6obclvzuBe4zbK%Hvem";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(Gaz.c_str()));
    std::string Kmi = "bJqvCqZL5nM4tS8!Wlk&uSl&xKjRX@EBj4&bM8ZB";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Kmi.c_str()));
    std::string kS = "3w8Uhn8tR6iljEUoeVHDPTZyi!Xmc$CoqxL1GRzx";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(kS.c_str()));
    std::string qg = "Z0$pU#S1$K1b88V05&!Ely99Tf^NP9veu9pgXQo3";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(qg.c_str()));

    return strarr;
}