cmake_minimum_required(VERSION 3.22.1)
project(nodule_android LANGUAGES CXX)

add_library(nodule_android SHARED nodule_android.cpp)
target_compile_features(nodule_android PUBLIC cxx_std_17)
target_link_libraries(nodule_android log)