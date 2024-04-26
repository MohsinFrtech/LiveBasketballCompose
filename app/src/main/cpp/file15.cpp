#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray15(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string qXd = "LXwnqfvcF0JB4DONd53aTiReYFjKwTdGND!NrBG&";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(qXd.c_str()));
    std::string sNE = "GKb0wDfY0tNrNJt7^IiFvQiqWgEyafPt#zWN#eCi";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(sNE.c_str()));
    std::string bE = "0LXNuqxEXxV$xqyXsuZ8e39kY0NNTqlsF4hPOOLX";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(bE.c_str()));
    std::string JJR = "NN0Tj!j3z4FAWYjbFk89@2GA%ZbEsIAC^Ar5MGJ0";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(JJR.c_str()));
    std::string HeK = "lJDSUIt@7sUkCW9Ai7nh@A@dqa#vaMhU$fq7lL^z";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(HeK.c_str()));
    std::string Ht = "wbftXRvQHfUfzpHiBsBJSG@zcy2HCPG&C7Nq4e5R";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Ht.c_str()));
    std::string zFG = "T3WfIOo!flrbaF@6XAUJ6P1wUxDJC^GRVWDygRV^";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(zFG.c_str()));
    std::string Zl5 = "x^0!V$DzPvBBX!K%^&&@&hXzXJIqizb5ydn9#Ex8";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Zl5.c_str()));
    std::string q25 = "vkKFFq%2si%&MDEtnx6Y#lvBNTkm3YPW!q5T%e0F";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(q25.c_str()));
    std::string Iq = "r5c!ui#ETV9fURZp#honGF4R4w0!tcbp1IutamR2";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Iq.c_str()));

    return strarr;
}