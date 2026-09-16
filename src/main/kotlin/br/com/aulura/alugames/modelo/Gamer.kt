package org.example.br.com.aulura.alugames.modelo

import java.util.Scanner
import kotlin.random.Random

data class Gamer(
    var nome: String,
    var email: String
){
    var dataNascimento: String? = null

    var usuario: String? = null
        set(value) {
            field = value
            if(idInterno.isNullOrBlank()){
                criaIdInterno()
            }
        }
    var idInterno: String? = null
            private set

    val jogosBuscados = mutableListOf<Jogo?>()

    constructor(

        nome: String,
        email: String,
        dataNascimento: String,
        usuario: String

    ):this(nome,email){
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criaIdInterno()
    }

//    init {
//        if(nome.isNullOrBlank()){
//            throw IllegalArgumentException("Nome está em branco")
//        }
//        this.email = validarEmail()
//    }


    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    fun criaIdInterno(){
        val numero = Random.nextInt(1000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {
        val regex = Regex(
            "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+" +
                    "@[a-zA-Z0-9]" +
                    "(?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?" +
                    "(?:\\.[a-zA-Z0-9]" +
                    "(?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$"
        )
        if(regex.matches(email)){
            return email

        }else
        {
            throw IllegalArgumentException("Email Inválido")
        }

    }
    companion object{
        fun criarGamer(leitura: Scanner): Gamer{

            println("Boa vindas ao AluGames! Vamos Fazer seu cadastro. Digite seu Nome")
            val nome = leitura.nextLine()

            println("Digite de Email.")
            val email = leitura.nextLine()

            println("Deseja complementae seu cadastro com usuário e data de nascimento? S/N")
            val opcao = leitura.nextLine()

            if(opcao.equals("s", ignoreCase = true)){

                println("Digite sua data de nascimento(DD/MM/YYYY)")
                val nacimento = leitura.nextLine()

                println("Digite seu nome de Usuário")
                val usuario = leitura.nextLine()

                return Gamer(nome,email,nacimento,usuario)

            }else{
                return Gamer(nome,email)

            }

        }
    }

}
