package com.quanmin.service;

import com.quanmin.model.Project;
import com.quanmin.model.Recuperate;
import com.quanmin.util.ResultUtils;

/**
 * Created by llsmp on 2017/7/20.
 */
public interface ICmsSwapDataSynService {


    ResultUtils synProject(Integer type, Project project);

    ResultUtils synRecuperate(Integer type, Recuperate recuperate);
}
