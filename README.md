# Eshop
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=adi-himawan_tutorial-1)
<br>
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=adi-himawan_tutorial-1&metric=coverage)](https://sonarcloud.io/summary/new_code?id=adi-himawan_tutorial-1)

Tautan menuju aplikasi: [Eshop](eshop-adi-himawan.koyeb.app)

<details>
<summary> Tutorial 1 </summary>

## Reflection 1
#### You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code. If you find any mistake in your source code, please explain how to improve your code. Please write your reflection inside the repository's README.md file.

Setelah me-review lagi source code yang sudah dituliskan, berikut ini adalah beberapa prinsip clean code yang sudah saya ikuti.
- Meaningful Names: Penamaan variabel, function, dan class tidak ada yang berupa singkatan sehingga sudah self-explanatory.
- Function: Function yang dibuat hanya berfokus pada satu kegunaan dan tidak ada yang menggunakan global variable untuk menghindari side effects.
- Layout and Formatting. Code formatting seperti indentation, variable declaration, serta vertical ordering sudah diperhatikan agar layout kode terlihat lebih rapi.
- Objects and Data Structures. Untuk meningkatkan keamanan proses akses data pada model Product.java, saya sudah menggunakan private variable beserta getter dan setter dari library Lombok.
- Version Control. Untuk mempermudah proses pengembangan program, saya mengimplementasikan feature branch workflow dengan membuat enam branch berbeda.

Sementara itu, berikut ini adalah sejumlah hal terkait clean code yang masih dapat ditingkatkan.
- Comments: Pada beberapa potongan kode, saya merasa saya masih bisa menambahkan lebih banyak comment bertipe explanation of intents untuk mendeskripsikan logika dan struktur kode secara lebih rinci. Misalnya pada function edit di ProductRepository.java.
- Error Handling. Pada function findById di ProductRepository.java, saya masih membuat function yang me-return nilai null. Untuk semakin meningkatkan aspek clean code dari function ini, saya bisa menggunakan beberapa alternatif lain seperti mengimplementasikan Exception.

## Reflection 2
#### After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

Setelah membuat unit test, saya merasa semakin yakin dengan kebenaran dari program yang sudah dibuat. Selain itu, saya juga merasa terbantu karena adanya unit test membuat saya tidak perlu melakukan pengecekan secara manual. 

Menurut saya, tidak ada jumlah pasti dari banyaknya unit test yang perlu dibuat. Jumlah unit test sendiri nyatanya sangat tergantung dengan kompleksitas setiap program. Oleh karena itu, dibanding berfokus terhadap jumlah, developer seharusnya lebih berfokus untuk membuat test case yang terdiri dari skenario positif serta skenario negatif.

Secara definisi, 100% code coverage hanya menjamin semua baris dalam program kita sudah dipanggil setidaknya sekali ketika testing dilakukan. Oleh karenanya, 100% code coverage tidak menjamin program kita untuk sepenuhnya terbebas dari bug atau error yang misalnya ada di edge case. 

#### Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! Please write your reflection inside the repository's README.md file.

Menurut saya, membuat functional test suite dengan proses setup yang sama di sebuah class baru merupakan salah satu contoh code redundancy. Hal ini bukanlah langkah yang tepat untuk dilakukan karena dapat menurunkan code cleanliness.

Dibanding memisahkannya ke dalam dua class yang berbeda, saya merasa akan jauh lebih baik jika functional test suite yang baru ini ditambahkan di CreateProductFunctionalTest.java juga. Setelah itu, buatkan juga beberapa method baru untuk proses setup agar proses ini tidak perlu dijalankan lebih dari sekali.

</details>

<details>
<summary> Tutorial 2 </summary>

#### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
- JUnit5 test classes and methods should have default package visibility. Meski JUnit 5 tidak terlalu terpengaruh oleh visibility test classes serta visibility methods, penggunaan default package visibility lebih disarankan karena dapat meningkatkan code readability. Oleh karena itu, langkah yang saya lakukan adalah menghapus public modifier pada semua test classes.
- Call to Mockito method "verify", "when" or "given" should be simplified. Penggunaan eq() sebenarnya tidak selalu diperlukan sehingga dapat dihilangkan untuk membuat kode yang lebih ringkas. Oleh karena itu, langkah yang saya lakukan adalah menghapus semua penggunaan eq() pada ProductControllerTest.java.
- Tests should include assertions. Untuk memastikan kode yang dijalankan sudah sepenuhnya benar, assertions harus digunakan di dalam setiap test case. Tanpa assertions, test case hanya dapat memastikan ada atau tidaknya thrown exception. Oleh karena itu, langkah yang saya lakukan adalah menambahkan penggunaan assertions pada EshopApplicationTests.java.
- Field dependency injection should be avoided. Field injection kurang disarankan karena dapat membuat unit testing menjadi lebih sulit. Oleh karena itu, langkah yang saya lakukan adalah mengganti field injection dengan constructor injection.

#### 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
Setiap kali ada perubahan kode dalam suatu branch, kode akan secara otomatis diperiksa menggunakan test case yang sudah ditentukan dalam ci.yml serta dianalis dengan SonarCloud. Setelah pengecekan berhasil, kita dapat melakukan pull request untuk menggabungkan semua perubahan kode di branch ke main. Di main, kode akan diperiksa sekali lagi oleh Scorecard serta di-deploy secara otomatis ke PaaS Koyeb. Berhubung proyek ini memiliki proses pengecekan serta deployment yang berjalan secara otomatis, dapat disimpulkan bahwa proyek ini telah mengimplementasikan konsep CI/CD.

</details>