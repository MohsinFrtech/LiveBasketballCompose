#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray8(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string REf = "NKC2$i06kBmHfNubWF2yPgkQCd%%yeo9sdD&h69p";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(REf.c_str()));
    std::string S3 = "SRSWd4J&&JMQJPTqOiiApx0G&eDQrpeVUKb$JjAG";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(S3.c_str()));
    std::string Qlq = "3L&Cg5PckZuncM4$&@&NIH$lhuzA6vTh5UI%fW8K";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(Qlq.c_str()));
    std::string Tb = "iB2$3815LuDzdBa3YWw8Mo9OFF&bPyu%NH&jMfqx";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Tb.c_str()));
    std::string u3 = "vSkYcNENReEV4qFoYw@cHss6QF5buAJtG3^Y4ul2";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(u3.c_str()));
    std::string Lw = "!sd0j8eJKCLZoVp%yeZ2a6po9IoA$F@B0CexmOr0";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Lw.c_str()));
    std::string qGZ = "o9QDM8PRFT@w!SG8WnUW1sm0Y78tst5pkWy#yPr7";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(qGZ.c_str()));
    std::string pi6 = "jEHdHJfjIPk!IAGRjvWerSOZ@uXQPh8Da!MKdYkn";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(pi6.c_str()));
    std::string uHG = "#5QzeqIOWYcfSuM5Vd1ilrXurq6lpPnSc@pmjTvN";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(uHG.c_str()));
    std::string OhD = "XVT3DeP3gvcPOfpke588Rlu@P0SMXjr&1$77KAOY";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(OhD.c_str()));

    return strarr;
}