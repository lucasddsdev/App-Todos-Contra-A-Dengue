import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    fun sendEmail(subject: String, name: String, description: String) {
        val emailRequest = EmailRequest(subject, name, description)
        RetrofitInstance.api.sendEmail(emailRequest).enqueue(object : Callback<EmailResponse> {
            override fun onResponse(call: Call<EmailResponse>, response: Response<EmailResponse>) {
                if (response.isSuccessful) {
                    _response.value = response.body()?.message ?: "Email enviado com sucesso!"
                } else {
                    _response.value = "Falha ao enviar o email."
                }
            }

            override fun onFailure(call: Call<EmailResponse>, t: Throwable) {
                _response.value = "Erro: ${t.message}"
            }
        })
    }
}
