package com.drgn.common.service.inter;

import com.drgn.common.exception.ParamException;
import com.drgn.common.model.DTO.EurekaServiceRequestDTO;

public interface RequestService {

    /**
     * 通用post请求
     *
     * @param eurekaServiceRequestDTO eureka请求服务DTO
     * @return 服务接口返回数据
     */
    Object post(EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException;

    /**
     * 通用get请求
     *
     * @param eurekaServiceRequestDTO eureka请求服务DTO
     * @return 服务接口返回数据
     */
    Object get(EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException;

    /**
     * 通用delete请求
     *
     * @param eurekaServiceRequestDTO eureka请求服务DTO
     * @return 服务接口返回数据
     */
    Object delete(EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException;

    /**
     * 通用put请求
     *
     * @param eurekaServiceRequestDTO eureka请求服务DTO
     * @return 服务接口返回数据
     */
    Object put(EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException;
}
