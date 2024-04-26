#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray3(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string J5i = "idA452Ti@jIV6PJC%gX8@FDaP6q62hM%lE@QvHn8";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(J5i.c_str()));
    std::string gNX = "DokDrvXadetZAQng4TDYSX%1qa6d7tpE%l8fyKGd";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(gNX.c_str()));
    std::string J4i = "MHtF%TDZ0NIohZ7BS0yq!yM%PSIATD3iUdyh4RpD";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(J4i.c_str()));
    std::string v = "z#F$@ReQOUqUKL$rjb6SNMe6DqikSxtA4nWXW0Q5";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(v.c_str()));
    std::string ST3 = "JqMpCMs7bh64lHMyRpQIuk1ZzUznONlUh5BcF%!o";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(ST3.c_str()));
    std::string YFi = "roGFPM6T!F4k@4pZWM9GyybHrnpjFMD$D9@HF1tt";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(YFi.c_str()));
    std::string Meu = "0!Io2IX4nfBbp8c0AwkW3kCs2T2pFpw9CH!LH%06";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(Meu.c_str()));
    std::string iYq = "DNcWrVd9kxLP!7T!1q51CEj3eJBMva3Ah55FLX$6";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(iYq.c_str()));
    std::string CrD = "E5yFZc3R#Ju37xGJJaC8jCWKEb5vPFt35q5wcM2S";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(CrD.c_str()));
    std::string w = "nZ5tW!gLlXaHIo0!1NY3oYK9cFV2ZCgD!ey7IlE0";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(w.c_str()));

    return strarr;
}