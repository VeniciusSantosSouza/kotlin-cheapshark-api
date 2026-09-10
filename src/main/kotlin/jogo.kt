package org.example

data class Jogo(val titulo: String, var capa: String, var numero: String) {


    override fun toString(): String {
        return  "Meu Jogo \n " +
                "Titulo = $titulo \n " +
                "Capa = $capa \n " +
                "Numero = $numero"
    }

}