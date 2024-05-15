Project ini merupakan implementasi dari sebuah metode, yaitu ORM (Object Relational Mapping) yang menggunakan bantuan dari Hibernate.
Pada dasarnya, ORM membantu kita untuk menyambungkan antara program OOP Java dengan database yang telah kita buat, memperbolehkan kita untuk mengisi, mengedit, maupun menghapus data pada database (CRUD) dan begitu juga sebaliknya.

Dalam folder src/main/java/com/example, terdapat 3 file java dan 1 file xml
File java ini terdiri atas:
1. Main.java
   Merupakan file utama yang di run untuk menjalankan program. Berisikan metode scanner (inputan user), condition statement, switch case untuk menyediakan 5 pilihan untuk user, beserta dengan try and catch untuk handling error pada inputan user.
2. Students.java
   Merupakan file yang berisi class "Students", berisikan seluruh atribut yang dimiliki oleh class "Students" beserta dengan getter dan setter nya
3. Utils,java
   Merupakan file tempat menaruh objek "SessionFactory" dari Hibernate agar dapat dengan mudah untuk diakses

Adapun file xml sebagai berikut:
1. Mapping.xml
   Merupakan file penting untuk menyambungkan (mapping) antara kodingan java dengan database yang telah dibuat sebelumnya
2. hibernate.cfg.xml (src/main/resources)
   Merupakan file yang berisi konfigurasi untuk mengatur koneksi dengan database
3. pom.xml
   Merupakan file yang berisikan kodingan untuk mengatur dependency yang akan digunakan, serta menjalankan semua method import pada file-file sebelumnya

Program ini bertujuan untuk memasukkan data dari mahasiswa, seperti id, nama, usia, dan jurusan.
Selain memasukkan data, program juga memberikan pilihan bagi user untuk memperbarui, memilih, serta menghapus data yang telah dibuat sebelumnya.
User dapat memilih opsi nomor "5" untuk keluar dari program
