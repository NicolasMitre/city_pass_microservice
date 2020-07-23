package net.avalith.city_pass.paypal;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.paypal.models.Amount;
import net.avalith.city_pass.paypal.models.Item;
import net.avalith.city_pass.paypal.models.ItemList;
import net.avalith.city_pass.paypal.models.Payer;
import net.avalith.city_pass.paypal.models.Payment;
import net.avalith.city_pass.paypal.models.Transaction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PayPalApi {

    private final PayPalConfig paypalConfig;
    private final RestTemplate restTemplate;
    private final String getTokenUri = "https://api.sandbox.paypal.com/v1/oauth2/token?grant_type=client_credentials";
    private final String paymentBaseUri = "https://api.sandbox.paypal.com/v1/payments/payment";

    private String getToken() throws JsonProcessingException {
        HttpHeaders basicAuth = new HttpHeaders();
        basicAuth.setBasicAuth(paypalConfig.getClientId(), paypalConfig.getClientSecret());
        ResponseEntity<String> response = restTemplate.exchange
                (getTokenUri, HttpMethod.POST, new HttpEntity<>(basicAuth), String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        return jsonNode.get("access_token").asText();
    }

    public Payment createPayment(Purchase purchase) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode paymentJsonNode = objectMapper.createObjectNode();

        Map<String, String> redirectUrls = new HashMap<>();
        redirectUrls.put("return_url", paypalConfig.getSuccessUrl() + "/" + purchase.getIdPurchase());
        redirectUrls.put("cancel_url", paypalConfig.getFailureUrl() + "/" + purchase.getIdPurchase());

        String redirectUrlsJSON = objectMapper.writeValueAsString(redirectUrls);
        paymentJsonNode.set("redirect_urls", objectMapper.readTree(redirectUrlsJSON));

        Payer payer = Payer.builder().paymentMethod("paypal").build();
        String payerJSON = objectMapper.writeValueAsString(payer);

        paymentJsonNode.set("payer", objectMapper.readTree(payerJSON));

        Amount amount = Amount.builder()
                .total(String.format(Locale.US, "%.2f", purchase.getTotalPrice()))
                .currency("USD")
                .build();

        List<Item> items = purchase.getProductsBought()
                .stream()
                .map(ticket -> Item.builder()
                        .name(ticket.getName())
                        .quantity(String.valueOf(ticket.getQuantity()))
                        .price(String.format(Locale.US, "%.2f", ticket.getUnitPrice()))
                        .currency("USD")
                        .build())
                .collect(Collectors.toList());

        ItemList itemList = ItemList.builder()
                .items(items)
                .build();

        Transaction transaction = Transaction.builder()
                .amount(amount)
                .itemList(itemList)
                .build();

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        ArrayNode arrayNode = paymentJsonNode.putArray("transactions");

        for (Transaction t : transactionList) {
            String transactionJson = objectMapper.writeValueAsString(t);
            arrayNode.add(objectMapper.readTree(transactionJson));
        }

        paymentJsonNode.put("intent", "sale");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken());
        HttpEntity<String> request = new HttpEntity<>(paymentJsonNode.toString(), headers);
        //cachear excepciones de rest template client exception
        ResponseEntity<Payment> response = restTemplate.exchange(paymentBaseUri, HttpMethod.POST, request, Payment.class);

        return response.getBody();
    }

    public String executePayment(String paymentId, String payerId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();

        String executePaymentUri = paymentBaseUri + "/" + paymentId + "/execute";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken());
        requestBody.put("payer_id", payerId);

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        ResponseEntity<String> response = null;
        try{
            response = restTemplate.exchange(executePaymentUri, HttpMethod.POST, request, String.class);
        } catch (RestClientException ex){
            throw new PayPalApiException();
        }
        return response.toString();
    }
}
