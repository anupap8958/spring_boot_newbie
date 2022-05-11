package com.prior.newbie.service;

import com.prior.newbie.entities.Train;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RestTemplateService {

    private final static String url = "https://61970d0aaf46280017e7e3c5.mockapi.io/api/v1/train/users";
    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> getAllUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    public Train getUserById(String id) {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        return restTemplate.getForObject(url + "/" + id, Train.class, param);
    }

    public ResponseEntity<String> createUser(Train userTrain) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Train> entity = new HttpEntity<Train>(userTrain, headers);

        userTrain.setCreatedAt(LocalDateTime.now().toString());

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public String updateUser(Map<String, Object> inputs, String id) {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        Train user = restTemplate.getForObject(url + "/" + id, Train.class, param);

        user.setName(inputs.containsKey("name") ? inputs.get("name").toString() : user.getName());
        user.setAvatar(inputs.containsKey("avatar") ? inputs.get("avatar").toString() : user.getAvatar());
        user.setRank(inputs.containsKey("rank") ? (int)inputs.get("rank") : user.getRank());
        user.setColorCode(inputs.containsKey("colorCode") ? inputs.get("colorCode").toString() : user.getColorCode());

        restTemplate.put(url + "/" + id, user, param);
        return "Updated Successfully";
    }

    public String delete(String id) {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        restTemplate.delete(url + "/" + id, param);
        return "Deleted " + id + " Successfully";
    }
}
