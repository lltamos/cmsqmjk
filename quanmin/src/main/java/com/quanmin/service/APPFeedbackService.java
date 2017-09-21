package com.quanmin.service;

import com.quanmin.model.Feedback;
import com.quanmin.util.ResultUtils;

public interface APPFeedbackService {

	ResultUtils saveFeedback(Feedback feedback);
}
