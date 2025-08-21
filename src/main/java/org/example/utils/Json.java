
package org.example.utils;
import org.example.controller.BankController;
import org.example.model.*;
import org.json.JSONObject;
import java.io.*;

public class Json {

    public static void createJSON(BankController controller,int userId) {

        try {
            Users user = controller.getUsersService().getUserById(userId);
            Accounts account = controller.getAccountsService().getAccountByUserId(userId);

            if (user == null || account == null) {
                System.out.println("Kullanici veya hesap bulunamadi.");
                return;
            }

            //Create JSON
            JSONObject json = new JSONObject();
            json.put("id", user.getId());
            json.put("username", user.getUsername());
            json.put("password", user.getPassword());

            JSONObject accountJson = new JSONObject();
            accountJson.put("account_id", account.getId());
            accountJson.put("balance", account.getBalance());
            json.put("account", accountJson);

            // create json folder if dont json folder
            File jsonFolder = new File("JSON");
            if (!jsonFolder.exists()) {
                jsonFolder.mkdir();
            }

            // write to file
            File file = new File(jsonFolder, "user_" + userId + ".json");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json.toString(4));
            }

            System.out.println("JSON dosyasi olusturuldu: " + file.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }


    }
    }





