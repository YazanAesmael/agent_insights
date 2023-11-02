package com.yaxan.agent_insights.domain.model


sealed class PhoneCall {

    data class Incoming(
        val state: String? = null,
        val number: String? = null
    ) : PhoneCall()

}
