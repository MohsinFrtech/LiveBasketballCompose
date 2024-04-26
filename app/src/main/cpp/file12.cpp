#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray12(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string Qy = "N!cnw1kuk2U4LMaIF8hBMHQS0KKLaS29e$Ee2Us2";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(Qy.c_str()));
    std::string YLt = "p!eJCQZABwWNp8B5l1LfZLvCU@uKUCETraNd1loL";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(YLt.c_str()));
    std::string E5o = "mUdAvQQMgfT6kjqNIECENl7h2&!51eaj7pJgVs^E";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(E5o.c_str()));
    std::string diF = "VexidHjN@hb65Fz^XZvzyY1lSVXhZ9bfcoIoGWJz";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(diF.c_str()));
    std::string qeY = "G1UdGC!UP@5CeYZpG6Rq7ZOAhO&&w6^Dhm6GGLIu";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(qeY.c_str()));
    std::string Lx = "P7%9UrQTQGQJ$@BNqVmPp9s7801GXlwVd3By9tt^";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Lx.c_str()));
    std::string muf = "X0$bZj^x5HASnk%I%uGEbflVlMOiPrKs#21#vWPF";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(muf.c_str()));
    std::string nlU = "W34TQ8$dP&5yG6Wi9bmx7l1EB2g8ugGBTCIMXFwG";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(nlU.c_str()));
    std::string cO = "^zrUGwa#Dieu14h1j#onX!EVcQbnUu^e8Cqk$mW1";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(cO.c_str()));
    std::string hsT = "IdmuU4fLt&53Afic&cG3ZrzH^8zqzkqXyv82lqaP%";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(hsT.c_str()));

    return strarr;
}