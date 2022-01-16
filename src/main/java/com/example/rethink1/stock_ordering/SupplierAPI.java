package com.example.rethink1.stock_ordering;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SupplierAPI {
    HttpClient client;

    private static final String SUPPLIER_API_URL = "https://rethink-supplier.herokuapp.com/user/";
    private static final String SUPPLIER_API_PRODUCT_URL = "https://rethink-supplier.herokuapp.com/product/";
    private static final String SUPPLIER_API_ORDER_URL = "https://rethink-supplier.herokuapp.com/order/";

    private static SupplierAPI instance;

    public static SupplierAPI getInstance(){
        if (instance == null) {
            instance = new SupplierAPI();
        }
        return instance;
    }

    public SupplierAPI() {
        this.client = HttpClient.newHttpClient();
        createConnection();
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
            System.out.println("Get all products:");
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createOrderAPI(){

    }

    private void getALLOrdersAPI(){
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

    private void addOrderLineAPI(){

    }

    private void sendOrderAPI(){

    }

    private void viewDeliveriesAPI(){

    }
}
