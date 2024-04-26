#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray23(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string na = "g&FNQ9aD5VA#!XFswukl^!gGESUQADFOCO&v89YN";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(na.c_str()));
    std::string l3o = "LzJcxwnjKxBr4Z3SY&NklnEqv@IBSzfzEnwvhjPf";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(l3o.c_str()));
    std::string KoI = "QifxpIGaAa^PmnWk4@tHg%S%G4$UxIDLGbaPaEPS";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(KoI.c_str()));
    std::string uac = "BbiWrTeDcsfFhyuvl6Pyc6GxU03VhTgFBds@0i^2";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(uac.c_str()));
    std::string L = "8lzOwG3OASlvbZPyEcwbyF!7HNPP0W0N$4iuUXMT";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(L.c_str()));
    std::string Hme = "L$i6axw2Y%Ho%tuT#uBx6&!0H2Nf57$9J5AjW3&c";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(Hme.c_str()));
    std::string LpZ= "L$i6acv2Y%Ho%ggT#uBx6&!0H2Nf57$9J5AjW3&c";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(LpZ.c_str()));
    std::string zaK= "PRo^bHEiox0KIuCofE&r^bTvZYgC7v9zBgOMAmo9";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(zaK.c_str()));
    std::string qnQ = "!^RMSa&&2e#xer3d#Csm5eBoAnaccO5yJkyAB8w0";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(qnQ.c_str()));
    std::string GnS = "jGGQVmn8K7&wvI&z!Er%QZyv$@1E@@2Sc%uZRbvk";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(GnS.c_str()));

    return strarr;
}