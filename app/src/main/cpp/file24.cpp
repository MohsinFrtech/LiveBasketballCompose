#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray24(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string dbc = "O!B6$GAAasaSEGWIvT1YTT6vJW2thgRLZ7n3^016";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(dbc.c_str()));
    std::string ny = "!kp%NeSCmnCv17AglttCMnKa$uXZTqzUJqHO#BL@";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(ny.c_str()));
    std::string s3 = "Asyxp&b9Egh0!VV9!QV%m$lnY%&g^Z0a2r@A3^xb";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(s3.c_str()));
    std::string xmv = "f^Ub^UO1uioLJcW62GPOYd&3yHRPJcE&IZrtjbj8";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(xmv.c_str()));
    std::string o = "BW4p87@7IMgtyqhVdbPtkNVIyq4f0964i6eeOBxZ";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(o.c_str()));
    std::string qtp = "X&QIKlitgbhpoP3S4@c8ZvF@vtgYcf8QqwQw2kt0";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(qtp.c_str()));
    std::string do2 = "X&QIKlitgbhtyP3S4@c8ZvF@vtgYcf8QqwQw2kt0";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(do2.c_str()));
    std::string rj5 = "F14Yaavhvb7TR^wirEXxKRu!UwLjv@v#LYdgT8kq";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(rj5.c_str()));
    std::string oN = "i2^rMwnfWyuwSzP3bXg!Q6uUY0MGlDXZJia&Ii&2";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(oN.c_str()));
    std::string Rxf = "ZF7tRy068ttyc%lCPo20byHOyO1sHXVZyoSnsQ8T";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Rxf.c_str()));

    return strarr;
}