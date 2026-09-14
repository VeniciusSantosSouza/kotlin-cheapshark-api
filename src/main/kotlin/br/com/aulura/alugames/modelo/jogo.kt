package org.example.br.com.aulura.alugames.modelo

data class Jogo(
    val titulo: String,
    var capa: String,
    var numero: String
    ){

    var descricao: String? = null

    override fun toString(): String {
        return  "Meu Jogo \n " +
                "Titulo = $titulo \n " +
                "Capa = $capa \n " +
                "Numero = $numero \n " +
                "Descrição = $descricao :>)"
    }

}