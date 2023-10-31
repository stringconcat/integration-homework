package com.stringconcat.integration.service.action

import com.stringconcat.integration.service.data.OperationStatus

interface StatusValidator {
    fun validate(status: OperationStatus);
}