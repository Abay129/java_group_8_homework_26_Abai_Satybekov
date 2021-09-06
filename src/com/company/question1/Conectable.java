package com.company.question1;

import com.company.Gates;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface Conectable {
    String [] reutrnSomeString(int index,int count);

    void connectToDataBase();

    void disconnectToDataBase();

    void checkConnection();

    void readStringByIndex(int index);

    void checkString(String key);

    void readStringByKey(String key);

    void takeStrings() throws IOException, ParseException;

    void countOfStrings();

    void WriteString(Gates[] gates) throws IOException, ParseException;

    void updateStringByIndex();

    void updateInfoByKey(String key);
}
