package live.qtec.survey.network.wrapper

sealed class ApiResponse<T>{

    data class Progress<T>(var loading:Boolean) : ApiResponse<T>()
    data class Success<T>(var data:T) : ApiResponse<T>()
    data class Failure<T>(val errorMessage:Throwable) : ApiResponse<T>()
    data class ErrorCode<T>(val errorCode:Throwable): ApiResponse<T>()

    companion object{
        fun <T> loading(isLoading:Boolean): ApiResponse<T> = Progress(isLoading)
        fun <T> success(data:T): ApiResponse<T> = Success(data)
        fun <T> failure(error:Throwable): ApiResponse<T> = Failure(error)
        fun <T> errorCode(error:Throwable): ApiResponse<T> = ErrorCode(error)
    }
}