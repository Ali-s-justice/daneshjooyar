//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import org.json.JSONObject;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            // ساخت یک کلاینت HTTP
//            HttpClient client = HttpClient.newHttpClient();
//
//            // ساخت یک درخواست HTTP
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://api.keybit.ir/time/"))
//                    .build();
//
//            // ارسال درخواست و دریافت پاسخ
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // تبدیل پاسخ به JSON
//            JSONObject jsonResponse = new JSONObject(response.body());
//
//            // دریافت تاریخ شمسی
//            String persianDate = jsonResponse.getJSONObject("date").getString("persian");
//
//            // نمایش تاریخ شمسی
//            System.out.println("تاریخ امروز به شمسی: " + persianDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
