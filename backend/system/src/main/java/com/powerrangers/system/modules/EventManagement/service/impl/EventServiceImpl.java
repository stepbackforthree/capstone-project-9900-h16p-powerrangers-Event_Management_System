package com.powerrangers.system.modules.EventManagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.powerrangers.system.modules.EventManagement.dao.EventMapper;
import com.powerrangers.system.modules.EventManagement.dao.EventTicketMapper;
import com.powerrangers.system.modules.EventManagement.service.EventService;
import com.powerrangers.system.modules.EventManagement.service.dto.*;
import com.powerrangers.system.modules.UserAccess.dao.UserMapper;
import com.powerrangers.system.modules.UserAccess.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final EventMapper eventMapper;

    @Autowired
    private final EventTicketMapper eventTicketMapper;

    @Value("${DefaultImage.eventImage}")
    private String defaultEventImage;

    @Override
    public ResponseEntity<Object> createEvent(String token, SmallEventDTO smallEventDTO) {

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        EventModifyDTO eventModifyDTO = new EventModifyDTO();
        eventModifyDTO.setEventName(smallEventDTO.getEventName());
        eventModifyDTO.setHostId(currUser.getId());
        smallEventDTO.setHostId(currUser.getId());

        if (smallEventDTO.getImage().equals("") || smallEventDTO.getImage().length() == 0) {
            smallEventDTO.setImage(defaultEventImage);
        }

        if (eventMapper.checkExist(eventModifyDTO) > 0) {
            responseBody.put("error", "Duplicated event name!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        eventMapper.createEvent(smallEventDTO);
        eventMapper.createEventInsertFullPriceTicket(smallEventDTO);

        responseBody.put("msg", "Create event succeed!");
        return new ResponseEntity<>(responseBody ,HttpStatus.OK);
    }

    @Override
    public Boolean checkExist(EventModifyDTO eventModifyDTO) {
        return eventMapper.checkExist(eventModifyDTO) > 0;
    }

    @Override
    public ResponseEntity<Object> updateEventName(String token, EventModifyDTO eventModifyDTO){

        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            String eventName = eventModifyDTO.getEventName();
            eventModifyDTO.setEventName(eventModifyDTO.getNewString());
            if (checkExist(eventModifyDTO)){
                responseBody.put("error", "The new event name has existed");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }else{
                eventModifyDTO.setEventName(eventName);
                eventMapper.updateEventName(eventModifyDTO);
                responseBody.put("msg", "Update event name succeed!");
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }

        }else{
            responseBody.put("error", "The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Object> updateEventTime(String token, EventModifyDTO eventModifyDTO){
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventTime(eventModifyDTO);

            responseBody.put("msg", "Update event time succeed!");

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{

            responseBody.put("error", "The event you want to modify did not exist");

            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Object> updateEventDescription(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventDescription(eventModifyDTO);
            responseBody.put("msg","Update event description succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateEventAddress(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());


        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventAddress(eventModifyDTO);
            responseBody.put("msg","Update event address succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> updateEventType(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            eventMapper.updateEventType(eventModifyDTO);
            responseBody.put("msg","Update event type succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> changeEventCancelState(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            eventMapper.changeEventCancelState(eventModifyDTO);
            responseBody.put("msg","Update event cancel_state succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> changeEventDisplayState(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            eventMapper.changeEventDisplayState(eventModifyDTO);
            responseBody.put("msg","Update event display_state succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> changeEventTag(String token, EventModifyDTO eventModifyDTO) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_"+token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        eventModifyDTO.setHostId(currUser.getId());

        if (checkExist(eventModifyDTO)){
            eventMapper.changeEventTag(eventModifyDTO);
            responseBody.put("msg","Update event tag succeed!");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }else{
            responseBody.put("error","The event you want to modify did not exist");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> queryEvent(String token, String eventName, String hostName) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        EventModifyDTO eventModifyDTO = new EventModifyDTO();
        if (currUser != null) {
            eventModifyDTO.setHostId(userMapper.getUserIdByUserName(hostName));
            eventModifyDTO.setEventName(eventName);
        } else {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");

        EventDTO eventDTO = eventMapper.queryEvent(eventModifyDTO);

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setEventName(eventName);
        ticketDTO.setHostName(hostName);

        eventDTO.setTickets(eventTicketMapper.getTicketType(ticketDTO));

        return ResponseEntity.ok().headers(headers)
                .body(JSON.parseObject(JSON.toJSONString(eventDTO)));
    }

    @Override
    public ResponseEntity<Object> getEvents(String token, String hostName) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(eventMapper.getEvents(userMapper.getUserIdByUserName(hostName)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllEvents(EventFilterDTO eventFilterDTO) {

        return new ResponseEntity<>(eventMapper.getAllEvents(eventFilterDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> searchEvents(String token, String keyWords) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if(keyWords != null && keyWords.length() != 0) {
            return new ResponseEntity<>(eventMapper.searchEvents(keyWords), HttpStatus.OK);
        }

        responseBody.put("error","empty content to search");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> checkSpendingHistory(String token, String userName) {
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        Map<String, String> responseBody = new HashMap<>();

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        if(eventMapper.checkUserExist(userName)>0){
            if(eventMapper.checkSpendingHistory(userName)>0){
                return new ResponseEntity<>(eventMapper.getSpendingHistory(userName), HttpStatus.BAD_REQUEST);
            }
            responseBody.put("error", "the user did not spend anything");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        responseBody.put("error", "the user did not exist");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @Override
    public void updateStarLevel(EventDTO eventDTO) {
        eventMapper.updateStarLevel(eventDTO);
    }

    @Override
    public ResponseEntity<Object> getOneMonthEvents() {

        return new ResponseEntity<>(eventMapper.getOneMonthEvents(), HttpStatus.OK);
    }

    public List filterMethod(String[] a , HashSet filterWords){
        List<String> res = new ArrayList<>();

        for(String w: a){
            if(!filterWords.contains(w.trim()) && !"".equals(w) && !" ".equals(w)){
                res.add(w.trim());
            }
        }

        return res;
    }

    public Double cal(List<String> history , List<String> event){

        HashMap<String,Integer> totalWords = new HashMap<>();
        int count = 0;
        for (int i = 0; i < history.size(); i++) {
            if(totalWords.get(history.get(i)) == null){
                totalWords.put(history.get(i),count);
                count++;
            }
        }
        for (int i = 0; i < event.size(); i++) {
            if(totalWords.get(event.get(i)) == null){
                totalWords.put(event.get(i),count);
                count++;
            }
        }

        List<Integer> his = new ArrayList<>(count);
        List<Integer> eve = new ArrayList<>(count);
        for(int i = 0; i<count; i++){
            his.add(0);
        }

        for(int i = 0; i<count; i++){
            eve.add(0);
        }

        for (String e: history){
            his.set(totalWords.get(e), his.get(totalWords.get(e)) + 1);}
        for (String e: event){
            eve.set(totalWords.get(e), eve.get(totalWords.get(e)) + 1);
        }
        double sumHis = 0.0;
        double sumeve = 0.0;
        double sum = 0;
        for(int i = 0; i< history.size(); i++){
            sum += his.get(i)*eve.get(i);
            sumHis += Math.pow(his.get(i),2);
            sumeve += Math.pow(eve.get(i),2);
        }
        return sum/(Math.pow(sumHis,0.5)+Math.pow(sumeve,0.5));
    }

    @Override
    public ResponseEntity<Object> getRecommendation(String token) {

        Map<String, String> responseBody = new HashMap<>();
        User currUser = JSON.parseObject(redisTemplate.opsForValue().get("token_" + token), User.class);

        if (currUser == null) {
            responseBody.put("error", "token is invalid!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if (eventMapper.checkSpendingHistory(currUser.getUserName()) == 0) {
            return new ResponseEntity<>(eventMapper.randomRecommendation(), HttpStatus.OK);

        }

        if (eventMapper.checkSpendingHistory(currUser.getUserName()) > 0) {
            List<OrderDTO> spendingHistory = eventMapper.getSpendingHistory(currUser.getUserName());
            List<String> eventsDecrip = new ArrayList<>();
            HashSet<String> eventType = new HashSet<>();
            HashMap<EventDTO,Double> orderedRes = new HashMap<>();
            HashSet<Integer> eventId = new HashSet<>();

            for (OrderDTO e: spendingHistory){
                eventsDecrip.add(e.getDescription().replaceAll( "[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "").trim());
                eventType.add(e.getEventType());
                eventId.add(e.getEventId());
            }
            String t = String.join(" ",eventsDecrip);
            String[] historywords = t.split(" ");
            HashSet<String> filterWords = new HashSet<>();
            filterWords.add("This");
            filterWords.add("is");
            filterWords.add("a");
            filterWords.add("an");
            filterWords.add("the");
            filterWords.add("we");
            filterWords.add("you");
            filterWords.add("can");


            for(String e:eventType){
                List<EventDTO> res = eventMapper.getEventsByType(e);
                for( EventDTO i : res){
                    if(!eventId.contains(i.getEventId())){
                        orderedRes.put(i,1.0);
                    }
                }
            }
            if(orderedRes.isEmpty()){
                return new ResponseEntity<>(eventMapper.randomRecommendation(), HttpStatus.OK);
            }
            List<String> history = filterMethod(historywords,filterWords);
            for(EventDTO e : orderedRes.keySet()){

                orderedRes.put(e,cal(history,filterMethod(e.getDescription().replaceAll( "[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "").trim().split(" "),filterWords)));
            }

            List<Map.Entry<EventDTO, Double>>lst=new ArrayList(orderedRes.entrySet());


            Collections.sort(lst, new Comparator<Map.Entry<EventDTO, Double>>() {
                @Override
                public int compare(Map.Entry<EventDTO, Double> o1, Map.Entry<EventDTO, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
                }
            });
            List res = new ArrayList();
            for(Map.Entry<EventDTO, Double> entry : lst){
               res.add(entry.getKey());
            }

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        responseBody.put("error","cannot recommend");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
