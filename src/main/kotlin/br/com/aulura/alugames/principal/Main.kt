package org.example.br.com.aulura.alugames.principal

import org.example.br.com.aulura.alugames.modelo.Gamer
import org.example.br.com.aulura.alugames.modelo.Jogo
import org.example.br.com.aulura.alugames.servicos.ConsumoApi
import transformarEmIdade
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro construido com sucesso. Dados Game")
    println(gamer)
    println("Idade do Gamer " + gamer.dataNascimento?.transformarEmIdade())


    do{
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

            gamer.jogosBuscados.add(meuJogo)


        }
        println("Deseja Busca um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while(resposta.equals("s", ignoreCase = true))

    println("Jogos Buscado:")
    println(gamer.jogosBuscados)

    println("\n Jogos Ordenados por título:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Título:" + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("\n Jogo Filtrados")
    println(jogosFiltrados)

    println("Deseja excluir algun da lista Original? S/N")
    val opcao = leitura.nextLine()

    if(opcao.equals("s", true)){
        println(gamer.jogosBuscados)


        println("\n Informe a posição do jogo que deseja excluir ")
        val  posicao =  leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\n Lista atualizada")

    println(gamer.jogosBuscados)

    println("Busca Finalizada com Sucesso")
}