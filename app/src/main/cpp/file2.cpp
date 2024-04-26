#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray2(JNIEnv *env,
                                                                           jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string wDC = "W9WzD7Bjpci7KJTX$AAG!#Jf830SRW$2tf8jz1Rl";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(wDC.c_str()));
    std::string jm = "2YEHcf@TOCmbYXEF0o0WoHUURe3Y2J#r#wjigAQ4";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(jm.c_str()));
    std::string c8z = "uNqyMukGXr1J4eM61e3cX6iAoU%8Litssu2NSnmc";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(c8z.c_str()));
    std::string Ye1 = "QzF8B1uJT8J10@gjwAugZoXb6L7%erAaR@UVC3h6";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Ye1.c_str()));
    std::string s42 = "Eq5vCK03zNKhIxPLslBOdDlbe7exmKvwe6CKo%6e";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(s42.c_str()));
    std::string fv = "Isn9tQuJvgsEQsTLHe2erE0g5TFlAFNnrDBzcCq6";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(fv.c_str()));
    std::string AxC = "vkJN8!3FX3OHgFQFKIXOm0f5USB3oqgrTkm#YnQF";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(AxC.c_str()));
    std::string QT = "bH!T8VDY2qoJTWLZ2p#m07ZIji$Kvlhx2GGKbBvB";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(QT.c_str()));
    std::string nhD = "MhwUvh9GtQXGSC0p0g#F2FWSUoknv7hywXEJXSpy";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(nhD.c_str()));
    std::string Pk = "lpO6FQpQAqwYSKCr%uWf#9uLQ0zudPDhvdeN0hzM";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Pk.c_str()));

    return strarr;
}