//
//  main.cpp
//  NumberConverter
//
//  Created by Christopher Lawton on 9/3/24.
//

#include <iostream>
#include <string>
#include <cassert>
#include <vector>


int charToInt(char c){
    if (isalpha(c)){
        return tolower(c) - 'a' + 10;
    } else {
        return c - '0';
    }
}

// TODO: handle negatives
int stringToInt(std::string numberStr, int base){
    bool isNegative = numberStr[0] == '-';
    if (isNegative){
        numberStr = numberStr.substr(1, numberStr.size() - 1);
    }
    int result = 0;
    for (int i = 0; i < numberStr.size(); i++){
        int number = charToInt(numberStr[i]);
        result += number * (pow(base, numberStr.size() - i - 1));
    }
    return isNegative ? result * -1 : result;
}

std::string _numToString(int num, int base){
    bool isNegative = num < 0;
    if (isNegative){
        num = num * -1;
    }
    std::string result;
    while (num > 0){
        char converted;
        int remainder = num % base;
        if (remainder>=10){
            converted = '7' + remainder;
        } else {
            converted = '0' + remainder;
        }
        result.insert(result.begin(), converted);
        num = num / base;
    }
    return isNegative ? '-' + result : result;
}

// TODO: handle negative numbers
std::string intToString(int num){
    return _numToString(num, 10);
}

std::string intToBinary(int num){
    return _numToString(num, 2);
}

std::string intToHex(int num){
    return _numToString(num, 16);
}

void testStringtoInt(){
    assert(stringToInt("99", 10) == 99);
    assert(stringToInt("10", 2) == 2);
    assert(stringToInt("32", 16) == 50);
    assert(stringToInt("FF", 16) == 255);
    assert(stringToInt("-FF", 16) == -255);
    assert(stringToInt("-10", 2) == -2);
}

void testIntToString(){
    assert(intToString(10) == "10");
    assert(intToString(123) == "123");
    assert(intToString(-123) == "-123");
}

void testToBinary(){
    assert(intToBinary(12) == "1100");
    assert(intToBinary(57) == "111001");
    assert(intToBinary(-57) == "-111001");
}

void testToHex(){
    assert(intToHex(12) == "C");
    assert(intToHex(100) == "64");
    assert(intToHex(254) == "FE");
    assert(intToHex(-254) == "-FE");
}

int main(int argc, const char * argv[]) {
    testStringtoInt();
    testIntToString();
    testToBinary();
    testToHex();
    
    return 0;
}
