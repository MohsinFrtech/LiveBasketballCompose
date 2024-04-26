#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray9(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string B6W = "xr3BTAj5fslhlFvZh1!VdP9n5NCEKc^gSugYsRCF";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(B6W.c_str()));
    std::string wO2 = "43L9TVSjKf#wuzF24Ipsq%6BWhXYy8nvkv$JVs@Z";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(wO2.c_str()));
    std::string sbV = "NqbrnW8#aa0UhYIHnyxFXRho1vmCzY#O8RQy2f9#";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(sbV.c_str()));
    std::string Ba5 = "iXoAq3fNHW7MzHM9xKoQvLZW4&kdpALDL72qsZ9@";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Ba5.c_str()));
    std::string w = "s8IhREJZe4h4Y18eljhK98AE^5ZZnT10!xlvFWWY";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(w.c_str()));
    std::string scP = "R5mWsX&EfJ@@jzYnVice&^NJFXLoaeNY9vcrVz0%";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(scP.c_str()));
    std::string Tpo = "nixHyfqfjEqiHLZwyGW29dyIa8vPxwU6!r1@8ZLz";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(Tpo.c_str()));
    std::string vDq = "S7bO3Z&tcNwRAtLWR!5&AJXElxFj^hXxnOIu%c4&";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(vDq.c_str()));
    std::string Gg = "Kor!P#jc%6YQ#IlY&eJ$#KTX8XpRB8kKbmUgt^kY";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(Gg.c_str()));
    std::string TBG = "jpCH@8rUJ8aH1g%%w!6%BqKvP1W7!b6oA$7ATUE#";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(TBG.c_str()));

    return strarr;
}