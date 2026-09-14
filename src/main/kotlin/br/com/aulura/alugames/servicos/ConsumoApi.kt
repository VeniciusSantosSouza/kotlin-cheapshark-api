package org.example.br.com.aulura.alugames.servicos

import com.google.gson.Gson
import org.example.br.com.aulura.alugames.modelo.InfoJogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {

    fun buscaJogo(id: String): InfoJogo {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client = HttpClient.newHttpClient()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .header("User-Agent", "MeuAppKotlin/1.0")
            .build()

        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()

        //println(json)

        val gson = Gson()

        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

        return meuInfoJogo
    }
}