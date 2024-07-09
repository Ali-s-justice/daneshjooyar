package MVC_PROJECT;

import okhttp3.*;

import java.io.IOException;

public class ChatGptClient {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        String userMessage = "Hello, World!"; // شما می‌توانید این خط را جهت دریافت رشته از کاربر تغییر دهید

        RequestBody requestBody = new FormBody.Builder()
                .add("message", userMessage)
                .build();

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/engines/text-davinci-002/completions")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer YOUR_API_KEY") // لطفا API Key خود را قرار دهید
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println("Server response: " + responseBody);
            } else {
                System.out.println("Error: " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}