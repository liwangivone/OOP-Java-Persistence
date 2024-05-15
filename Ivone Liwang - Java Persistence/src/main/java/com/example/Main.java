package com.example;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static Scanner myScanner = new Scanner(System.in);

    public static String getInput(String message) {
        try {
            System.out.print(message);
            String input = myScanner.nextLine().trim();

            if (input.matches("\\d+")) {
                throw new IllegalArgumentException("Input tidak boleh hanya berisi angka.");
            }

            else if (!Pattern.matches("[\\p{Alnum}]+", input)) {
                System.out.println("Input mengandung simbol. Harap masukkan bilangan bulat.");
                return getInput(message);
            }

            else if (input.isEmpty()) {
                System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
                return getInput(message);
            }

            return input;
        } catch (IllegalArgumentException ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return getInput(message);
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return getInput(message);
        }
    }

    public static int getInputInt(String message) {
        try {
            System.out.print(message);
            String inputStr = myScanner.nextLine().trim();
            if (inputStr.isEmpty()) {
                System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
                return getInputInt(message);
            }

            if (inputStr.contains(".") || inputStr.contains(",")) {
                System.out.println("Input tidak valid. Harap masukkan bilangan bulat.");
                return getInputInt(message);
            }

            Integer input = Integer.parseInt(inputStr);

            if (input < 0) {
                System.out.println("Input tidak boleh kurang dari atau sama dengan 0. Silakan coba lagi.");
                return getInputInt(message);
            }

            return input;
        } catch (NumberFormatException ex) {
            System.out.println("Input tidak valid. Harap masukkan bilangan bulat.");
            return getInputInt(message);
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return getInputInt(message);
        }
    }

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            boolean exit = true;
            while (exit) {
                tx = session.beginTransaction();
                System.out.println("Silahkan Memilih dari Pilihan yang Tersedia: ");
                System.out.println("1. Insert ");
                System.out.println("2. Update ");
                System.out.println("3. Delete ");
                System.out.println("4. Select ");
                System.out.println("5. Keluar Program ");
                // Perform database operations
                int choice = getInputInt("Masukkan pilihan anda: ");
                switch (choice) {
                    case 1:
                        Students newStudent = new Students();
                        newStudent.setName(getInput("Masukkan nama siswa: "));
                        newStudent.setAge(getInputInt("Masukkan umur siswa: "));
                        newStudent.setMajor(getInput("Masukkan nama jurusan: "));
                        session.save(newStudent);
                        tx.commit();
                        break;
                    case 2:
                        List<Students> result = session.createQuery("select s from Students s", Students.class).list();
                        System.out.println("== Daftar Siswa ==");
                        for (Students students : result) {
                            System.out.println(students.toString());
                        }
                        session.getTransaction().commit();
                        Long updateId = Long
                                .parseLong(String.valueOf(getInputInt("Masukkan ID siswa yang ingin diupdate: ")));
                        Students studentToUpdate = session.get(Students.class, updateId);
                        if (studentToUpdate != null) {
                            tx = session.beginTransaction();
                            System.out.println("Data siswa yang ingin diupdate: " + studentToUpdate);
                            studentToUpdate.setName(getInput("Masukkan nama siswa baru: "));
                            studentToUpdate.setAge(getInputInt("Masukkan umur siswa baru: "));
                            studentToUpdate.setMajor(getInput("Masukkan nama jurusan baru: "));
                            session.update(studentToUpdate);
                            tx.commit();
                        } else {
                            System.out.println("Siswa dengan ID tersebut tidak ditemukan.");
                        }
                        break;
                    case 3:
                        Long deleteId = Long
                                .parseLong(String.valueOf(getInputInt("Masukkan ID siswa yang ingin dihapus: ")));
                        Students studentToDelete = session.get(Students.class, deleteId);
                        if (studentToDelete != null) {
                            System.out.println("Data siswa yang dihapus: " + studentToDelete);
                            session.delete(studentToDelete);
                            tx.commit();
                        } else {
                            System.out.println("Siswa dengan ID tersebut tidak ditemukan.");
                        }
                        break;
                    case 4:
                        List<Students> getresult = session.createQuery("select s from Students s", Students.class)
                                .list();
                        System.out.println("== Daftar Siswa ==");
                        for (Students students : getresult) {
                            System.out.println(students.toString());
                        }
                        session.getTransaction().commit();
                        break;
                    case 5:
                        exit = false;
                        System.out.println("Anda Telah Keluar dari Program");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
                // tx.commit();
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
