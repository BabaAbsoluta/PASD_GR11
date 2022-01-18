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
import java.util.HashMap;
import java.util.List;

/**
 * TODO: Replace the print statements of the exceptions
 */

public class SupplierAPI {

    HttpClient client;
    private ObjectMapper objectMapper;
    @Getter
    protected List<SupplierProducts> supplierProductsList;
    @Getter
    protected List<Order> supplierOrderList;
    @Getter
    protected List<Delivery> supplierDeliveryList;
    private List<OrderLine> orderLineList;

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

    private SupplierAPI() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.supplierProductsList = new ArrayList<>();
        this.supplierOrderList = new ArrayList<>();
        this.supplierDeliveryList = new ArrayList<>();
        this.orderLineList = new ArrayList<>();
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

    public Order createOrderAPI(Order order) throws IOException, InterruptedException, JSONException {

        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(order);

        System.out.println("Create order with order_id: " + order.getOrder_id());

        HttpRequest requestCreateOrder = HttpRequest.newBuilder()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(SUPPLIER_API_ORDER_URL))
                .build();

        HttpResponse<String> response = client.send(requestCreateOrder, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return parseCreatedOrder(response.body());
    }

    private Order parseCreatedOrder(String responseBody) throws JSONException {
        supplierOrderList.clear();
        JSONObject order = new JSONObject(responseBody);
        int id = order.getInt("id");
        int buyer = order.getInt("buyer");
        boolean is_processed = order.getBoolean("is_processed");
        Order newOrder = new Order(id, buyer, is_processed);

        System.out.println("in the parse: " + newOrder);
        return newOrder;
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
            parseAllOrders(response.body());
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseAllOrders(String responseBody) throws JSONException {
        supplierOrderList.clear();
        JSONArray orders = new JSONArray(responseBody);
        for (int i = 0; i < orders.length(); i++) {
            JSONObject order = orders.getJSONObject(i);
            int id = order.getInt("id");
            int buyer = order.getInt("buyer");
            boolean is_processed = order.getBoolean("is_processed");

            Order newOrder = new Order(id, buyer, is_processed);
            supplierOrderList.add(i, newOrder);
        }
        System.out.println(supplierOrderList);
    }

    public List<OrderLine> getOrderDetails(int order_id){
        Integer int_id = new Integer(order_id);
        String id = int_id.toString();

        HttpRequest requestGetOrder = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .uri(URI.create(SUPPLIER_API_ORDER_URL+id))
                .build();

        try {
            HttpResponse<String> response = client.send(requestGetOrder, HttpResponse.BodyHandlers.ofString());
            System.out.println("Get order_id: " + order_id);
            return parseOrder(response.body());
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<OrderLine> parseOrder(String responseBody) throws JSONException {
        orderLineList.clear();
        JSONObject order = new JSONObject(responseBody);
        System.out.println(order);
        JSONArray orderLinesArray = order.getJSONArray("orderlines");
        System.out.println(orderLinesArray);
        System.out.println(orderLinesArray.length());
        for (int i = 0; i < orderLinesArray.length(); i++) {
            JSONObject orderLines = orderLinesArray.getJSONObject(i);
            int product_id = orderLines.getInt("product");
            int orderLine_id = orderLines.getInt("id");
            int nr_of_product = orderLines.getInt("nr_of_products");
            int order_id = orderLines.getInt("order");

            OrderLine newOrderLine = new OrderLine(product_id, order_id, nr_of_product, orderLine_id);
            orderLineList.add(i, newOrderLine);
        }
        return orderLineList;
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

        System.out.println(requestBody);
        System.out.println("Send order with order_id: " + order.getOrder_id());

        HttpRequest requestCreateOrder = HttpRequest.newBuilder()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(SUPPLIER_API_SEND_ORDER_URL))
                .build();

        HttpResponse<String> response = client.send(requestCreateOrder, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());

    }

    public void viewDeliveryList(){
        HttpRequest requestGetDelivery = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Token ce0058bb4ad8047d7b0d87bc44843e4d35da5695")
                .uri(URI.create(SUPPLIER_API_DELIVERY_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(requestGetDelivery, HttpResponse.BodyHandlers.ofString());
            System.out.println("Get all deliveries:");
            parseDelivery(response.body());
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseDelivery(String responseBody) throws JSONException {
        supplierDeliveryList.clear();
        JSONArray deliveries = new JSONArray(responseBody);
        for (int i = 0; i < deliveries.length(); i++) {
            JSONObject order = deliveries.getJSONObject(i);
            int delivery_id = order.getInt("id");
            String date_time = order.getString("date_time");
            int order_id = order.getInt("order");

            Delivery newDelivery = new Delivery(delivery_id, date_time, order_id);
            supplierDeliveryList.add(i, newDelivery);
        }
        System.out.println(supplierDeliveryList);
    }
}
