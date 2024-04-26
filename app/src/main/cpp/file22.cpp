#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//
extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray22(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string IkR = "iDN&pvh1vAaSxnbCv8#Vx$m$QHs$caph&&ir0n56";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(IkR.c_str()));
    std::string l6 = "AKhwQ&$i@JVnpomqD@L#TC5Cx&%K@SWV3tMyuHBw";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(l6.c_str()));
    std::string sme = "ln2uX9qmn^h#eH7LgLo5OKjcBQwM&!P7E4!vQ!&R";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(sme.c_str()));
    std::string EnY = "@&MJZ^K&40mh5$6Z$At5pqpezfvE73%hT^U@y#V3";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(EnY.c_str()));
    std::string hm0 = "X0vXeJTpLx9864Ykj6rhsqGzGO3M9mK01^4MK^uL";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(hm0.c_str()));
    std::string bvg = "964E#TBxAjCla@KmnBQ8#xOmnfMNwvgHw9vmRTKc";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(bvg.c_str()));
    std::string QaB = "KZa9%A1t2IjpozU6YF1kcxvbn%M@DOcVhw7YQIIK";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(QaB.c_str()));
    std::string Mq9 = "kaKFc%Nj^w6lDgh7$Lxza2P4t%Go$i&W1xW@bLrk";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Mq9.c_str()));
    std::string BgB = "8a4rGc!&@iC3TasIB7xVb5O2V9gJR1T9LUWDCIZ1";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(BgB.c_str()));
    std::string b5P = "wV8o22ELtTe8yuTLCBfnvK9JxJjNKvDu#EBlDELO";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(b5P.c_str()));

    return strarr;
}