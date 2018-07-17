package com.kasback.interfaces;

import com.kasback.interfaces.ExpectedConditions;
import com.kasback.utils.EmailUtility;

public interface EmailConditions extends ExpectedConditions {
	boolean getEmailConditions(EmailUtility e);
}