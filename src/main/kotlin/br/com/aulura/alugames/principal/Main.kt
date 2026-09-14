package org.example.br.com.aulura.alugames.principal

import org.example.br.com.aulura.alugames.modelo.Jogo
import org.example.br.com.aulura.alugames.servicos.ConsumoApi
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um codigo de jogo para buscar:")
    val busca = leitura.nextLine()

    val buscaApi = ConsumoApi()
    val informacaoJogo = buscaApi.buscaJogo(busca)

    var meuJogo: Jogo? = null

    val resultado = runCatching {

         meuJogo = Jogo(
             titulo = informacaoJogo.info.title,
             capa = informacaoJogo.info.thumb,
             numero = informacaoJogo.info.steamAppID
         )
    }

    resultado.onFailure {
        println("Jogo inexistente. Tente outro id.")
    }
    resultado.onSuccess {

        println("Deseja inserir uma descrição personalizada? S/N")

        val opcao = leitura.nextLine()

        if (opcao.equals("s", ignoreCase = true)){

            println("Insira a descrição personalizada para o jogo")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        }else{
            meuJogo?.descricao = meuJogo?.titulo

        }

        println(meuJogo)
        resultado.onSuccess {
            println("Busca Finalizada com Sucesso")
        }

    }
}