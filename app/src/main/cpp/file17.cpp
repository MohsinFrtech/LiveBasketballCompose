#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray17(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string OP = "2xDpEMdb!334fs9Don5Mhqnw4%BK##dwEVF2yIvt";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(OP.c_str()));
    std::string tD4 = "d7SYYOC39od#Ntr7fqJmkraEuqEkAql8o9w$pAC0";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(tD4.c_str()));
    std::string CfE = "At7W3RKwBDsolHAQqcUPA$VMollw9tePZjyodG82";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(CfE.c_str()));
    std::string uKs = "D$SA2NBx7n73&na58nc5uJ9a$%reBS18RGH0z^fl";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(uKs.c_str()));
    std::string TO = "YMKx^1vLeX9nieUOXocOQxr@SOvz3t#&W9$Pg^%c";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(TO.c_str()));
    std::string WO0 = "sk&%D#484oyUeoeb^H6KRj67yz5CyE!aa6!8JKmH";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(WO0.c_str()));
    std::string HXI = "nxCNdASBNr984Q05cVzfAIt8v#zFe4&#HA^48qyo";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(HXI.c_str()));
    std::string OPd = "Z7CUmgn%F!zm@iMnkI1G!l5z&MWB#E8HFvdJxYp&";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(OPd.c_str()));
    std::string Y = "XAUIO17BS31N&4gATyGtytKmue#B7uO28@^auOe%";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(Y.c_str()));
    std::string oBp = "qvvUlZP2EQ1WM9bujC#bBun0iemE28O^R!jXLVGB";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(oBp.c_str()));

    return strarr;
}