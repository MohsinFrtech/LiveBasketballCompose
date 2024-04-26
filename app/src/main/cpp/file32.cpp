#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray32(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string qbd = "LXwnqfvcF0JB4DONd53cviReYFjKwTdGND!NrBG&";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(qbd.c_str()));
    std::string smE = "GKb0wDfY0tNrNcv7^IiFvQiqWgEyafPt#zWN#eCi";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(smE.c_str()));
    std::string hE = "0LXNuqxEXxV$lkyXfgZ8e39kY0NNTqlsF4hPOOLX";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(hE.c_str()));
    std::string JkR = "NN0Tj!j3z4FApojbFk89@2GA%ZbEsIAC^Ar5MGJ0";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(JkR.c_str()));
    std::string HaK = "lJDSUIt@7sUkCW9lk7nh@A@dqa#vaMhU$fq7lL^z";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(HaK.c_str()));
    std::string dt = "wbftXRvQHfUfzpHiBcvJSG@zcy2HCPG&C7Nq4e5R";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(dt.c_str()));
    std::string zwG = "T3WfIOo!flrbaF@6nmUJ6P1wUxDJC^GRVWDygRV^";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(zwG.c_str()));
    std::string Zv5 = "x^0!V$DzPvBBX!K%^&&@&hXzXJIqmnb5ydn9#Ex8";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Zv5.c_str()));
    std::string qg5 = "vkKFFq%2si%&MDEtnx6Y#lvnmTkm3YPW!q5T%e0F";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(qg5.c_str()));
    std::string Iaq = "r5c!ui#ETV9fmnZp#honGF4R4w0!tcbp1IutamR2";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Iaq.c_str()));

    return strarr;
}