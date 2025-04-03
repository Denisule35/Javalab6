package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static List<Angajat> citire() {
        try {
            File file = new File("src/main/resources/oameni.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> persoane = mapper.readValue(file, new TypeReference<List<Angajat>>() {
            });


            return persoane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void afisarepeste2500(List<Angajat> angajati) {

        Predicate<Angajat> arepeste = angajat -> angajat.getSalariu() > 2500;

        for (Angajat angajat : angajati) {
            if (arepeste.test(angajat)) {
                System.out.println(angajat);
            }
        }

    }

    public static List<Angajat> Lunaaprilie(List<Angajat> angajati) {

        Predicate<Angajat> ok = angajat ->
                angajat.getData_angajarii().getYear() == LocalDateTime.now().minusYears(1).getYear() && angajat.getData_angajarii().getMonthValue()==4 && (angajat.getPostul().contains("sef") || angajat.getPostul().contains("director"));


List<Angajat> buna = angajati.stream().filter(ok).collect(Collectors.toList());

return buna;
    }

    public static List<String> numeleupper(List<Angajat> angajati) {

        List<String> nume = angajati.stream().map(angajat -> angajat.getNume().toUpperCase()).collect(Collectors.toList());
        return nume;

    }

    public static void farafunctideconducere(List<Angajat> angajati) {


        Predicate<Angajat> ok = angajat -> !angajat.getPostul().contains("sef") && !angajat.getPostul().contains("director");

        angajati.stream().filter(ok).sorted(Comparator.comparing(Angajat::getSalariu).reversed()).forEach(angajat -> System.out.println(angajat));
    }

    public static void salarimaimicide3000(List<Angajat> angajati) {

        Predicate<Angajat> ok = angajat -> angajat.getSalariu() < 3000;

        List<Float> numere = angajati.stream().filter(ok).map(angajat -> angajat.getSalariu()).collect(Collectors.toList());
        numere.forEach(System.out::println);

    }

    public static void primulangajat(List<Angajat> angajati) {


        angajati.stream().min(Comparator.comparing(Angajat::getData_angajarii)).ifPresent(angajat -> System.out.println(angajat));
    }

    public static void minmaxavg(List<Angajat> angajati) {

        DoubleSummaryStatistics lista = angajati.stream().collect(Collectors.summarizingDouble(Angajat::getSalariu));
        System.out.println("min: " + lista.getMin());
        System.out.println("max: " + lista.getMax());
        System.out.println("avg: " + lista.getAverage());
    }


    public static void eion(List<Angajat> angajati) {

        Optional<Angajat> bun = angajati.stream().filter(angajat -> angajat.getNume().contains("Ion")).findAny();
        if (bun.isPresent()) {
            System.out.println("Exista un Ion");
        }
        else{
            System.out.println("No exista un Ion");
        }
    }

    public static void cativara(List<Angajat> angajati) {

        int cati = (int)  angajati.stream().filter(angajat -> angajat.getData_angajarii().getYear() == LocalDate.now().minusYears(1).getYear() && (angajat.data_angajarii.getMonthValue()==6 || angajat.data_angajarii.getMonthValue()==7 ||angajat.data_angajarii.getMonthValue()==8)).count();
        System.out.println(cati);

    }

    public static void main(String[] args) {

        List<Angajat> angajati = citire();


        System.out.println("EX1");
        angajati.forEach(System.out::println);

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println("EX2");
        afisarepeste2500(angajati);

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println("EX3");
        List<Angajat> ex3 = Lunaaprilie(angajati);
        ex3.forEach(System.out::println);


        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println("EX4");
        farafunctideconducere(angajati);

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println("EX5");
        List<String> nume = numeleupper(angajati);
        nume.forEach(System.out::println);

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println("EX6");
        salarimaimicide3000(angajati);

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }


        System.out.println("EX7");
        primulangajat(angajati);

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println("EX8");
        minmaxavg(angajati);

        for (int i = 0; i < 5; i++) {
            System.out.println();
        }

        System.out.println("EX9");
        eion(angajati);



        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
        System.out.println("EX10");
        cativara(angajati);

    }


}


