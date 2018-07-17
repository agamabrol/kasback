package com.kasback.interfaces;

import com.kasback.interfaces.ExpectedConditions;
import com.kasback.utils.EmailUtility;

public interface EmailFilterConditions extends ExpectedConditions {
	boolean getEmailFilterConditions(EmailUtility e,String emailSubject);
}
