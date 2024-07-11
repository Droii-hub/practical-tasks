package com.walking.lesson32_files_1.task2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.walking.lesson32_files_1.task1.model.Car;
import com.walking.lesson32_files_1.task1.model.Specification;

/**
 * Используя Задачу 1, реализуйте чтение из carCatalog.txt,
 * реализовав сохранение данных в массив Car.
 */
public class Main {
    public static void main(String[] args) {
        Car[] cars=loadCars("./resource/Cars.txt");
        for (Car car : cars) {
            System.out.println(car.toString());
        }
    }
    private static Car[] loadCars(String path){
        StringBuilder loadedString=new StringBuilder();
        try (InputStreamReader isr=new InputStreamReader(new FileInputStream(path),"ISO8859_5")){
            int i;  
                    
            while ((i=isr.read())!=-1) {
                loadedString.append((char)i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String stringCars=new String(loadedString);
        Car[] cars=new Car[count(stringCars,"gosNumber:")];
        Pattern gosNumberPattern=Pattern.compile("gosNumber:[\\wа-яА-Я]+");
        Pattern engineVinPattern=Pattern.compile("engineVin:[\\wа-яА-Я]+");
        Pattern modelPattern=Pattern.compile("model:[\\wа-яА-Я]+");
        Pattern brandPattern=Pattern.compile("brand:[\\wа-яА-Я]+");
        Pattern colorPattern=Pattern.compile("color:[\\wа-яА-Я]+");
        Matcher gosNumberMatcher=gosNumberPattern.matcher(stringCars);
        Matcher engineVinMatcher=engineVinPattern.matcher(stringCars);
        Matcher modelMatcher=modelPattern.matcher(stringCars);
        Matcher brandMatcher=brandPattern.matcher(stringCars);
        Matcher colorMatcher=colorPattern.matcher(stringCars);
        for (int i = 0; i < cars.length; i++) {
            gosNumberMatcher.find();
            engineVinMatcher.find();
            modelMatcher.find();
            brandMatcher.find();
            colorMatcher.find();
            cars[i]=new Car(gosNumberMatcher.group().substring(10), engineVinMatcher.group().substring(10),
             new Specification(brandMatcher.group().substring(6), modelMatcher.group().substring(6)), colorMatcher.group().substring(6));
        }
        return cars;
    }
    public static int count(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }
}
