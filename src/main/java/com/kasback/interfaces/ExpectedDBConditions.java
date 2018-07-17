package com.kasback.interfaces;

import com.kasback.interfaces.ExpectedConditions;

public interface ExpectedDBConditions extends ExpectedConditions {
	boolean getDBConditions(String query);
}
