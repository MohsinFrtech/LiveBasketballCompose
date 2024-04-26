#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray19(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string waC = "W9WzD7Bjpci7KJTasAAG!#Jf830SRW$2tf8jz1Rl";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(waC.c_str()));
    std::string jjm = "2YEHcf@TOCmbmnEF0o0WoHUURe3Y2J#r#wjigAQ4";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(jjm.c_str()));
    std::string ctz = "uNqyMukkur1J4eM61e3cX6iAoU%8Litssu2NSnmc";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(ctz.c_str()));
    std::string Ys1 = "QzF8B1bvT8J10@gjwAugZoXb6L7%erAaR@UVC3h6";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Ys1.c_str()));
    std::string sa2 = "Eq5vCK03zNmhIxPLslBOdDlbe7exmKvwe6CKo%6e";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(sa2.c_str()));
    std::string mv = "Isn9tQuJvgsEQsTLHe2euy0g5TFlAFNnrDBzcCq6";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(mv.c_str()));
    std::string AnC = "vkJN8!3FX3OHgFQhuIXOm0f5USB3oqgrTkm#YnQF";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(AnC.c_str()));
    std::string yT = "bH!T8VDY2qoJTtyZ2p#m07ZIji$Kvlhx2GGKbBvB";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(yT.c_str()));
    std::string noD = "MhwUvh9GtQhbSC0p0g#F2FWSUoknv7hywXEJXSpy";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(noD.c_str()));
    std::string Pl = "lpO6FQpQAqwYSKar%uWf#9uLQ0zudPDhvdeN0hzM";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Pl.c_str()));

    return strarr;
}