package org.example;

import org.example.controller.BankController;
import org.example.model.Users;
import org.example.repository.AccountsRepository;
import org.example.repository.TransactionsRepository;
import org.example.repository.UsersRepository;
import org.example.service.AccountsService;
import org.example.service.TransactionsService;
import org.example.service.UsersService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Repository'leri oluşturduk
        UsersRepository usersRepo = new UsersRepository();
        AccountsRepository accountsRepo = new AccountsRepository();
        TransactionsRepository transactionsRepo = new TransactionsRepository();

        // Servisleri usersrepoya bagladık
        UsersService usersService = new UsersService(usersRepo);
        AccountsService accountsService = new AccountsService(accountsRepo);
        TransactionsService transactionsService = new TransactionsService(transactionsRepo, accountsRepo);

        // Controlleri servislere bağladık
        BankController controller = new BankController(usersService, accountsService, transactionsService);

        int userId = -1;
        boolean devam = true;

        while (devam) {
            System.out.println("============================================");
            System.out.println(" BankApp - Ana Menu");
            System.out.println("============================================");
            System.out.println("1. Giris Yap");
            System.out.println("2. Yeni Kayit Olustur");
            System.out.println("3. Bakiye Goruntule");
            System.out.println("4. Para Yatir");
            System.out.println("5. Para Cek");
            System.out.println("6. Islem Gecmisini Gor");
            System.out.println("7. Cikis");
            System.out.print("Seciminiz: ");

            int secim = Integer.parseInt(scanner.nextLine());



            switch (secim) {
                case 1:
                    System.out.print("Kullanici adi: ");
                    String username = scanner.nextLine();
                    System.out.print("Sifre: ");
                    String password = scanner.nextLine();
                    Users user = controller.login(username, password);
                    if (user != null) {
                        userId = user.getId();
                        System.out.println("Giris basarili! Hosgeldiniz " + username);
                    } else {
                        System.out.println("Kullanici adi veya sifre hatali!");
                    }
                    break;

                case 2:
                    System.out.print("Yeni kullanici adi: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Yeni sifre: ");
                    String newPassword = scanner.nextLine();

                    boolean created = controller.register(newUsername, newPassword);
                    if (created) {
                        Users newUser = controller.login(newUsername, newPassword);
                        if (newUser != null) {
                            userId = newUser.getId();

                            // account add
                            accountsService.createAccount(userId,0.0);

                            System.out.println("Kayit basarili! Hosgeldiniz " + newUsername);
                        } else {
                            System.out.println("Kayit sonrası login basarisiz!");
                        }
                    } else {
                        System.out.println("Kayit basarisiz, kullanici adi alinmis olabilir.");
                    }
                    break;


                case 3:
                    if (userId != -1) {
                        System.out.println("Bakiyeniz: " + controller.getBalance(userId) + " TL");
                    } else {
                        System.out.println("Lutfen giris yapin.");
                    }
                    break;

                case 4:
                    if (userId != -1) {
                        System.out.print("Yatirmak istediginiz miktar: ");
                        double amount;
                        try {
                            amount = Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Gecersiz miktar!");
                            break;
                        }

                        if (controller.deposit(userId, amount)) System.out.println(amount + " TL yatirildi.");
                    }else System.out.println("Lutfen giris yapin.");
                    break;

                case 5:
                    if (userId != -1) {
                        System.out.print("Cekmek istediginiz miktar: ");
                        double amount;
                        try {
                            amount = Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Gecersiz miktar!");
                            break;
                        }

                        if (controller.withdraw(userId, amount)) System.out.println(amount + " TL cekildi.");
                        else System.out.println("Yetersiz bakiye!");
                    } else System.out.println("Lutfen giris yapin.");
                    break;

                case 6:
                    if (userId != -1) {
                        System.out.println("=== Islem Gecmisi ===");
                        controller.getTransactions(userId).forEach(t ->
                                System.out.println(t.getType() + " : " + t.getAmount() + " TL -> " + t.getCreatedAt())
                        );
                    } else System.out.println("Lutfen giris yapin.");
                    break;

                case 7:
                    devam = false;
                    break;

                default:
                    System.out.println("Gecersiz secim!");
            }
            System.out.println();
        }

        scanner.close();
    }
}
