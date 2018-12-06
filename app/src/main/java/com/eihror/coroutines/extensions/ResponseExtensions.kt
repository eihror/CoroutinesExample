package com.eihror.coroutines.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast
import retrofit2.Response

fun <T> Context.getErrors(response: Response<T>) {

    if (response.errorBody() != null) {
        Log.d("RESPONSE", response.errorBody()!!.string())
    }

    Toast.makeText(this, "Error ${response.code()} \nError Type: ${response.code().errorType()}", Toast.LENGTH_LONG).show()
}

fun Int.errorType(): String {
    return when (this) {
        400 -> "A requisição é inválida, conteúdo mal formado."
        401 -> "Acesso não autorizado ou erro na autenticação."
        403 -> "Acesso não permitido ou bloqueado."
        404 -> "Recurso não encontrado."
        409 -> "A requisição não pode ser processada por causa de um conflito."
        422 -> "Requisição bem formada, mas com erros de semântica."
        503 -> "Serviço indisponível, o servidor está fora do ar."
        500 -> "Erro interno do servidor ao processar a requisição."
        else -> "Erro interno do servidor ao processar a requisição."
    }

}

fun <T> Response<T>.getErrors(): String {
    return "Error ${code()} \nError Type: ${code().errorType()}"
}