#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray29(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string Qt = "N!cnw1kuk2U4LnmIF8hBMHQS0KKLaS29e$Ee2Us2";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(Qt.c_str()));
    std::string Yut = "p!eJCQZABwWop8B5l1LfZLvCU@uKUCETraNd1loL";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(Yut.c_str()));
    std::string Emo = "mUdAvQklgfT6kjqNIECENl7h2&!51eaj7pJgVs^E";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(Emo.c_str()));
    std::string djF = "VexidHjN@hb65Fz^XZvvbY1lSVXhZ9bfcoIoGWJz";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(djF.c_str()));
    std::string qaY = "G1UdGC!UP@5CeYZty6Rq7ZOAhO&&w6^Dhm6GGLIu";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(qaY.c_str()));
    std::string Lux = "P7%9UrQerGQJ$@BNqVmPp9s7801GXlwVd3By9tt^";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Lux.c_str()));
    std::string maf = "X0$bZj^x5Hionk%I%uGEbflVlMOiPrKs#21#vWPF";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(maf.c_str()));
    std::string npU = "W34TQ8$dP&5yG6nm9bmx7l1EB2g8ugGBTCIMXFwG";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(npU.c_str()));
    std::string dO = "^zrUpoa#Dieu14h1j#onX!EVcQbnUu^e8Cqk$mW1";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(dO.c_str()));
    std::string hdT = "IdmuU4fLt&53Aacc&cG3ZrzH^8zqzkqXyv82lqaP%";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(hdT.c_str()));

    return strarr;
}