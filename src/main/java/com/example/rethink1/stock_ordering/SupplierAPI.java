package com.example.rethink1.stock_ordering;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SupplierAPI {

    HttpClient client;
    private ObjectMapper objectMapper;
    protected List<SupplierProducts> supplierProductsList;

    private static final String SUPPLIER_API_URL = "https://rethink-supplier.herokuapp.com/user/";
    private static final String SUPPLIER_API_PRODUCT_URL = "https://rethink-supplier.herokuapp.com/product/";
    private static final String SUPPLIER_API_ORDER_URL = "https://rethink-supplier.herokuapp.com/order/";
    private static final String SUPPLIER_API_DELIVERY_URL = "https://rethink-supplier.herokuapp.com/delivery/";
    private static final String SUPPLIER_API_ADD_ORDERLINE_URL = "https://rethink-supplier.herokuapp.com/orderline/";
    private static final String SUPPLIER_API_SEND_ORDER_URL = "https://rethink-supplier.herokuapp.com/send_order/";

    private static SupplierAPI instance;

    public static SupplierAPI getInstance(){
        if (instance == null) {
            instance = new SupplierAPI();
        }
        return instance;
    }

    public SupplierAPI() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.supplierProductsList = new ArrayList<>();
        createConnection();
        getAllProductsAPI();
    }

    public void createConnection(){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .uri(URI.create(SUPPLIER_API_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Connection with supplierAPI is made, user information:");
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void getAllProductsAPI(){
        HttpRequest requestGetProduct = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .uri(URI.create(SUPPLIER_API_PRODUCT_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(requestGetProduct, HttpResponse.BodyHandlers.ofString());
            String listStringResponse = response.body();
            System.out.println("Get all products:");
            System.out.println(listStringResponse);

            parseProductSupplier(listStringResponse);

        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseProductSupplier(String responseBody) throws JSONException {
        JSONArray products = new JSONArray(responseBody);
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            int id = product.getInt("id");
            String name = product.getString("name");
            String EAN_13 = product.getString("EAN_13");
            double vat_rate = product.getDouble("vat_rate");
            int price_in_cents = product.getInt("price_in_cents");

            SupplierProducts product1 = new SupplierProducts(id, name, EAN_13, vat_rate, price_in_cents);
            supplierProductsList.add(i, product1);
        }
        System.out.println(supplierProductsList);
    }

    public void createOrderAPI(Order order) throws IOException, InterruptedException {

        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(order);

        System.out.println("Create order with order_id: " + order.getId());

        HttpRequest requestCreateOrder = HttpRequest.newBuilder()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(SUPPLIER_API_ORDER_URL))
                .build();

        HttpResponse<String> response = client.send(requestCreateOrder, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());

    }

    public void getALLOrdersAPI(){
        HttpRequest requestGetOrder = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .uri(URI.create(SUPPLIER_API_ORDER_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(requestGetOrder, HttpResponse.BodyHandlers.ofString());
            System.out.println("Get all orders:");
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addOrderLineAPI(OrderLine orderLine) throws IOException, InterruptedException {

        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(orderLine);

        System.out.println("Add orderLine: " + orderLine);
        System.out.println(requestBody);

        HttpRequest requestCreateOrderLine = HttpRequest.newBuilder()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(SUPPLIER_API_ADD_ORDERLINE_URL))
                .build();

        HttpResponse<String> response = client.send(requestCreateOrderLine, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    public void sendOrderAPI(Order order) throws IOException, InterruptedException {
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(order);

        System.out.println("Send order with order_id: " + order.getId());

        HttpRequest requestCreateOrder = HttpRequest.newBuilder()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(SUPPLIER_API_SEND_ORDER_URL))
                .build();

        HttpResponse<String> response = client.send(requestCreateOrder, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());

    }

    public void viewDeliveriesAPI(){
        HttpRequest requestGetDelivery = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .uri(URI.create(SUPPLIER_API_DELIVERY_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(requestGetDelivery, HttpResponse.BodyHandlers.ofString());
            System.out.println("Get all deliveries:");
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
