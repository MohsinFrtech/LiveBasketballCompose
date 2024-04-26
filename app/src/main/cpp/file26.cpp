#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray26(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string BhW = "xr3BTAj5fslhlFnmh1!VdP9n5NCEKc^gSugYsRCF";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(BhW.c_str()));
    std::string wl2 = "43L9TVSjKf#tyzF24Ipsq%6BWhXYy8nvkv$JVs@Z";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(wl2.c_str()));
    std::string svV = "NqbrnW8#ajkUhYIHnyxFXRho1vmCzY#O8RQy2f9#";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(svV.c_str()));
    std::string Bo5 = "iXoAq3fNHW7nkHM9xKoQvLZW4&kdpALDL72qsZ9@";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Bo5.c_str()));
    std::string nw = "s8IhRauZe4h4Y18eljhK98AE^5ZZnT10!xlvFWWY";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(nw.c_str()));
    std::string snP = "R5mWsX&kmJ@@jzYnVice&^NJFXLoaeNY9vcrVz0%";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(snP.c_str()));
    std::string Tqo = "nixHyfqfjEqiHLZwymn29dyIa8vPxwU6!r1@8ZLz";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(Tqo.c_str()));
    std::string vmq= "S7bO3Z&tcNwRdfLWR!5&AJXElxFj^hXxnOIu%c4&";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(vmq.c_str()));
    std::string mg = "Kor!P#jc%6YQ#IlY&eJ$#Kyz8XpRB8kKbmUgt^kY";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(mg.c_str()));
    std::string TvG = "jpCH@8rUJ8fg1g%%w!6%BqKvP1W7!b6oA$7ATUE#";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(TvG.c_str()));

    return strarr;
}