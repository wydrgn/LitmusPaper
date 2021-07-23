package com.drgn.common.service.impl;

import com.drgn.common.exception.ParamException;
import com.drgn.common.model.DTO.EurekaServiceRequestDTO;
import com.drgn.common.model.enums.Server;
import com.drgn.common.service.inter.RequestService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.*;

@Service
public class RequestServiceImpl implements RequestService {

    public static final String HTTP_STR = "http://";

    @Resource(name = "restTemplate")
    RestTemplate restTemplate;

    @Override
    public Object post(EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException {
        return call(eurekaServiceRequestDTO, RequestMethod.POST);
    }

    @Override
    public Object get(EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException {
        return call(eurekaServiceRequestDTO, RequestMethod.GET);
    }

    @Override
    public Object delete(EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException {
        return call(eurekaServiceRequestDTO, RequestMethod.DELETE);

    }

    @Override
    public Object put(EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException {
        return call(eurekaServiceRequestDTO, RequestMethod.PUT);

    }

    private Object call(EurekaServiceRequestDTO eurekaServiceRequestDTO, RequestMethod requestMethod) throws ParamException {
        String action = eurekaServiceRequestDTO.getAction();
        String serverName = eurekaServiceRequestDTO.getServerName();
        Optional<Server> op = Optional.ofNullable(Server.initFromName(serverName));
        if (!op.isPresent()) {
            throw new ParamException("服务名参数错误");
        }
        Server server = op.get();
        String url = HTTP_STR + server.value + server.contextPath + action;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //设置接收返回值的格式为json
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);
        Map<String, String> params = eurekaServiceRequestDTO.getParams();
        //headers.put(HttpHeaders.COOKIE, cookieList);
        if (requestMethod == RequestMethod.POST) {
            HashMap<String, Object> map = new HashMap<>();
            if (params != null && params.size() > 0) {
                for (String key : params.keySet()) {
                    map.put(key, params.get(key));
                }
            }
            return restTemplate.postForObject(url, new HttpEntity<>(map, headers), Object.class);
        } else {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            if (params != null && params.size() > 0) {
                for (String key : params.keySet()) {
                    map.add(key, params.get(key));
                }
            }
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
            UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(map).build();
            URI uri = uriComponents.encode().toUri();
            if (requestMethod == RequestMethod.GET) {
                ResponseEntity<Object> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Object.class);
                return exchange.getBody();
            } else if (requestMethod == RequestMethod.DELETE) {
                ResponseEntity<Object> exchange = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, Object.class);
                return exchange.getBody();
            } else if (requestMethod == RequestMethod.PUT) {
                HashMap<String, Object> map2 = new HashMap<>();
                if (params != null && params.size() > 0) {
                    for (String key : params.keySet()) {
                        map2.put(key, params.get(key));
                    }
                }
                ResponseEntity<Object> exchange = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(map2, headers), Object.class);
                return exchange.getBody();
            }
        }
        return null;
    }
}
