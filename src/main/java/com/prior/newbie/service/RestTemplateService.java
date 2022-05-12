package com.prior.newbie.service;

import com.prior.newbie.entities.Color;
import com.prior.newbie.entities.Train;
import com.prior.newbie.entities.TrainV2;
import com.prior.newbie.repository.ColorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestTemplateService {

    @Autowired
    private ColorRepository colorRepository;
    private final static String url = "https://61970d0aaf46280017e7e3c5.mockapi.io/api/v1/train/users";
    private RestTemplate restTemplate = new RestTemplate();

    public List<TrainV2> getAllUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Train>> rateResponse = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Train>>() {
        });

        List<Train> trainList = rateResponse.getBody();

        List<TrainV2> trainV2List = new ArrayList<>();

        List<Color> colors = colorRepository.findAll();

        Map<String, Color> colorStream = colors.stream()
                .collect(Collectors.toMap(color -> color.getColor_code(), color -> color));

        for (Train train : trainList) {
            TrainV2 trainV2 = new TrainV2();
            String colorCodeUpperCase = train.getColorCode().toUpperCase();
            if (colorStream.containsKey(colorCodeUpperCase)) {
                Color color = colorStream.get(colorCodeUpperCase);
                trainV2.setCreatedAt(train.getCreatedAt());
                trainV2.setName(train.getName());
                trainV2.setAvatar(train.getAvatar());
                trainV2.setRank(train.getRank());
                trainV2.setColorCode(train.getColorCode());
                trainV2.setColorName(color.getColor_name());
                trainV2.setColorDecimal(color.getDecimal_code());
                trainV2.setId(train.getId());
            } else {
                trainV2.setCreatedAt(train.getCreatedAt());
                trainV2.setName(train.getName());
                trainV2.setAvatar(train.getAvatar());
                trainV2.setRank(train.getRank());
                trainV2.setColorCode(train.getColorCode());
                trainV2.setId(train.getId());
            }
            // Solution 2
//            Optional<Color> colorOptional = colorRepository.findById(train.getColorCode().toUpperCase());
//            if (colorOptional.isEmpty()) {
//                trainV2.setCreatedAt(train.getCreatedAt());
//                trainV2.setName(train.getName());
//                trainV2.setAvatar(train.getAvatar());
//                trainV2.setRank(train.getRank());
//                trainV2.setColorCode(train.getColorCode());
//                trainV2.setId(train.getId());
//            } else {
//                Color color = colorOptional.get();
//                trainV2.setCreatedAt(train.getCreatedAt());
//                trainV2.setName(train.getName());
//                trainV2.setAvatar(train.getAvatar());
//                trainV2.setRank(train.getRank());
//                trainV2.setColorCode(train.getColorCode());
//                trainV2.setColorName(color.getColor_name());
//                trainV2.setColorDecimal(color.getDecimal_code());
//                trainV2.setId(train.getId());
//            }
            trainV2List.add(trainV2);
        }
        return trainV2List;
    }

    public TrainV2 getUserById(String id) {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        Train train = restTemplate.getForObject(url + "/" + id, Train.class, param);

        Optional<Color> colorOptional = colorRepository.findById(train.getColorCode());

        TrainV2 trainV2 = new TrainV2();

        if (colorOptional.isEmpty()) {
            trainV2.setCreatedAt(train.getCreatedAt());
            trainV2.setName(train.getName());
            trainV2.setAvatar(train.getAvatar());
            trainV2.setRank(train.getRank());
            trainV2.setColorCode(train.getColorCode());
            trainV2.setId(train.getId());
        } else {
            Color color = colorOptional.get();
            trainV2.setCreatedAt(train.getCreatedAt());
            trainV2.setName(train.getName());
            trainV2.setAvatar(train.getAvatar());
            trainV2.setRank(train.getRank());
            trainV2.setColorCode(train.getColorCode());
            trainV2.setColorName(color.getColor_name());
            trainV2.setColorDecimal(color.getDecimal_code());
            trainV2.setId(train.getId());
        }

        return trainV2;
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
