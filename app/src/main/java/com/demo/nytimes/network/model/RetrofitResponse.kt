package com.demo.nytimes.network.model

/**
 * wrapper class fro response received from network layer
 *
 * @param T
 * @property responseType
 * @property response
 * @property errorMessage
 */
data class RetrofitResponse<out T>(
    val responseType: ResponseType, val response: T?, val errorMessage: String?
) {
    companion object {
        fun <T> successResponse(responseData: T): RetrofitResponse<T> =
            RetrofitResponse(
                responseType = ResponseType.SUCCESS,
                response = responseData,
                errorMessage = null
            )

        fun <T> failureResponse(message: String): RetrofitResponse<T> =
            RetrofitResponse(
                responseType = ResponseType.FAILURE,
                response = null,
                errorMessage = message
            )

        fun <T> loadingData(): RetrofitResponse<T> =
            RetrofitResponse(
                responseType = ResponseType.LOADING,
                response = null,
                errorMessage = null
            )
    }

}

enum class ResponseType {
    SUCCESS, FAILURE, LOADING
}
