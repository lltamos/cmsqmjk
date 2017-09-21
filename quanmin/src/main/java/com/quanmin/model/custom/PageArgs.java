package com.quanmin.model.custom;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Data
public class PageArgs {
    @NotNull
    private Integer size;
    @NotNull
    private Integer page;
    private String queryStr;
    private boolean disPart;
}
