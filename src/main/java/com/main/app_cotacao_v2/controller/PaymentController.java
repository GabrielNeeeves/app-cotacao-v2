package com.main.app_cotacao_v2.controller;

import com.main.app_cotacao_v2.model.payment.PaymentPreferenceRequestDto;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pagamentos")
public class PaymentController {

    @PostMapping("/criar_preferencia")
    public ResponseEntity<?> createPreference(@RequestBody PaymentPreferenceRequestDto requestBody) {
        try {
            // Configure o SDK com seu Access Token
            com.mercadopago.MercadoPagoConfig.setAccessToken("APP_USR-5950841880623430-092211-ce22546ae0ef08c6b49c2df84fc5f6f9-2707566340");

            // Cria o item da compra usando os dados recebidos
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .title(requestBody.getTitulo())
                    .quantity(requestBody.getQuantidade())
                    .unitPrice(requestBody.getPreco())
                    .currencyId("BRL")

                    .build();

            // Monta a preference
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .backUrls(
                            PreferenceBackUrlsRequest.builder()
                                    .success("http://localhost:5500/pages/listarOfertas/listarOfertas.html")
                                    .build())
                    .items(Collections.singletonList(item))
                    .build();

            // Cria no Mercado Pago
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            // Retorna o ID da preference para o frontend
            // return ResponseEntity.ok(Collections.singletonMap("preferenceId", preference.getId()));
            Map<String, Object> response = new HashMap<>();
            response.put("preferenceId", preference.getId());
            response.put("initPoint", preference.getSandboxInitPoint()); // sandbox init point -> testes

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar preferência: " + e.getMessage());
        }
    }
}

