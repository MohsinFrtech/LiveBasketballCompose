#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray25(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string Raf= "NKC2$i06kBmHfNnmWF2yPgkQCd%%yeo9sdD&h69p";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(Raf.c_str()));
    std::string t3 = "SRSWd4J&&JMQcvTqOiiApx0G&eDQrpeVUKb$JjAG";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(t3.c_str()));
    std::string Qbq = "3L&Cg5PckZunnm4$&@&NIH$lhuzA6vTh5UI%fW8K";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(Qbq.c_str()));
    std::string yb = "iB2$3815LuDzvba3YWw8Mo9OFF&bPyu%NH&jMfqx";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(yb.c_str()));
    std::string uy3= "vSkYcNENReEV4tuoYw@cHss6QF5buAJtG3^Y4ul2";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(uy3.c_str()));
    std::string Lmw = "!sd0j8eJKCLZopo%yeZ2a6po9IoA$F@B0CexmOr0";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Lmw.c_str()));
    std::string qkZ= "o9QDM8gjFT@w!SG8WnUW1sm0Y78tst5pkWy#yPr7";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(qkZ.c_str()));
    std::string po6 = "jEHdHJfjIPk!IAGRjvnmrSOZ@uXQPh8Da!MKdYkn";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(po6.c_str()));
    std::string uoG = "#5QzeqIOWYcfmiM5Vd1ilrXurq6lpPnSc@pmjTvN";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(uoG.c_str()));
    std::string OlD = "XVT3DeP3gvcdgfpke588Rlu@P0SMXjr&1$77KAOY";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(OlD.c_str()));

    return strarr;
}