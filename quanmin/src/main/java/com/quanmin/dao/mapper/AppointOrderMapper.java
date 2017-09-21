package com.quanmin.dao.mapper;

import com.quanmin.model.jpapo.AppointOrder;

import java.util.List;
import java.util.Map;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
public interface AppointOrderMapper {
    List<Map<String, Object>> factorQueryList(AppointOrder appointOrder);
}
