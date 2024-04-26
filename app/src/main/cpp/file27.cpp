#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray27(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string kmL = "MDFyIfEx5TQi6TNFcv%qi#G6IBvbjQ1GF7VX@n07";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(kmL.c_str()));
    std::string laC = "ztz5CvREz0hioAF!qqEJnmIhd0pcN5G&m$1N49u&";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(laC.c_str()));
    std::string Bn8 = "5Sx$hoOpocL^a@nFa5szQ03kVsbFcovKh#RLBmA@";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(Bn8.c_str()));
    std::string xQ = "!Sd$Rf0edaeZU&9tty47%YCRp5OJMMu#fptwqYsu";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(xQ.c_str()));
    std::string MgU = "%AgNHLRqN%80elfCmntRR&beS^qoDvsL%QdIevi&";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(MgU.c_str()));
    std::string IaM = "QDWGir^YuM@WknmOP$YjP^sPDGaWX4GA5CasTrqu";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(IaM.c_str()));
    std::string wu3 = "6wAae3QJ@9rrtvIeGr07w&AXEMpZDsXPJh2swX4&";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(wu3.c_str()));
    std::string BuB = "LIH7TpJT$a8HjkD4JGfdcZNcpNXMVBQHQGhHLppT";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(BuB.c_str()));
    std::string YoO = "Z$L&h3xYQtrKGjk1^m&zKvlVcFcdsCQ$j1wISHjq";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(YoO.c_str()));
    std::string ngR = "SofHXh4N@tq5Be!gIdxs06$0^HU6VT^g0Qj0vsgU";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(ngR.c_str()));

    return strarr;
}