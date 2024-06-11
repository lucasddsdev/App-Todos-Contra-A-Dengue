import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailService {
    @POST("/.netlify/functions/sendEmail")
    fun sendEmail(@Body emailRequest: EmailRequest): Call<EmailResponse>
}

data class EmailRequest(
    val subject: String,
    val name: String,
    val description: String
)

data class EmailResponse(
    val message: String,
    val error: String?
)

object RetrofitInstance {
    private const val BASE_URL = "https://splendorous-gecko-de7bd7.netlify.app"

    val api: EmailService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmailService::class.java)
    }
}
