#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray20(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string Jwi = "idA452Ti@jIV6tuC%gX8@FDaP6q62hM%lE@QvHn8";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(Jwi.c_str()));
    std::string gbX = "DokDrvXadetZAgfg4TDYSX%1qa6d7tpE%l8fyKGd";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(gbX.c_str()));
    std::string Jgi = "MHtF%TDZ0NIovc7BS0yq!yM%PSIATD3iUdyh4RpD";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(Jgi.c_str()));
    std::string kv = "z#F$@ReQlkqUKL$rjb6SNMe6DqikSxtA4nWXW0Q5";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(kv.c_str()));
    std::string Sv3 = "JqMpCMs7bh64lhyyRpQIuk1ZzUznONlUh5BcF%!o";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(Sv3.c_str()));
    std::string Yii = "roGFPM6T!F4k@4mbWM9GyybHrnpjFMD$D9@HF1tt";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Yii.c_str()));
    std::string Mnu = "0!Io2IX4nfBbp8c0bckW3kCs2T2pFpw9CH!LH%06";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(Mnu.c_str()));
    std::string ieq = "DNcWrVd9abLP!7T!1q51CEj3eJBMva3Ah55FLX$6";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(ieq.c_str()));
    std::string CbD = "E5yFZc3R#Ju37xGyjaC8jCWKEb5vPFt35q5wcM2S";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(CbD.c_str()));
    std::string y = "nZ5tW!gLlXakjo0!1NY3oYK9cFV2ZCgD!ey7IlE0";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(y.c_str()));

    return strarr;
}